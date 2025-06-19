package edu.ucne.freimyhidalgo_ap2_p2.presentation.repositoryy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.util.copy
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.freimyhidalgo_ap2_p2.data.remote.repositorys.Resource
import edu.ucne.freimyhidalgo_ap2_p2.data.repository.RepositoryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositoryViewModel @Inject constructor(
    private val repositoryRepository: RepositoryRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(RepositoryUIState())
    val uiState = _uiState.asStateFlow()

    private val _load = MutableStateFlow(false)
    val load: StateFlow<Boolean> = _load

    init {
        getRepository("Eminence")
    }


    fun getRepository(username:String) {
        viewModelScope.launch {
            repositoryRepository.getRetenciones(username).collectLatest { getting ->
                when (getting) {
                    is Resource.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }
                    is Resource.Success -> {
                        _uiState.update {
                            it.copy(
                                repository = getting.data ?: emptyList(),
                                isLoading = false
                            )
                        }
                    }
                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false
                            )
                        }
                    }
                }
            }
        }
    }
}



