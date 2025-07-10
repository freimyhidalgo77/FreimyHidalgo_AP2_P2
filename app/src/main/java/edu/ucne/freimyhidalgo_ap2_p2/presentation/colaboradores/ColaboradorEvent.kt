package edu.ucne.freimyhidalgo_ap2_p2.presentation.colaboradores

import edu.ucne.freimyhidalgo_ap2_p2.presentation.repositoryy.RepositoryEvent


interface ColaboradorEvent {

    data object GetColaboradores: ColaboradorEvent
    data object PostColaboradores: ColaboradorEvent
    data object PutColaboradores: ColaboradorEvent
    data object DeleteColaboradores: ColaboradorEvent
}