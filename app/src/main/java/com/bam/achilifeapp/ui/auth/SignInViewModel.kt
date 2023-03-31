package com.bam.achilifeapp.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bam.achilifeapp.data.auth.FirebaseAuthService
import com.bam.achilifeapp.data.auth.UserSession
import com.bam.achilifeapp.data.auth.models.LoggedUser
import com.bam.achilifeapp.data.auth.models.UserDTO
import com.bam.achilifeapp.ui.components.BtnUiState
import com.bam.achilifeapp.ui.components.EditTextUiState
import com.bam.achilifeapp.ui.components.NotifyUiState
import com.bam.achilifeapp.ui.components.utils.EmailValidator
import com.bam.achilifeapp.ui.components.utils.PasswordValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val session: UserSession,
    private val service: FirebaseAuthService
) : ViewModel() {

    val emailUiState = EditTextUiState(EmailValidator())
    val passUiState = EditTextUiState(PasswordValidator())
    val notifyUiState = NotifyUiState()
    val signInBtnState = BtnUiState()

    fun signIn() {

        if (emailUiState.isValid() && passUiState.isValid()) {
            signInBtnState.progress()
            viewModelScope.launch() {
                val user = UserDTO(login = emailUiState.text, pass = passUiState.text)
                service.login(user).collect {
                    it.onSuccess { u ->
                        session.set(LoggedUser(u.id, u.displayName))
                    }.onFailure { f ->
                        updateStateWithError(f)
                    }
                }
            }
        }


    }

    private suspend fun updateStateWithError(f: Throwable) {
        signInBtnState.reset()
        notifyUiState.show(f.message ?: "Server error")
        delay(1500)
        notifyUiState.close()
    }

}

