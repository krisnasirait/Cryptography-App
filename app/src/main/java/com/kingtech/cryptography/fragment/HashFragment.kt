package com.kingtech.cryptography.fragment

import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.amorim.crypto.keys.AesKey
import com.kingtech.cryptography.databinding.FragmentHashBinding
import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec


class HashFragment : Fragment() {

    private lateinit var binding: FragmentHashBinding

    private val cipher = Cipher.getInstance(TRANSFORMATION_SYMMETRIC)
    private val aesKey = AesKey()
    private var iv: ByteArray? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHashBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val input = binding.etInput.text.toString()


        binding.btnEncrypt.setOnClickListener {
            binding.tvEncrypted.text = encrypt(input)
        }

        binding.btnDecrypt.setOnClickListener {
            binding.tvDecrypted.text = decrypt(encrypt(input))
        }
    }

    private fun encrypt(value: String): String {
        cipher.init(Cipher.ENCRYPT_MODE, aesKey.getAesKey())
        val textToEncrypt = value.toByteArray()
        val encryptedByteArray = cipher.doFinal(textToEncrypt)
        iv = cipher.iv
        return Base64.encodeToString(encryptedByteArray, Base64.DEFAULT)
    }

    private fun decrypt(value: String): String {
        cipher.init(Cipher.DECRYPT_MODE, aesKey.getAesKey(), IvParameterSpec(iv))
        val encryptedData = Base64.decode(value, Base64.DEFAULT)
        val decoded = cipher.doFinal(encryptedData)
        return String(decoded)
    }

    companion object {
        private const val TRANSFORMATION_SYMMETRIC = "AES/CBC/PKCS7Padding"
    }
}