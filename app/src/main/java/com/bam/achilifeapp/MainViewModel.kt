package com.bam.achilifeapp

import androidx.lifecycle.ViewModel
import com.bam.achilifeapp.data.auth.UserSession
import com.bam.achilifeapp.data.auth.models.LoggedUser
import com.bam.achilifeapp.data.auth.models.Roles
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(val userSession: UserSession) : ViewModel() {
    private val aut = Firebase.auth

    fun checkAuth() {
        val user = aut.currentUser
        if (user != null) {
            userSession.set(
                LoggedUser(
                    id = user.uid,
                    displayName = user.displayName ?: "",
                    role = Roles.USER
                )
            )
        }
    }

}