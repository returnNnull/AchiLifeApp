package com.bam.achilifeapp.data.app.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.bam.achilifeapp.data.app.room.IEntityDao
import com.bam.achilifeapp.data.app.room.ProfileEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface IProfileDao : IEntityDao<ProfileEntity> {

    @Query("select * from ProfileEntity")
    override fun getAllFlow(): Flow<List<ProfileEntity>>

    @Query("select * from ProfileEntity")
    override suspend fun getAll(): List<ProfileEntity>


    @Query("select * from ProfileEntity where id = :id")
    override suspend fun getById(id: String): ProfileEntity

    @Query("select * from ProfileEntity  where id = :id")
    override fun getByIdFlow(id: String): Flow<ProfileEntity>
}