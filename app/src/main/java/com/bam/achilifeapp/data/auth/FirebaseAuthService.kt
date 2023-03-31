package com.bam.achilifeapp.data.auth

import com.bam.achilifeapp.data.auth.models.UserDTO
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Module
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseAuthService @Inject constructor(){

    private val auth = Firebase.auth

     fun login(user: UserDTO): Flow<Result<UserDTO>> = callbackFlow {
            auth.signInWithEmailAndPassword(user.login, user.pass)
                .addOnSuccessListener{
                    it.user?.let { u ->
                       trySend(
                            Result.success(
                                UserDTO(
                                    id = u.uid,
                                    displayName = u.displayName ?: "",
                                )
                            )
                        )
                    }
                }
                .addOnFailureListener {
                    trySend(Result.failure(it))
                }
            awaitClose()
        }

    fun logout() {
        auth.signOut()
    }

    fun register(user: UserDTO): Flow<Result<UserDTO>> =
        callbackFlow {
            auth.createUserWithEmailAndPassword(user.login, user.pass)
                .addOnSuccessListener{
                    it.user?.let { u ->
                        trySend(
                            Result.success(
                                UserDTO(
                                    id = u.uid,
                                    displayName = u.displayName ?: "",
                                )
                            )
                        )
                    }
                }
                .addOnFailureListener {
                    trySend(Result.failure(it))
                }
        }


    fun changePassword(user: UserDTO): Flow<Result<UserDTO>> =
        callbackFlow {
            auth.sendPasswordResetEmail(user.login)
                .addOnCompleteListener {
                    trySend(Result.success(user))
                }
                .addOnFailureListener {
                    trySend(Result.failure(it))
                }
        }



    fun isLogged(): Flow<UserDTO>  = flow {
        val user = auth.currentUser
        user?.let {
            emit(UserDTO(id = it.uid, displayName = it.displayName ?: ""))
        }
    }
}