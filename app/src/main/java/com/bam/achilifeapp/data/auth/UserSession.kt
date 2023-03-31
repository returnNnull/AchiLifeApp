package com.bam.achilifeapp.data.auth

import com.bam.achilifeapp.data.auth.models.LoggedUser
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserSession @Inject constructor(){

    private val _loggedState = MutableStateFlow(false)
    val loggedState = _loggedState.asStateFlow()
    var user: LoggedUser? = null
        private set

    fun isLogged() = loggedState.value

    fun set(loggedUser: LoggedUser?) {
        loggedUser?.let {
            _loggedState.update { true}
        }

    }

    fun clear() {
        user = null
        _loggedState.update { false}
    }
}




