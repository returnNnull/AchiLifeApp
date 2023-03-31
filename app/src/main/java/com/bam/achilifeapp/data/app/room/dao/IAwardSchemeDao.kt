package com.bam.achilifeapp.data.app.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.bam.achilifeapp.data.app.room.AwardProgressEntity
import com.bam.achilifeapp.data.app.room.IEntityDao
import kotlinx.coroutines.flow.Flow

@Dao
interface IAwardSchemeDao : IEntityDao<AwardProgressEntity> {

    @Query("select * from AwardProgressEntity")
    override fun getAllFlow(): Flow<List<AwardProgressEntity>>

    @Query("select * from AwardProgressEntity")
    override suspend fun getAll(): List<AwardProgressEntity>

    @Query("select * from AwardProgressEntity where id = :id")
    override suspend fun getById(id: String): AwardProgressEntity

    @Query("select * from AwardProgressEntity  where id = :id")
    override fun getByIdFlow(id: String): Flow<AwardProgressEntity>
}