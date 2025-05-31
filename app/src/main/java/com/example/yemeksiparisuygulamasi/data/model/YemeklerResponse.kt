package com.example.yemeksiparisuygulamasi.data.model

import com.google.gson.annotations.SerializedName

data class YemeklerResponse(
    @SerializedName("yemekler") val yemekler: List<Yemek>?, // Ana sayfa için
    @SerializedName("sepet_yemekler") val sepetYemekler: List<Yemek>?, // Sepet için
    @SerializedName("success") val success: Int
)

