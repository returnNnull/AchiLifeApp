package com.bam.achilifeapp.data.app.domain

import com.bam.achilifeapp.data.app.repositories.AwardsProgressRepository
import com.bam.achilifeapp.data.app.repositories.AwardsSchemeRepository
import com.bam.achilifeapp.data.app.repositories.CategoriesSchemeRepository
import com.bam.achilifeapp.data.app.repositories.ProfileRepository
import com.bam.achilifeapp.data.auth.UserSession
import kotlinx.coroutines.*
import javax.inject.Inject

class GetProfile @Inject constructor(
    private val userSession: UserSession,
    private val profileRepository: ProfileRepository,
    private val awardsSchemeRepository: AwardsSchemeRepository,
    private val awardsProgressRepository: AwardsProgressRepository,
    private val categoriesSchemeRepository: CategoriesSchemeRepository,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) {

    suspend operator fun invoke(): Profile {
        return withContext(defaultDispatcher) {

            val bestCategoryTask = getBestCategoryNameAsync()

            val profile = profileRepository.getById(userSession.user!!.id)
            val bestAwardsId = profile.awardsBest
            val bestAwards = awardsSchemeRepository.getById(bestAwardsId)
                .map { BestAward(it.img, it.name) }
            val bestCategory = bestCategoryTask.await()

            Profile(
                id = profile.id,
                name = profile.name,
                motivation = profile.motivation,
                img = profile.img,
                bestCategoryName = bestCategory?.name ?: "",
                bestAwards = bestAwards
            )
        }
    }

    private suspend fun getBestCategoryNameAsync() = coroutineScope {
        async {
            val bestCategoryId = awardsProgressRepository.getAll()
                .groupBy { it.categoryId }
                .maxByOrNull {
                    it.value.size
                }?.key
            val bestCategory = bestCategoryId?.let {
                categoriesSchemeRepository.getById(it)
            }
            bestCategory
        }
    }
}