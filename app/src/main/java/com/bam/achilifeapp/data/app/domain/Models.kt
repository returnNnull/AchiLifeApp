package com.bam.achilifeapp.data.app.domain

import com.bam.achilifeapp.data.app.repositories.AwardProgressModel
import com.bam.achilifeapp.data.app.repositories.AwardSchemeModel
import com.bam.achilifeapp.data.app.repositories.CategoriesSchemeModel


data class Profile(
    val id: String,
    val name: String,
    val motivation: String,
    val img: String,
    val bestCategoryName: String,
    val bestAwards: List<BestAward>
)


data class BestAward(
    val img: String,
    val name: String
)

data class Award(
    val info: AwardSchemeModel,
    val progress: AwardProgressModel,
    val category: CategoriesSchemeModel
)

data class Category(
    val id: String,
    val name: String,
    val img: String
)