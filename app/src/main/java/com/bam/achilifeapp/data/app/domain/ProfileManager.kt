package com.bam.achilifeapp.data.app.domain

import com.bam.achilifeapp.data.app.repositories.AwardsProgressRepository
import com.bam.achilifeapp.data.app.repositories.AwardsSchemeRepository
import com.bam.achilifeapp.data.app.repositories.CategoriesSchemeRepository
import com.bam.achilifeapp.data.app.repositories.ProfileRepository
import com.bam.achilifeapp.data.auth.UserSession
import javax.inject.Inject

class ProfileManager @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val awardsProgressRepository: AwardsProgressRepository,
    private val awardsSchemeRepository: AwardsSchemeRepository,
    private val categoriesSchemeRepository: CategoriesSchemeRepository,
    private val userSession: UserSession
) {


}
