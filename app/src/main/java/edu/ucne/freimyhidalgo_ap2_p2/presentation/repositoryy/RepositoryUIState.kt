package edu.ucne.freimyhidalgo_ap2_p2.presentation.repositoryy

import edu.ucne.freimyhidalgo_ap2_p2.data.remote.dto.RepositoryDTO
import edu.ucne.freimyhidalgo_ap2_p2.data.repository.RepositoryRepository

data class RepositoryUIState(
    val name:String = "",
    val descripcion: String = "",
    val html_url:String = "", val isLoading: Boolean = false,
    val repository: List<RepositoryDTO> = emptyList()


)