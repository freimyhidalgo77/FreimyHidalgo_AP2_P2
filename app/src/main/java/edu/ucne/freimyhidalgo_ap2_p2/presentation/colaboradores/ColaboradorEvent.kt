package edu.ucne.freimyhidalgo_ap2_p2.presentation.colaboradores

import edu.ucne.freimyhidalgo_ap2_p2.presentation.repositoryy.RepositoryEvent


interface ColaboradorEvent {

    data class GetContributors(val repoPath: String) : ColaboradorEvent

}