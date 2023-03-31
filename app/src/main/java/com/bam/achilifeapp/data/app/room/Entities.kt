package com.bam.achilifeapp.data.app.room

import androidx.room.Entity
import androidx.room.PrimaryKey

open class LocalEntity(
    @PrimaryKey
    var id: String,
    var isSynchronized: Boolean = false,
    var isDeleted: Boolean = false
)

@Entity
class AwardSchemeEntity(
    id: String,
    var name: String,
    var about: String,
    var img: String,
    var maxValue: Int,
    var categoriesId: String
) : LocalEntity(id)

@Entity
class CategoriesSchemeEntity(
    id: String,
    var name: String,
    var img: String
) : LocalEntity(id)


@Entity
class AwardProgressEntity(
    id: String,
    var value: Int,
    var awardId: String,
    val categoryId: String
) : LocalEntity(id)

@Entity
class ProfileEntity(
    id: String,
    val name: String,
    val img: String,
    val motivation: String,
    val bestCategoryId: String,
    val awardsBest: MutableList<String>,
    val awardsProgress: MutableList<String>
) : LocalEntity(id)
