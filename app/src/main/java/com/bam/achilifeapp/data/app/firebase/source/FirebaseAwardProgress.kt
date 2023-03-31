package com.bam.achilifeapp.data.app.firebase.source

import com.bam.achilifeapp.data.app.firebase.AwardProgressDTO
import com.bam.achilifeapp.data.app.firebase.FirebaseDataSource
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.toObject

class FirebaseAwardProgress(collectionName: String = "award_progress") : FirebaseDataSource<AwardProgressDTO>(
    collectionName
) {
    override fun parse(v: DocumentSnapshot): AwardProgressDTO? {
        val result = v.toObject<AwardProgressDTO>()
        result?.id = v.id
        return result
    }

}