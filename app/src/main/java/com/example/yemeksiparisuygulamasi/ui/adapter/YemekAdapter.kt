package com.example.yemeksiparisuygulamasi.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yemeksiparisuygulamasi.data.model.Yemek
import com.example.yemeksiparisuygulamasi.databinding.ItemYemekBinding

class YemekAdapter(
   private var yemekListesi: List<Yemek>,
    private val onYemekClick: (Yemek) -> Unit
) : RecyclerView.Adapter<YemekAdapter.YemekViewHolder>() {

    inner class YemekViewHolder(val binding: ItemYemekBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(yemek: Yemek) {
            binding.txtYemekAdi.text = yemek.yemek_adi
            binding.txtYemekFiyat.text = "${yemek.yemek_fiyat} ₺"

            val resimUrl = "http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}"
            Glide.with(binding.root.context)
                .load(resimUrl)
                .into(binding.imgYemek)

            binding.root.setOnClickListener {
                onYemekClick(yemek)
            }
        }
    }
    fun updateList(yeniListe: List<Yemek>) {
        yemekListesi = yeniListe
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YemekViewHolder {
        val binding = ItemYemekBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return YemekViewHolder(binding)
    }

    override fun onBindViewHolder(holder: YemekViewHolder, position: Int) {
        holder.bind(yemekListesi[position])
    }

    override fun getItemCount() = yemekListesi.size
}
