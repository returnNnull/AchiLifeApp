package com.bam.achilifeapp.data.app.room

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.bam.achilifeapp.data.app.repositories.ILocalDataSource


interface IEntityDao<T>: ILocalDataSource<T, String> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insert(t : T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insertAll(items: List<T>)

    @Update
    override suspend fun update(t: T)

    @Delete
    override suspend fun delete(t : T)


}