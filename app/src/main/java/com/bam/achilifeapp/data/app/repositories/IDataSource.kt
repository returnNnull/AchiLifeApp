package com.bam.achilifeapp.data.app.repositories

import kotlinx.coroutines.flow.Flow

interface IRemoteDataSource<T, I> {
    suspend fun getAll(): Result<List<T>>
    suspend fun insert(t: T) : Result<T>
    suspend fun update(t: T) : Result<T>
    suspend fun delete(t: T) : Result<T>
    suspend fun getById(id: I) : Result<T>
}

interface ILocalDataSource<E, I>{
    fun getAllFlow(): Flow<List<E>>
    fun getByIdFlow(id: I): Flow<E>
    suspend fun getAll(): List<E>
    suspend fun insertAll(items: List<E>)
    suspend fun insert(t: E)
    suspend fun update(t: E)
    suspend fun delete(t: E)
    suspend fun getById(id: I) : E
    suspend fun clear()
}

