package edu.ucne.freimyhidalgo_ap2_p2.presentation.colaboradores

import edu.ucne.freimyhidalgo_ap2_p2.data.remote.dto.ColaboradorDTO
import edu.ucne.freimyhidalgo_ap2_p2.data.remote.dto.RepositoryDTO


data class ColaboradorUiState(
    val login:String = "",
    val Id: Int = 0,
    val avatar_url:String = "",
    val isLoading: Boolean = false,
    val colaboradors: List<ColaboradorDTO> = emptyList(),
    val errorMessage: String? = null,

    )