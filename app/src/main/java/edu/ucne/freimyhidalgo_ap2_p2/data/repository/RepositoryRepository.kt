package edu.ucne.freimyhidalgo_ap2_p2.data.repository

import android.util.Log
import edu.ucne.freimyhidalgo_ap2_p2.data.remote.dto.RepositoryDTO
import edu.ucne.freimyhidalgo_ap2_p2.data.remote.repositorys.RepositoryDataSource
import edu.ucne.freimyhidalgo_ap2_p2.data.remote.repositorys.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject


class RepositoryRepository @Inject constructor(
    private val dataSource: RepositoryDataSource
){
    fun getRepository(username:String): Flow<Resource<List<RepositoryDTO>>> = flow {
        try{
            emit(Resource.Loading())
            val repository = dataSource.getRepository(username)
            emit(Resource.Success(repository))
        }catch (e: HttpException){
            val errorMessage = e.response()?.errorBody()?.string() ?: e.message()
            Log.e("RepositoryRepository", "HttpException: $errorMessage")
            emit(Resource.Error("Error de conexion $errorMessage"))
        }catch (e: Exception){
            Log.e("RepositoryRepository", "Exception: ${e.message}")
            emit(Resource.Error("Error: ${e.message}"))

        }
    }


    suspend fun createRepository(
        repositoryDTO: RepositoryDTO
    ): Resource<RepositoryDTO> = try {
        val result = dataSource.postRepository(repositoryDTO)
        Resource.Success(result)
    } catch (e: Exception) {
        Resource.Error(e.message ?: "Error al crear el repositorio")
    }

    suspend fun updateRepository(
        username: String,
        repositoryDTO: RepositoryDTO,
    ): Resource<RepositoryDTO> = try {
        val result = dataSource.putRepository(username,repositoryDTO)
        Resource.Success(result)
    } catch (e: Exception) {
        Resource.Error(e.message ?: "Error al actualizar el repositorio")
    }

    suspend fun delete(username:String, repos:String)  = dataSource.deleteRepository(username, repos)


    }

