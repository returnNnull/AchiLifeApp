package com.bam.achilifeapp.data.app.domain

import com.bam.achilifeapp.data.app.repositories.*
import com.bam.achilifeapp.data.auth.UserSession
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetAwardsWithProgressAndCategory @Inject constructor(
    private val awardsProgressRepository: AwardsProgressRepository,
    private val awardsSchemeRepository: AwardsSchemeRepository,
    val categoriesSchemeRepository: CategoriesSchemeRepository,
    val userSession: UserSession,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) {


    suspend operator fun invoke(): List<AwardWithProgressAndCategory> =
        withContext(defaultDispatcher) {
            val result: List<AwardWithProgressAndCategory> = mutableListOf()
            val awardsSchemeList = awardsSchemeRepository.getAll()
            val progress = awardsProgressRepository.getAll()


            result
        }

    fun List<AwardSchemeModel>.merge(list: List<AwardProgressModel>): List<AwardWithProgressAndCategory> {
        for (i in this) {
            for (j in list) {
                if (i.id == j.awardId) {
                }
            }
        }
    }

    private fun joinModels(
        awardProgressModel: AwardProgressModel,
        awardSchemeModel: AwardSchemeModel,
        categoriesSchemeModel: CategoriesSchemeModel
    ): AwardWithProgressAndCategory {
        return AwardWithProgressAndCategory(
            id = awardSchemeModel.id,
            name = awardSchemeModel.name,
            img = awardSchemeModel.img,
            about = awardSchemeModel.about,
            categoryName = categoriesSchemeModel.name
        )
    }
}