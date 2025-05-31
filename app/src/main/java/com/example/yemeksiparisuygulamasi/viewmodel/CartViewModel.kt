package com.example.yemeksiparisuygulamasi.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yemeksiparisuygulamasi.data.model.Yemek
import com.example.yemeksiparisuygulamasi.data.repository.YemekRepository
import kotlinx.coroutines.launch

class CartViewModel : ViewModel() {

    private val repository = YemekRepository()
    val sepetYemekleri = MutableLiveData<List<Yemek>>()
    val toplamTutar = MutableLiveData<Int>()
    val hataMesaji = MutableLiveData<String>()

    fun sepettekiYemekleriYukle(kullaniciAdi: String) {
        viewModelScope.launch {
            try {
                val response = repository.sepettekiYemekleriGetir(kullaniciAdi)
                if (response.isSuccessful) {
                    val liste = response.body()?.sepetYemekler ?: emptyList()
                    sepetYemekleri.value = liste
                    hesaplaToplamTutar(liste)
                } else {
                    hataMesaji.value = "Sepet yüklenemedi: ${response.code()}"
                }
            } catch (e: Exception) {
                hataMesaji.value = "Hata: ${e.localizedMessage}"
            }
        }
    }

    fun sepeteYemekEkle(
        yemekAdi: String,
        resimAdi: String,
        fiyat: Int,
        adet: Int,
        kullaniciAdi: String
    ) {
        viewModelScope.launch {
            try {
                repository.sepeteYemekEkle(yemekAdi, resimAdi, fiyat, adet, kullaniciAdi)
                sepettekiYemekleriYukle(kullaniciAdi)
            } catch (e: Exception) {
                hataMesaji.value = "Sepete ekleme hatası: ${e.localizedMessage}"
            }
        }
    }

    fun sepettenYemekSil(sepetYemekId: Int, kullaniciAdi: String) {
        viewModelScope.launch {
            try {
                val response = repository.sepettenYemekSil(sepetYemekId, kullaniciAdi)
                if (response.isSuccessful) {
                    sepettekiYemekleriYukle(kullaniciAdi) // Listeyi güncelle
                } else {
                    hataMesaji.value = "Silme başarısız: ${response.code()}"
                }
            } catch (e: Exception) {
                hataMesaji.value = "Silme hatası: ${e.localizedMessage}"
            }
        }
    }

    private fun hesaplaToplamTutar(liste: List<Yemek>) {
        val toplam = liste.sumOf { it.yemek_fiyat.toInt() * it.yemek_siparis_adet.toInt() }
        toplamTutar.value = toplam
    }
}
