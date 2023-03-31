package com.bam.achilifeapp.data.app.repositories

class AwardSchemeModel(
    val id: String,
    val name: String,
    val about: String,
    val img: String,
    val maxValue: Int,
    val categoriesId: String
)

class CategoriesSchemeModel(
    val id: String,
    val name: String,
    val img: String
)
class AwardProgressModel (
    val id: String,
    val value: Int,
    val awardId: String,
    val categoryId: String

)

class ProfileModel(
    val id: String,
    val name: String,
    val img: String,
    val motivation: String,
    val bestCategoryId: String,
    val awardsBest: MutableList<String>,
    val awardsProgress: MutableList<String>
)