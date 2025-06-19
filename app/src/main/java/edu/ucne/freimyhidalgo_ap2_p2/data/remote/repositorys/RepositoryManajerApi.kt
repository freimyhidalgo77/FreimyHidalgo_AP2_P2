package edu.ucne.freimyhidalgo_ap2_p2.data.remote.repositorys

import edu.ucne.freimyhidalgo_ap2_p2.data.remote.dto.RepositoryDTO
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface RepositoryManajerApi {

    @GET("users/{username}/repos")
    suspend fun listRepos(@Path("username") username: String): List<RepositoryDTO>
}