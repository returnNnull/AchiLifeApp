package com.bam.achilifeapp.data.app.firebase

import com.bam.achilifeapp.data.app.repositories.IRemoteDataSource
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

abstract class FirebaseDataSource<T : DTO>(private val collectionName: String) :
    IRemoteDataSource<T, String> {

    private val db = Firebase.firestore
    private val ioDispatcher = Dispatchers.IO

    fun <V : Any?> where(fieldName: String, value: V) = flow {
        val task = db.collection(collectionName).whereEqualTo(fieldName, value).get()
        if (!task.isSuccessful) {
            throw task.exception!!
        }
        val array = ArrayList(task.result.map { t -> parse(t)!! })
        emit(array)
    }.flowOn(ioDispatcher)


    override suspend fun getAll() = withContext(ioDispatcher) {
        try {
            val task = db.collection(collectionName).get()
            if (!task.isSuccessful) {
                throw task.exception!!
            }
            Result.success(task.result.map { c -> parse(c)!! })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun insert(t: T) = withContext(ioDispatcher) {
        try {
            val collection = db.collection(collectionName)
            val task = collection.add(t)
            if (!task.isSuccessful) {
                throw task.exception!!
            }
            Result.success(t)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    override suspend fun delete(t: T) = withContext(ioDispatcher) {
        try {
            val task = db.collection(collectionName).document(t.id).delete()
            if (!task.isSuccessful) {
                throw task.exception!!
            }
            Result.success(t)
        } catch (e: Exception) {
            Result.failure(e)
        }

    }

    override suspend fun update(t: T) = withContext(ioDispatcher) {
        try {
            val collection = db.collection(collectionName).document(t.id)
            val task = collection.set(t)
            if (!task.isSuccessful) {
                throw task.exception!!
            }
            Result.success(t)
        }
        catch (e : Exception){
            Result.failure(e)
        }

    }


    override suspend fun getById(id: String) = withContext(ioDispatcher) {
        try {
            val task = db.collection(collectionName).document(id).get()
            if (!task.isSuccessful) {
                throw task.exception!!
            }
            Result.success(parse(task.result)!!)
        }
        catch (e: Exception){
            Result.failure(e)
        }


    }


    abstract fun parse(v: DocumentSnapshot): T?

}
