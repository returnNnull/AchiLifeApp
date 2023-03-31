package com.bam.achilifeapp.data.app.firebase.source

import android.net.Uri
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.tasks.await
import java.io.File

class FirebaseImageCloud {

    private val server = Firebase.storage
    fun save(file: File, folder: Folder): Result<Uri> {
        return try {
            val ref = server.reference
            val uri = Uri.fromFile(file)
            val riversRef = ref.child("${folder.name.lowercase()}/${uri.lastPathSegment}")
            val task = riversRef.putFile(uri)
            if (!task.isSuccessful) {
                throw task.exception!!
            }
            Result.success(riversRef.downloadUrl.result)
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }

    enum class Folder() {
        CATEGORIES,
        AWARDS,
        AVATARS
    }

}