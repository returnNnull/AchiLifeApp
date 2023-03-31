package com.bam.achilifeapp.data.app.repositories

import com.bam.achilifeapp.data.app.firebase.DTO
import com.bam.achilifeapp.data.app.room.LocalEntity
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

abstract class Repository<D : DTO, E : LocalEntity, M, ID>(
    private val remote: IRemoteDataSource<D, ID>,
    private val local: ILocalDataSource<E, ID>,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default,

    ) {
    private val coroutineScope: CoroutineScope = CoroutineScope(Job() + defaultDispatcher)

    val getAll: Flow<List<M>> = local.getAllFlow().map { list ->
        list.map { entity ->
            entity.toModel()
        }
    }.flowOn(defaultDispatcher)

    suspend fun init() {
        return withContext(defaultDispatcher) {
            local.clear()
            remote.getAll()
                .onSuccess { items ->
                    local.insertAll(
                        items.map { i ->
                            i.toEntity()
                        })
                }
        }
    }

    abstract fun E.toModel(): M
    abstract fun D.toEntity(): E
    abstract fun M.toEntity(): E
    abstract fun E.toDTO(): D


    suspend fun getAll(): List<M>{
        return withContext(defaultDispatcher){
            local.getAll().map {
                it.toModel()
            }
        }
    }

    suspend fun getById(idList: List<ID>): List<M> {
        return withContext(defaultDispatcher) {
            idList.map {
                getById(it)
            }
        }

    }

    suspend fun getById(id: ID): M {
        return withContext(defaultDispatcher) {
            local.getById(id).toModel()
        }
    }

    suspend fun update(t: M) {
        withContext(defaultDispatcher) {
            val entity = t.toEntity()
            remote.update(entity.toDTO())
                .onSuccess {
                    entity.isSynchronized = true
                }
            local.update(entity)
        }
    }

    suspend fun delete(t: M) {
        withContext(defaultDispatcher) {
            val entity = t.toEntity()
            remote.delete(entity.toDTO())
                .onSuccess {
                    local.delete(entity)
                }.onFailure {
                    entity.isDeleted = true
                    local.update(entity)
                }
        }
    }

    suspend fun insert(t: M) {
        withContext(defaultDispatcher) {
            val entity = t.toEntity()
            remote.insert(entity.toDTO())
                .onSuccess {
                    entity.isSynchronized = true
                }
            local.insert(entity)
        }
    }


    private suspend fun saveLocal(remoteScheme: List<D>) {
        withContext(defaultDispatcher) {
            val items = remoteScheme.map {
                it.toEntity()
            }
            local.insertAll(items)
        }
    }


}

