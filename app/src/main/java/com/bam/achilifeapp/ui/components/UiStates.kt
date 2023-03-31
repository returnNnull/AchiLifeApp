package com.bam.achilifeapp.ui.components

import com.bam.achilifeapp.ui.components.utils.TextValidator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class DialogUiState {

    private val _state = MutableStateFlow(UiStates())
    fun flow() = _state.asStateFlow()

    fun show(text: String){
        _state.update {
            it.copy(isOpen = true, text = text)
        }
    }

    fun close(){
        _state.update {
            it.copy(isOpen = false)
        }
    }

    data class UiStates(
        val isOpen: Boolean = false,
        val text: String = ""
    )
}

class BtnUiState {
    private val _state = MutableStateFlow(UiStates())
    fun flow() = _state.asStateFlow()

    fun progress() {
        _state.update {
            it.copy(isWait = true, block = true)
        }
    }

    fun reset() {
        _state.update {
            it.copy(isWait = false, block = false)
        }
    }

    data class UiStates(
        val isWait: Boolean = false,
        val block: Boolean = false
    )
}


class EditTextUiState(private val validator: TextValidator = TextValidator()) {

    private val _state = MutableStateFlow(UiStates())

    var text: String
    get() {
       return _state.value.text
    }
    set(value) {
        clear()
        _state.update {
            it.copy(text = value)
        }
    }
    fun flow() = _state.asStateFlow()

    fun error(text: String){
        _state.update {
            it.copy(isError = true, error = text)
        }
    }

    fun isValid(): Boolean {
        val report = validator.check(_state.value.text)
        if (!report.isValid) {
            error(report.error)
            return false
        }
        return true
    }

    private fun clear() {
        _state.update {
            it.copy(isError = false, error = "")
        }
    }

    data class UiStates(
        val text: String = "",
        val isError: Boolean = false,
        val error: String = ""

    )
}

class NotifyUiState {

    private val _state = MutableStateFlow(UiStates())
    val state = _state.asStateFlow()

    data class UiStates(
        val isOpen: Boolean = false,
        val text: String = ""
    )

    fun show(text: String) {
        _state.update {
            it.copy(isOpen = true, text = text)
        }
    }

    fun close() {
        _state.update {
            it.copy(isOpen = false)
        }
    }
}
