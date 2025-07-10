package edu.ucne.freimyhidalgo_ap2_p2.data.repository

import android.util.Log
import edu.ucne.freimyhidalgo_ap2_p2.data.remote.dto.ColaboradorDTO
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
    suspend fun getRepositories(username: String): Flow<Resource<List<RepositoryDTO>>> = flow {
        emit(Resource.Loading())
        try {
            val repositories = dataSource.getRepository(username)
            emit(Resource.Success(repositories))
        } catch (e: Exception) {
            emit(Resource.Error("Error al obtener los repositorios: ${e.message}"))
        }
    }

    suspend fun getColaborators(owner: String, repo: String): Flow<Resource<List<ColaboradorDTO>>> = flow {
        emit(Resource.Loading())
        try {
            val contributors = dataSource.getConlaborators(owner, repo)
            emit(Resource.Success(contributors))
        } catch (e: Exception) {
            emit(Resource.Error("Error al obtener los colaboradores: ${e.message}"))
        }
        }

    }

