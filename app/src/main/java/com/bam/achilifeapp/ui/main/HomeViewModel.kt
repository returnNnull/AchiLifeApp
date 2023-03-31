package com.bam.achilifeapp.ui.main

import androidx.lifecycle.ViewModel
import com.bam.achilifeapp.data.app.firebase.Award
import com.bam.achilifeapp.data.app.firebase.Categories
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeViewModel @Inject constructor(

): ViewModel(), IHomeScreenViewModel {

    private val _profileUiState = ProfileUiState()
    private val _awardsList = flow<List<Award>> {

    }

    override fun profileUiState(): ProfileUiState {
        return _profileUiState
    }

    override fun awardsProgressUiState(): ListUiState<Award> {
        TODO("Not yet implemented")
    }

    override fun categoriesProgressUiState(): ListUiState<Categories> {
        TODO("Not yet implemented")
    }
}