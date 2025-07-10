package edu.ucne.freimyhidalgo_ap2_p2.presentation.colaboradores

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.freimyhidalgo_ap2_p2.data.remote.dto.ColaboradorDTO
import edu.ucne.freimyhidalgo_ap2_p2.data.remote.dto.RepositoryDTO
import edu.ucne.freimyhidalgo_ap2_p2.data.remote.repositorys.Resource
import edu.ucne.freimyhidalgo_ap2_p2.data.repository.RepositoryRepository
import edu.ucne.freimyhidalgo_ap2_p2.presentation.repositoryy.RepositoryEvent
import edu.ucne.freimyhidalgo_ap2_p2.presentation.repositoryy.RepositoryUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log


@HiltViewModel
class ColaboradorViewModel @Inject constructor(
    private val colaboradorRepository: RepositoryRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ColaboradorUiState())
    val uiState = _uiState.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _searchResults = MutableStateFlow<List<ColaboradorDTO>>(emptyList())
    val searchResults: StateFlow<List<ColaboradorDTO>> = _searchResults.asStateFlow()


    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun OnEvent(event: ColaboradorEvent){
        when(event){
            is ColaboradorEvent.GetContributors -> getContributor(event.repoPath)
        }
    }


    fun getContributor(repoPath: String) {
        val parts = repoPath.split("/")
        if (parts.size < 2) {
            _uiState.update {
                it.copy(errorMessage = "Ruta del repositorio inválida: $repoPath")
            }
            return
        }

        val (owner, repo) = parts
        viewModelScope.launch {
            colaboradorRepository.getContributors(owner, repo).collectLatest { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }
                    is Resource.Success -> {
                        _uiState.update {
                            it.copy(
                                colaboradors = resource.data ?: emptyList(),
                                isLoading = false,
                                errorMessage = null
                            )
                        }
                    }
                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                errorMessage = resource.message
                            )
                        }
                    }
                }
            }
        }
    }


    fun on_SearchQuery(query: String) {
        _searchQuery.value = query
    }


}








