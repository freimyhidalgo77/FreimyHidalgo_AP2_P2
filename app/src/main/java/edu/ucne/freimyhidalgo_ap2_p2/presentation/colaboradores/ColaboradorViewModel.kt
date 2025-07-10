package edu.ucne.freimyhidalgo_ap2_p2.presentation.colaboradores

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.freimyhidalgo_ap2_p2.data.remote.dto.ColaboradorDTO
import edu.ucne.freimyhidalgo_ap2_p2.data.remote.dto.RepositoryDTO
import edu.ucne.freimyhidalgo_ap2_p2.data.remote.repositorys.Resource
import edu.ucne.freimyhidalgo_ap2_p2.data.repository.ColaboradorRepository
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
    private val colaboradorRepository: ColaboradorRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ColaboradorUiState())
    val uiState = _uiState.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _searchResults = MutableStateFlow<List<ColaboradorDTO>>(emptyList())
    val searchResults: StateFlow<List<ColaboradorDTO>> = _searchResults.asStateFlow()

    init {
        getColaboradores("enelramon")
        viewModelScope.launch {
            _searchQuery
                .debounce(600)
                .distinctUntilChanged()
                .mapLatest { query ->
                }
                .collectLatest { filtrado ->
                    //_searchResults.value = filtrado
                }
        }
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }


    fun OnEvent(event: RepositoryEvent){
        when(event){
            RepositoryEvent.DeleteRepositories -> TODO()
            RepositoryEvent.GetRepositories -> getColaboradores("enelramon")
            RepositoryEvent.PostRepository -> TODO()
            RepositoryEvent.PutRepositories -> TODO()
        }
    }


    fun getColaboradores(colaborador:String) {
        viewModelScope.launch {
            colaboradorRepository.getColaborador(colaborador).collectLatest { getting ->
                when (getting) {
                    is Resource.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }
                    is Resource.Success -> {
                        _uiState.update {
                            it.copy(
                                colaboradors = getting.data ?: emptyList(),
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


    fun on_SearchQuery(query: String) {
        _searchQuery.value = query
    }



    private fun loadColab(login: String) {
        viewModelScope.launch {
            val colab = _uiState.value.colaboradors.find { it.login == login }
            _uiState.update {
                it.copy(
                    login = colab?.login ?: "",
                    Id = colab?.id!!,
                    html_url = colab?.html_url?: "",
                    avatar_url = colab?.avatar_url?:""
                )
            }
        }
    }

}





