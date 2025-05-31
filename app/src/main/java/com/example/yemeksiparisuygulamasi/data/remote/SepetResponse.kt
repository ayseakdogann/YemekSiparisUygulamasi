package com.example.yemeksiparisuygulamasi.data.model

import com.google.gson.annotations.SerializedName

data class SepetResponse(
    @SerializedName("sepet_yemekler") val sepetYemekler: List<Yemek>,
    @SerializedName("success") val success: Int
)
