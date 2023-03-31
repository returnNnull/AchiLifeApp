package com.bam.achilifeapp.data.app.room

import androidx.room.Database
import com.bam.achilifeapp.data.app.room.dao.IAwardProgressDao
import com.bam.achilifeapp.data.app.room.dao.IAwardSchemeDao
import com.bam.achilifeapp.data.app.room.dao.ICategorySchemeDao
import com.bam.achilifeapp.data.app.room.dao.IProfileDao

@Database(
    entities = [
        AwardSchemeEntity::class,
        CategoriesSchemeEntity::class,
        AwardProgressEntity::class,
        ProfileEntity::class
    ],
    version = 1
)
abstract class RoomDataBase {
    abstract fun awardSchemeDao(): IAwardSchemeDao
    abstract fun categorySchemeDao() : ICategorySchemeDao
    abstract fun awardProgressDao() : IAwardProgressDao
    abstract fun profileDao() : IProfileDao
}