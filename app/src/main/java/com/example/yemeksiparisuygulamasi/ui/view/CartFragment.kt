package com.example.yemeksiparisuygulamasi.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.yemeksiparisuygulamasi.databinding.FragmentCartBinding
import com.example.yemeksiparisuygulamasi.ui.adapter.CartAdapter
import com.example.yemeksiparisuygulamasi.viewmodel.CartViewModel

class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CartViewModel
    private val kullaniciAdi = "kasim_adalan"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[CartViewModel::class.java]

        viewModel.sepettekiYemekleriYukle(kullaniciAdi)

        viewModel.sepetYemekleri.observe(viewLifecycleOwner) { sepetListesi ->
            binding.rvSepet.adapter = CartAdapter(sepetListesi)
            val toplamTutar = sepetListesi.sumOf {
                (it.yemek_fiyat.toIntOrNull() ?: 0) * (it.yemek_siparis_adet.toIntOrNull() ?: 1)
            }
            binding.txtToplamTutar.text = "Toplam: ₺$toplamTutar"
        }

        viewModel.hataMesaji.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
