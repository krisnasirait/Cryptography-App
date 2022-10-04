package com.kingtech.cryptography.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import br.com.amorim.crypto.crypto.AESCrypto
import br.com.amorim.crypto.crypto.RSACrypto
import com.kingtech.cryptography.R
import com.kingtech.cryptography.databinding.FragmentSuperAlgorithmBinding

class SuperAlgorithmFragment : Fragment() {

    private lateinit var binding: FragmentSuperAlgorithmBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSuperAlgorithmBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rsaCrypto = context?.let { RSACrypto(it) }
        val aesCrypto = AESCrypto()

        val textToEncrypt = view.findViewById<TextView>(R.id.etInput)

        binding.btnEncrypt.setOnClickListener {
            val rsaChiper = rsaCrypto?.encrypt(textToEncrypt.text.toString())
            aesCrypto.encrypt(rsaChiper.toString()).let {
                binding.etEncrypted.text = it
            }
        }
    }
}