package com.example.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.module.DataState
import com.example.data.di.IoDispatcher
import com.example.domain.mapper.EpisodeDetails
import com.example.domain.usecase.EpisodeDetailsUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class EpisodeDetailsViewModel @Inject constructor(
    private val episodeDetailsUseCase: EpisodeDetailsUseCase,
    @IoDispatcher val ioDispatcher: CoroutineDispatcher
) :
    ViewModel() {

    private val _episodeDetails: MutableStateFlow<DataState<EpisodeDetails>> = MutableStateFlow(
        DataState.Loading
    )
    val episodeDetails: StateFlow<DataState<EpisodeDetails>> = _episodeDetails

    var episodeTitleForTopBar = ""
        private set

    fun getEpisodeDetailsByUsingEpisodeId(episodeId: String) {
        viewModelScope.launch(ioDispatcher) {
            val result = episodeDetailsUseCase.invoke(episodeId)
            _episodeDetails.emit(DataState.Loading)
            when (result) {
                is DataState.Success -> {
                    episodeTitleForTopBar = result.data.title
                    _episodeDetails.emit(DataState.Success(result.data))
                }

                is DataState.Error -> {
                    _episodeDetails.emit(result)
                }

                DataState.Loading -> {
                    _episodeDetails.emit(DataState.Loading)
                }
            }
        }
    }
}
