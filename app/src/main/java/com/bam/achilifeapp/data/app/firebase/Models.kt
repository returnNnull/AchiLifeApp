package com.bam.achilifeapp.data.app.firebase

open class DTO(
    var id: String,
)

class ProfileDTO(
    id: String,
    val name: String,
    val img: String,
    val motivation: String,
    val bestCategoryId: String,
    val awardsBest: MutableList<String>,
    val awardsProgress: MutableList<String>
) : DTO(id)

class AwardSchemeDTO(
    id: String,
    val name: String,
    val about: String,
    val img: String,
    val maxValue: Int,
    val categoriesId: String,
) : DTO(id)





class CategoriesSchemeDTO(
    id: String,
    val name: String,
    val img: String,
) : DTO(id)


class AwardProgressDTO (
    id: String,
    val value: Int,
    val awardId: String,
    val categoryId: String
) : DTO(id)

