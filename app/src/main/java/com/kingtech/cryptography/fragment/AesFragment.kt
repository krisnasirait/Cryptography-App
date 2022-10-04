package com.kingtech.cryptography.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import br.com.amorim.crypto.crypto.AESCrypto
import com.kingtech.cryptography.R
import com.kingtech.cryptography.databinding.FragmentAesBinding
import java.io.IOException


class AesFragment : Fragment() {

    private lateinit var binding: FragmentAesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val aesCrypto = AESCrypto()

        val textToEncrypt = view.findViewById<TextView>(R.id.etInput)
        val textToDecrypt = view.findViewById<TextView>(R.id.etEncrypted)
        val decryptResult = view.findViewById<TextView>(R.id.tvResult)

        binding.btnEncrypt.setOnClickListener {
            aesCrypto.encrypt(textToEncrypt.text.toString()).let {
                textToDecrypt.text = it
            }
        }

        binding.btnDecrypt.setOnClickListener {
            try {
                aesCrypto.decrypt(textToDecrypt.text.toString()).let {
                    decryptResult.text = it
                }
            } catch (error: IOException) {
                Toast.makeText(activity, "${error.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}