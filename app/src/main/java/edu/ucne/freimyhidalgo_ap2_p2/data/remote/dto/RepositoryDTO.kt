package edu.ucne.freimyhidalgo_ap2_p2.data.remote.dto

import com.squareup.moshi.Json

data class RepositoryDTO(
    @Json(name = "name") val name: String,
    @Json(name = "description") val description: String?,
    @Json(name = "html_url") val htmlUrl:String
)


