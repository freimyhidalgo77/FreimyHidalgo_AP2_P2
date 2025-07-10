package edu.ucne.freimyhidalgo_ap2_p2.data.remote.dto

import com.squareup.moshi.Json

data class ColaboradorDTO(
    @Json(name = "login") val login: String,
    @Json(name = "html_url") val htmlUrl: String,
    @Json(name = "contributions") val contributions: Int
)
