package com.bam.achilifeapp.data.app.firebase.source

import com.bam.achilifeapp.data.app.firebase.FirebaseDataSource
import com.bam.achilifeapp.data.app.firebase.ProfileDTO
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.toObject

class FirebaseProfiles(collectionName: String = "profiles") : FirebaseDataSource<ProfileDTO>(collectionName) {
    override fun parse(v: DocumentSnapshot): ProfileDTO? {
        val result = v.toObject<ProfileDTO>()
        result?.id = v.id
        return result
    }
}