package com.bam.achilifeapp.data.app.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.bam.achilifeapp.data.app.room.CategoriesSchemeEntity
import com.bam.achilifeapp.data.app.room.IEntityDao
import kotlinx.coroutines.flow.Flow

@Dao
interface ICategorySchemeDao : IEntityDao<CategoriesSchemeEntity> {

    @Query("select * from CategoriesSchemeEntity")
    override fun getAllFlow(): Flow<List<CategoriesSchemeEntity>>

    @Query("select * from CategoriesSchemeEntity")
    override suspend fun getAll(): List<CategoriesSchemeEntity>

    @Query("select * from CategoriesSchemeEntity where id = :id")
    override suspend fun getById(id: String): CategoriesSchemeEntity

    @Query("select * from CategoriesSchemeEntity  where id = :id")
    override fun getByIdFlow(id: String): Flow<CategoriesSchemeEntity>
}