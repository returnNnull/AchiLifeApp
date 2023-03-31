package com.bam.achilifeapp.data.app.firebase.source

import com.bam.achilifeapp.data.app.firebase.FirebaseDataSource
import com.bam.achilifeapp.data.app.firebase.AwardSchemeDTO
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.toObject

class FirebaseAwardsScheme(collectionName: String = "awards_scheme")
    : FirebaseDataSource<AwardSchemeDTO>(collectionName) {

    override fun parse(v: DocumentSnapshot): AwardSchemeDTO? {
        val result = v.toObject<AwardSchemeDTO>()
        result?.id = v.id
        return result
    }
}