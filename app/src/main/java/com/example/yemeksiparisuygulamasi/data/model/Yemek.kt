package com.example.yemeksiparisuygulamasi.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class Yemek(
    @SerializedName("yemek_id") val yemek_id: String = "",
    @SerializedName("yemek_adi") val yemek_adi: String = "",
    @SerializedName("yemek_resim_adi") val yemek_resim_adi: String = "",
    @SerializedName("yemek_fiyat") val yemek_fiyat: String = "",
    @SerializedName("yemek_siparis_adet") val yemek_siparis_adet: String = "1",
    @SerializedName("sepet_yemek_id") val sepet_yemek_id: String = ""
) : Parcelable
