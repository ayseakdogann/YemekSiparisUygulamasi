package com.example.yemeksiparisuygulamasi.data.remote

import com.example.yemeksiparisuygulamasi.data.model.Yemek
import com.google.gson.annotations.SerializedName

data class YemeklerResponse(
    val yemekler: List<Yemek>,
    val success: Int
)

