package edu.ucne.freimyhidalgo_ap2_p2.presentation.repositoryy



interface RepositoryEvent {
    data object GetRepositories: RepositoryEvent
    data object PostRepository: RepositoryEvent
    data object PutRepositories: RepositoryEvent
    data object DeleteRepositories: RepositoryEvent
}