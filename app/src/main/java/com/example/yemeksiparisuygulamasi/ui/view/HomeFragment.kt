package com.example.yemeksiparisuygulamasi.ui.view
import androidx.core.widget.doOnTextChanged

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
import com.example.yemeksiparisuygulamasi.data.model.Yemek
import com.example.yemeksiparisuygulamasi.ui.adapter.YemekAdapter
import com.example.yemeksiparisuygulamasi.viewmodel.HomeViewModel
import com.example.yemeksiparisuygulamasi.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HomeViewModel
    private lateinit var yemekAdapter: YemekAdapter
    private var tumYemekler: List<Yemek> = emptyList() // Boş liste ile başla

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val args: HomeFragmentArgs by navArgs()
        val kullaniciAdi = args.kullaniciAdi

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        viewModel.yemekleriYukle()
        viewModel.yemekListesi.observe(viewLifecycleOwner) { yemekler ->
            yemekler?.let {
                tumYemekler = it
                yemekAdapter = YemekAdapter(it) { secilenYemek ->
                    val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(secilenYemek)
                    findNavController().navigate(action)
                }
                binding.rvYemekler.adapter = yemekAdapter
            }
        }

        // Arama kutusu filtreleme
        binding.searchEditText.doOnTextChanged { text, _, _, _ ->
            val query = text.toString().lowercase().trim()
            val filtrelenmisListe = tumYemekler.filter { yemek ->
                yemek.yemek_adi.lowercase().contains(query)
            }
            yemekAdapter.updateList(filtrelenmisListe)
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
