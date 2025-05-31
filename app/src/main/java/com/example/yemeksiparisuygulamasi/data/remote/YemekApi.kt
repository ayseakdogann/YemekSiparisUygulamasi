package com.example.yemeksiparisuygulamasi.remote

import com.example.yemeksiparisuygulamasi.data.model.APIResponse
import com.example.yemeksiparisuygulamasi.data.model.SepetYemeklerResponse
import com.example.yemeksiparisuygulamasi.data.model.YemeklerResponse
import retrofit2.Response
import retrofit2.http.*

interface YemekApi {

    @GET("yemekler/tumYemekleriGetir.php")
    suspend fun tumYemekleriGetir(): Response<YemeklerResponse>

    @FormUrlEncoded
    @POST("yemekler/sepeteYemekEkle.php")
    suspend fun sepeteYemekEkle(
        @Field("yemek_adi") yemekAdi: String,
        @Field("yemek_resim_adi") resimAdi: String,
        @Field("yemek_fiyat") fiyat: Int,
        @Field("yemek_siparis_adet") adet: Int,
        @Field("kullanici_adi") kullaniciAdi: String
    ): Response<APIResponse>

    @FormUrlEncoded
    @POST("yemekler/sepettekiYemekleriGetir.php")
    suspend fun sepettekiYemekleriGetir(
        @Field("kullanici_adi") kullaniciAdi: String
    ): Response<SepetYemeklerResponse>

    @FormUrlEncoded
    @POST("yemekler/sepettenYemekSil.php")
    suspend fun sepettenYemekSil(
        @Field("sepet_yemek_id") sepetYemekId: Int,
        @Field("kullanici_adi") kullaniciAdi: String
    ): Response<Unit>
}
