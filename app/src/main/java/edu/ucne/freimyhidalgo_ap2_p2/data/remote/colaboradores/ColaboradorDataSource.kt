package edu.ucne.freimyhidalgo_ap2_p2.data.remote.colaboradores

import edu.ucne.freimyhidalgo_ap2_p2.data.remote.dto.ColaboradorDTO
import edu.ucne.freimyhidalgo_ap2_p2.data.remote.dto.RepositoryDTO
import edu.ucne.freimyhidalgo_ap2_p2.data.remote.repositorys.RepositoryManajerApi
import javax.inject.Inject



class ColaboradorDataSource  @Inject constructor(
    private val repositoryManagerApi: ColaboradoresManagerApi
) {
    suspend fun getColaborador(repo: String, owner:String) = repositoryManagerApi.listColab(repo, owner)



}


