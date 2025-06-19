package edu.ucne.freimyhidalgo_ap2_p2.data.remote.repositorys

sealed class Resource<R>(val data: R? = null, val message: String? = null) {

    class Success<R>(data: R): Resource<R>(data)

    class Error<R>(message: String, data: R? = null): Resource<R>(data, message)

    class Loading<R>(data: R? = null): Resource<R>(data)

}