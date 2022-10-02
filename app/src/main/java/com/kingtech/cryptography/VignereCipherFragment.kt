package com.kingtech.cryptography

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kingtech.cryptography.databinding.FragmentCaesarCipherBinding
import com.kingtech.cryptography.databinding.FragmentVignereCipherBinding
import java.util.*


class VignereCipherFragment : Fragment() {

    private lateinit var binding: FragmentVignereCipherBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentVignereCipherBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}