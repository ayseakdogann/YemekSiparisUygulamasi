package com.example.yemeksiparisuygulamasi.data.remote

import com.example.yemeksiparisuygulamasi.data.model.SepetYemeklerResponse
import com.example.yemeksiparisuygulamasi.data.model.YemeklerResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Field

interface ApiService {

    @GET("yemekler/tumYemekleriGetir.php")
    suspend fun getTumYemekler(): Response<YemeklerResponse>

    @FormUrlEncoded
    @POST("yemekler/sepeteYemekEkle.php")
    suspend fun sepeteYemekEkle(
        @Field("yemek_adi") yemekAdi: String,
        @Field("yemek_resim_adi") resimAdi: String,
        @Field("yemek_fiyat") fiyat: Int,
        @Field("yemek_siparis_adet") adet: Int,
        @Field("kullanici_adi") kullaniciAdi: String
    ): Response<Any>

    @FormUrlEncoded
    @POST("yemekler/sepettekiYemekleriGetir.php")
    suspend fun getSepettekiYemekler(
        @Field("kullanici_adi") kullaniciAdi: String
    ): Response<YemeklerResponse>

    @FormUrlEncoded
    @POST("yemekler/sepettenYemekSil.php")
    suspend fun sepettenYemekSil(
        @Field("sepet_yemek_id") sepetYemekId: Int,
        @Field("kullanici_adi") kullaniciAdi: String
    ): Response<SepetYemeklerResponse>
}
