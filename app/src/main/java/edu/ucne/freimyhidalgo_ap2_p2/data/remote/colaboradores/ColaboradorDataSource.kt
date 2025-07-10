package edu.ucne.freimyhidalgo_ap2_p2.data.remote.colaboradores

import edu.ucne.freimyhidalgo_ap2_p2.data.remote.dto.ColaboradorDTO
import edu.ucne.freimyhidalgo_ap2_p2.data.remote.dto.RepositoryDTO
import edu.ucne.freimyhidalgo_ap2_p2.data.remote.repositorys.RepositoryManajerApi
import javax.inject.Inject



class ColaboradorDataSource  @Inject constructor(
    private val repositoryManagerApi: ColaboradoresManagerApi
) {
    suspend fun getColaborador(colaborador: String) = repositoryManagerApi.listColab(colaborador)

    suspend fun postColaborador(colaboradorDTO: ColaboradorDTO) =
        repositoryManagerApi.createColab(colaboradorDTO)

    suspend fun putColaborador(colaborador: String, colaboradorDTO: ColaboradorDTO) =
        repositoryManagerApi.updateColab(colaborador, colaboradorDTO)

    suspend fun deleteColaborador(colaborador: String) =
        repositoryManagerApi.deleteRepo(colaborador)

}


