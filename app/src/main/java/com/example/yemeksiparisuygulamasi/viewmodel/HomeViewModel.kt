package com.example.yemeksiparisuygulamasi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yemeksiparisuygulamasi.data.model.Yemek
import com.example.yemeksiparisuygulamasi.data.repository.YemekRepository
import kotlinx.coroutines.launch
class HomeViewModel : ViewModel() {

    private val repository = YemekRepository()

    val yemekListesi = MutableLiveData<List<Yemek>>()
    val hataMesaji = MutableLiveData<String>()

    fun yemekleriYukle() {
        viewModelScope.launch {
            try {
                val liste = repository.tumYemekleriGetir()
                if (liste != null) {
                    yemekListesi.value = liste
                } else {
                    hataMesaji.value = "Yemekler yüklenemedi"
                }
            } catch (e: Exception) {
                hataMesaji.value = "Hata: ${e.localizedMessage}"
            }
        }
    }
}

