package edu.ucne.freimyhidalgo_ap2_p2.data.remote.colaboradores

import edu.ucne.freimyhidalgo_ap2_p2.data.remote.dto.ColaboradorDTO
import edu.ucne.freimyhidalgo_ap2_p2.data.remote.dto.RepositoryDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path


interface ColaboradoresManagerApi {

    @GET("repos/{username}/{repo}/collaborators")
    suspend fun listColab(@Path("collaborators") colaborador: String): List<ColaboradorDTO>

    @POST("repos/{username}/{repo}/collaborators")
    suspend fun createColab(
        @Body request: ColaboradorDTO,
    ): ColaboradorDTO

    @PATCH("repos/{username}/{repo}/collaborators")
    suspend fun updateColab(
        @Path("Colaborador") colaborador: String,
        @Body request: ColaboradorDTO,
    ): ColaboradorDTO

    @DELETE("repos/{username}/{repo}/collaborators")
    suspend fun deleteRepo(
        @Path("Colaborador") colaborador: String,
    ): Response<Unit>




}