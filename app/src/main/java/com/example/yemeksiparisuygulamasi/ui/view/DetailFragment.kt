package com.example.yemeksiparisuygulamasi.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.yemeksiparisuygulamasi.databinding.FragmentDetailBinding
import com.example.yemeksiparisuygulamasi.viewmodel.CartViewModel

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val args: DetailFragmentArgs by navArgs()
    private lateinit var viewModel: CartViewModel

    private var adet = 1
    private var birimFiyat = 0
    private val kullaniciAdi = "kasim_adalan"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[CartViewModel::class.java]

        val yemek = args.yemek
        Log.d("YemekLog", "Gelen yemek: ${args.yemek}")

        birimFiyat = yemek.yemek_fiyat.toIntOrNull() ?: 0

        binding.apply {
            txtYemekAdiDetay.text = yemek.yemek_adi
            txtYemekFiyatDetay.text = "${yemek.yemek_fiyat} ₺"
            txtAdet.text = adet.toString()
            val imageUrl = "http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}"
            Glide.with(requireContext()).load(imageUrl).into(imgYemekDetay)

            btnArttir.setOnClickListener {
                adet++
                txtAdet.text = adet.toString()
                guncelleToplam()
            }

            btnAzalt.setOnClickListener {
                if (adet > 1) {
                    adet--
                    txtAdet.text = adet.toString()
                    guncelleToplam()
                }
            }

            btnSepeteEkle.setOnClickListener {
                viewModel.sepeteYemekEkle(
                    yemek.yemek_adi,
                    yemek.yemek_resim_adi,
                    birimFiyat,
                    adet,
                    kullaniciAdi
                )
                Toast.makeText(requireContext(), "Sepete eklendi", Toast.LENGTH_SHORT).show()
                findNavController().navigate(DetailFragmentDirections.actionDetailToCart())
            }
        }

        guncelleToplam()
        return binding.root
    }

    private fun guncelleToplam() {
        val toplam = adet * birimFiyat
        binding.txtToplamFiyat.text = "Toplam: ₺$toplam"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

