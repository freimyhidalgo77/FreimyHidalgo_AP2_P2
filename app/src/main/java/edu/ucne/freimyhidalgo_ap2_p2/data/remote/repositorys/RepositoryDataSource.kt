package edu.ucne.freimyhidalgo_ap2_p2.data.remote.repositorys


import edu.ucne.freimyhidalgo_ap2_p2.data.remote.dto.RepositoryDTO
import javax.inject.Inject

class RepositoryDataSource  @Inject constructor(
    private val repositoryManagerApi: RepositoryManajerApi,

) {
    suspend fun getRepository(username: String) = repositoryManagerApi.listRepos(username)

    suspend fun getConlaborators(owner: String, repo: String) =
        repositoryManagerApi.listColaborators(owner, repo)

}