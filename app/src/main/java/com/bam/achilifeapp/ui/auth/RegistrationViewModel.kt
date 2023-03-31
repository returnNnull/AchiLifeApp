package com.bam.achilifeapp.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bam.achilifeapp.data.auth.FirebaseAuthService
import com.bam.achilifeapp.data.auth.models.UserDTO
import com.bam.achilifeapp.ui.components.BtnUiState
import com.bam.achilifeapp.ui.components.DialogUiState
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
class RegistrationViewModel
@Inject constructor(private val service: FirebaseAuthService): ViewModel() {
    val emailState = EditTextUiState(EmailValidator())
    val passState = EditTextUiState(PasswordValidator())
    val repeatPass = EditTextUiState()
    val displayName = EditTextUiState()
    val notifyUiState = NotifyUiState()
    val registerBtn = BtnUiState()



    private val _registrationState = MutableStateFlow(false)
    val registerState = _registrationState.asStateFlow()


    fun registration(){
        if (emailState.isValid() && passState.isValid() && repeatPass.isValid()){
            viewModelScope.launch {
                service.register(UserDTO(login = emailState.text, pass = passState.text)).collect{
                    it.apply {
                        onSuccess {
                            updateStateWithSuccess()
                        }
                        onFailure { t ->
                            updateStateWithError(t.message)
                        }
                    }
                }
            }
        }
    }

    private suspend fun updateStateWithError(message: String?) {
        notifyUiState.show(message ?: "Server error")
        delay(1500)
        notifyUiState.close()
    }

    private fun updateStateWithSuccess() {
        _registrationState.update { true }
    }

}