package com.cinevault.app.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AccountDto(
    val id: Int,
    val name: String = "",
    val username: String = "",
    @SerialName("include_adult") val includeAdult: Boolean = false,
    @SerialName("iso_639_1") val language: String = "en",
    @SerialName("iso_3166_1") val country: String = "US"
)
