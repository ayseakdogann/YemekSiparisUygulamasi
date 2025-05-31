package com.example.yemeksiparisuygulamasi.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yemeksiparisuygulamasi.data.model.Yemek
import com.example.yemeksiparisuygulamasi.databinding.SepetItemBinding

class CartAdapter(private val sepetListesi: List<Yemek>) :
    RecyclerView.Adapter<CartAdapter.SepetViewHolder>() {

    inner class SepetViewHolder(val binding: SepetItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SepetViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = SepetItemBinding.inflate(layoutInflater, parent, false)
        return SepetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SepetViewHolder, position: Int) {
        val yemek = sepetListesi[position]

        holder.binding.txtSepetYemekAdi.text = yemek.yemek_adi
        holder.binding.txtSepetAdet.text = "Adet: ${yemek.yemek_siparis_adet}"
        holder.binding.txtSepetFiyat.text =
            "Fiyat: ${yemek.yemek_fiyat.toInt() * yemek.yemek_siparis_adet.toInt()} ₺"

        val resimUrl = "http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}"
        Glide.with(holder.itemView.context)
            .load(resimUrl)
            .into(holder.binding.imgSepetYemek)

    }

    override fun getItemCount(): Int = sepetListesi.size
}
