package com.tif.testoonjo.model

data class MainResponse(
    val `data`: List<Data>
) {
    data class Data(
        val avatar: String,
        val email: String,
        val first_name: String,
        val gender: String,
        val id: Int,
        val last_name: String
    )
}