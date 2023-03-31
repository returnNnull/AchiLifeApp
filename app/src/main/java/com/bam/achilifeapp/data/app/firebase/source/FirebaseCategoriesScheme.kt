package com.bam.achilifeapp.data.app.firebase.source

import com.bam.achilifeapp.data.app.firebase.FirebaseDataSource
import com.bam.achilifeapp.data.app.firebase.CategoriesSchemeDTO
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.toObject

class FirebaseCategoriesScheme(collectionName: String = "categories_scheme")
    : FirebaseDataSource<CategoriesSchemeDTO>(collectionName) {
    override fun parse(v: DocumentSnapshot): CategoriesSchemeDTO? {
        val result = v.toObject<CategoriesSchemeDTO>()
        result?.id = v.id
        return result
    }


}