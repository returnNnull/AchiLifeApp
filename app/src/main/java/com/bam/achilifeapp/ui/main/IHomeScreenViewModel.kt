package com.bam.achilifeapp.ui.main

import androidx.compose.runtime.toMutableStateList
import com.bam.achilifeapp.data.app.firebase.Award
import com.bam.achilifeapp.data.app.firebase.Categories
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

interface IHomeScreenViewModel {
    fun profileUiState(): ProfileUiState
    fun awardsProgressUiState(): ListUiState<Award>
    fun categoriesProgressUiState(): ListUiState<Categories>
}

class ListUiState<T : Any>(list: List<T>) {
    private val _items = list.toMutableStateList()
    val items: List<T>
        get() = _items

    fun add(t: T) {
        _items.add(t)
    }

    fun delete(t: T) {
        _items.remove(t)
    }

    fun update(t: T, index: Int) {
        _items[index] = t
    }
}

class ProfileUiState(
    _uiState: UiStates = UiStates(),
    _bestAwards: ListUiState<Award> = ListUiState(List(10) { Award() })
) {

    private val _state = MutableStateFlow(_uiState)
    fun collect() = _state.asStateFlow()

    val bestAwards: List<Award> = _bestAwards.items

    fun name(name: String) {
        _state.update {
            it.copy(name = name)
        }
    }

    fun motivation(text: String) {
        _state.update {
            it.copy(motivation = text)
        }
    }

    fun bestCategory(text: String) {
        _state.update {
            it.copy(bestCategory = text)
        }
    }

    fun img(url: String) {
        _state.update {
            it.copy(img = url)
        }
    }

    data class UiStates(
        val name: String = "user",
        val motivation: String = "Все или ничего",
        val bestCategory: String = "Спорт",
        val img: String = "url",
        val bestAwardList: List<Award> = List(5) { Award() }
    )
}