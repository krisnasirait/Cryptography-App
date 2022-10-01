package com.kingtech.cryptography

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kingtech.cryptography.databinding.FragmentCaesarCipherBinding

class CaesarCipherFragment : Fragment() {

    private lateinit var binding: FragmentCaesarCipherBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCaesarCipherBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}