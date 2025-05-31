package com.example.yemeksiparisuygulamasi.data.repository

import com.example.yemeksiparisuygulamasi.remote.RetrofitInstance
import com.example.yemeksiparisuygulamasi.data.model.SepetResponse
import com.example.yemeksiparisuygulamasi.data.model.SepetYemeklerResponse
import com.example.yemeksiparisuygulamasi.data.model.Yemek
import com.example.yemeksiparisuygulamasi.data.model.YemeklerResponse
import com.example.yemeksiparisuygulamasi.remote.YemekApi
import retrofit2.Response



class YemekRepository {

    private val api: YemekApi = RetrofitInstance.api

    suspend fun sepettekiYemekleriGetir(kullaniciAdi: String): Response<SepetYemeklerResponse> {
        return api.sepettekiYemekleriGetir(kullaniciAdi)
    }
    // YemekRepository.kt
    suspend fun tumYemekleriGetir(): List<Yemek>? {
        val response = api.tumYemekleriGetir()
        return if (response.isSuccessful) {
            response.body()?.yemekler
        } else {
            null
        }
    }



    suspend fun sepeteYemekEkle(
        yemekAdi: String,
        yemekResimAdi: String,
        yemekFiyat: Int,
        yemekAdet: Int,
        kullaniciAdi: String
    ) {
        api.sepeteYemekEkle(yemekAdi, yemekResimAdi, yemekFiyat, yemekAdet, kullaniciAdi)
    }

    suspend fun sepettenYemekSil(sepetYemekId: Int, kullaniciAdi: String): Response<Unit> {
        return api.sepettenYemekSil(sepetYemekId, kullaniciAdi)
    }

}
