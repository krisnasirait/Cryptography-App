package com.kingtech.cryptography

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kingtech.cryptography.databinding.FragmentCaesarCipherBinding


class CaesarCipherFragment : Fragment() {

    private lateinit var binding: FragmentCaesarCipherBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCaesarCipherBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnEncrypt.setOnClickListener {
            val key = binding.etShiftKey.toString().toInt()
            val encrypted = encrypt(binding.etInput.toString(), key)
            binding.tvResult.text = encrypted
        }
    }

    private fun encrypt(message:String, step:Int) : String {
        val convMessage = message.lowercase()
        val letters = "abcdefghijklmnopqrstuvwxyz"
        val translated = mutableListOf<String>()
        val len = convMessage.length - 1
        for (i in 0..len) {
            if (convMessage[i] in letters) {
                translated.add(i, letters[(letters.indexOf(convMessage[i]) + step) % 26].toString())
            } else {
                translated.add(i, convMessage[i].toString())
            }
        }
        return translated.joinToString("")
    }
}