package com.bam.achilifeapp.data.app.domain

import com.bam.achilifeapp.data.app.repositories.AwardProgressModel
import com.bam.achilifeapp.data.app.repositories.AwardSchemeModel
import com.bam.achilifeapp.data.app.repositories.CategoriesSchemeModel

class AwardWithProgressAndCategory(
    val info: AwardSchemeModel,
    val progress: AwardProgressModel,
    val category: CategoriesSchemeModel
)
