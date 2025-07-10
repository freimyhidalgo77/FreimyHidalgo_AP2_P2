package edu.ucne.freimyhidalgo_ap2_p2.presentation.navegation

import kotlinx.serialization.Serializable


sealed class Screen{
    @Serializable
    data class Repository(val Id: Int?): Screen()

    @Serializable
    data object RepositoryList: Screen()

    @Serializable
    data class Colaborador(val Id: Int?): Screen()

    @Serializable
    data object ColaboradorList: Screen()



}