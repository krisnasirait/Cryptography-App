package com.kingtech.cryptography.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.amorim.crypto.crypto.RSACrypto
import com.kingtech.cryptography.R
import com.kingtech.cryptography.databinding.FragmentRsaBinding

class RsaFragment : Fragment() {

    private lateinit var binding: FragmentRsaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRsaBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rsaCrypto = context?.let { RSACrypto(it) }

        val textToEncrypt = view.findViewById<TextView>(R.id.etInput)
        val textToDecrypt = view.findViewById<TextView>(R.id.etEncrypted)
        val decryptResult = view.findViewById<TextView>(R.id.tvResult)

        binding.btnEncrypt.setOnClickListener {
            rsaCrypto?.encrypt(textToEncrypt.text.toString()).let {
                textToDecrypt.text = it
            }
        }

        binding.btnDecrypt.setOnClickListener {
            rsaCrypto?.decrypt(textToDecrypt.text.toString()).let {
                decryptResult.text = it
            }
        }
    }
}