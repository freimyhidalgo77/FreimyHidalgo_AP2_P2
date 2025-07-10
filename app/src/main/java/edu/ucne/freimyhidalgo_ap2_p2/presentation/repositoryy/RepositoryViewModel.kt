package edu.ucne.freimyhidalgo_ap2_p2.presentation.repositoryy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.util.copy
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.freimyhidalgo_ap2_p2.data.remote.dto.RepositoryDTO
import edu.ucne.freimyhidalgo_ap2_p2.data.remote.repositorys.Resource
import edu.ucne.freimyhidalgo_ap2_p2.data.repository.RepositoryRepository
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

@HiltViewModel
class RepositoryViewModel @Inject constructor(
    private val repositoryRepository: RepositoryRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(RepositoryUIState())
    val uiState = _uiState.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _searchResults = MutableStateFlow<List<RepositoryDTO>>(emptyList())
    val searchResults: StateFlow<List<RepositoryDTO>> = _searchResults.asStateFlow()

    init {
        getRepository("enelramon")
        viewModelScope.launch {
            _searchQuery
                .debounce(600)
                .distinctUntilChanged()
                .mapLatest { query ->
                    filtrarRepositorios(query)
                }
                .collectLatest { filtrado ->
                    _searchResults.value = filtrado
                }
        }
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }


    private fun filtrarRepositorios(query: String): List<RepositoryDTO> {
        return if (query.isBlank()) {
            _uiState.value.repository
        } else {
            _uiState.value.repository.filter {
                it.name.contains(query, ignoreCase = true) ||
                        (it.description?.contains(query, ignoreCase = true) ?: false)
            }
        }
    }

    fun OnEvent(event: RepositoryEvent){
        when(event){
            RepositoryEvent.DeleteRepositories -> TODO()
            RepositoryEvent.GetRepositories -> getRepository("enelramon")
            RepositoryEvent.PostRepository -> TODO()
            RepositoryEvent.PutRepositories -> TODO()
        }
    }


    fun getRepository(username:String) {
        viewModelScope.launch {
            repositoryRepository.getRepository(username).collectLatest { getting ->
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
                        _searchResults.value = filtrarRepositorios(_searchQuery.value)
                    }
                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false
                            )
                        }
                        _searchResults.value = filtrarRepositorios(_searchQuery.value)

                    }
                }
            }
        }
    }







    fun on_SearchQuery(query: String) {
        _searchQuery.value = query
    }

    private fun filterRepos(query: String): List<RepositoryDTO> {
        return if (query.isBlank()) {
            _uiState.value.repository
        } else {
            _uiState.value.repository.filter {
                it.name.contains(query, ignoreCase = true) ||
                        (it.description?.contains(query, ignoreCase = true) ?: false)
            }
        }
    }



    private fun onChangeName(name: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(name = name)
            }
        }
    }

    private fun htmlUrlOnChange(url: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(html_url = url)
            }
        }
    }




    private fun loadRepo(name: String) {
        viewModelScope.launch {
            val repo = _uiState.value.repository.find { it.name == name }
            _uiState.update {
                it.copy(
                    name = repo?.name ?: "",
                    descripcion = repo?.description ?: "",
                    html_url = repo?.htmlUrl ?: ""
                )
            }
        }
    }


}





