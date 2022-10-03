package com.kingtech.cryptography.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kingtech.cryptography.databinding.FragmentHashBinding
import org.bouncycastle.jce.provider.BouncyCastleProvider
import java.io.UnsupportedEncodingException
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import java.security.Security
import javax.crypto.*
import javax.crypto.spec.SecretKeySpec


class HashFragment : Fragment() {

    private lateinit var binding: FragmentHashBinding

    private val secretKey = "662ede836988e52fb6d087d9d85605e0"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHashBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var input = binding.etInput.text.toString()


        binding.btnEncrypt.setOnClickListener {
            binding.tvEncrypted.text = encrypt(input, secretKey).toString()
        }

        binding.btnDecrypt.setOnClickListener {
            binding.tvDecrypted.text = decryptWithAES(secretKey, binding.tvEncrypted.text.toString())
        }
    }

    private fun encrypt(strToEncrypt: String, secret_key: String): String? {
        Security.addProvider(BouncyCastleProvider())
        var keyBytes: ByteArray

        try {
            keyBytes = secret_key.toByteArray(charset("UTF8"))
            val skey = SecretKeySpec(keyBytes, "AES")
            val input = strToEncrypt.toByteArray(charset("UTF8"))

            synchronized(Cipher::class.java) {
                val cipher = Cipher.getInstance("AES/ECB/PKCS7Padding")
                cipher.init(Cipher.ENCRYPT_MODE, skey)

                val cipherText = ByteArray(cipher.getOutputSize(input.size))
                var ctLength = cipher.update(
                    input, 0, input.size,
                    cipherText, 0
                )
                ctLength += cipher.doFinal(cipherText, ctLength)
                return String(
                    org.bouncycastle.util.encoders.Base64.encode(cipherText)
                )
            }
        } catch (uee: UnsupportedEncodingException) {
            uee.printStackTrace()
        } catch (ibse: IllegalBlockSizeException) {
            ibse.printStackTrace()
        } catch (bpe: BadPaddingException) {
            bpe.printStackTrace()
        } catch (ike: InvalidKeyException) {
            ike.printStackTrace()
        } catch (nspe: NoSuchPaddingException) {
            nspe.printStackTrace()
        } catch (nsae: NoSuchAlgorithmException) {
            nsae.printStackTrace()
        } catch (e: ShortBufferException) {
            e.printStackTrace()
        }

        return null
    }

    private fun decryptWithAES(key: String, strToDecrypt: String?): String? {
        Security.addProvider(BouncyCastleProvider())
        val keyBytes: ByteArray

        try {
            keyBytes = key.toByteArray(charset("UTF8"))
            val skey = SecretKeySpec(keyBytes, "AES")
            val input = org.bouncycastle.util.encoders.Base64
                .decode(strToDecrypt?.trim { it <= ' ' }?.toByteArray(charset("UTF8")))

            synchronized(Cipher::class.java) {
                val cipher = Cipher.getInstance("AES/ECB/PKCS7Padding")
                cipher.init(Cipher.DECRYPT_MODE, skey)

                val plainText = ByteArray(cipher.getOutputSize(input.size))
                var ptLength = cipher.update(input, 0, input.size, plainText, 0)
                ptLength += cipher.doFinal(plainText, ptLength)
                val decryptedString = String(plainText)
                return decryptedString.trim { it <= ' ' }
            }
        } catch (uee: UnsupportedEncodingException) {
            uee.printStackTrace()
        } catch (ibse: IllegalBlockSizeException) {
            ibse.printStackTrace()
        } catch (bpe: BadPaddingException) {
            bpe.printStackTrace()
        } catch (ike: InvalidKeyException) {
            ike.printStackTrace()
        } catch (nspe: NoSuchPaddingException) {
            nspe.printStackTrace()
        } catch (nsae: NoSuchAlgorithmException) {
            nsae.printStackTrace()
        } catch (e: ShortBufferException) {
            e.printStackTrace()
        }

        return null
    }



}