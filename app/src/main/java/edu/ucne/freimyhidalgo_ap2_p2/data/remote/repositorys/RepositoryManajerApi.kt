package edu.ucne.freimyhidalgo_ap2_p2.data.remote.repositorys

import edu.ucne.freimyhidalgo_ap2_p2.data.remote.dto.RepositoryDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface RepositoryManajerApi {

    @GET("users/{username}/repos")
    suspend fun listRepos(@Path("username") username: String): List<RepositoryDTO>

    @POST("users/repos")
    suspend fun createRepo(
        @Body request: RepositoryDTO,
    ): RepositoryDTO


    @PATCH("repos/{username}/{repo}")
    suspend fun updateRepo(
        @Path("username") username: String,
        @Body request: RepositoryDTO,
    ): RepositoryDTO

    @DELETE("repos/{username}/{repo}")
    suspend fun deleteRepo(
        @Path("username") username: String,
        @Path("repo") repo: String,
    ): Response<Unit>


}




