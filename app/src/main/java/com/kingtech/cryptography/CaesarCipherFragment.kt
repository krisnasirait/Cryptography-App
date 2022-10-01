package com.kingtech.cryptography

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kingtech.cryptography.databinding.FragmentCaesarCipherBinding


class CaesarCipherFragment : Fragment() {

    private lateinit var binding: FragmentCaesarCipherBinding
    private var key: Int = 7
    private var letterShift = 'a' + 7

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

        binding.textKey.text = key.toString()
        binding.textShiftKey.text = "a -> $letterShift"

        val inputMsg = binding.textPlain
        transText(inputMsg.text.toString(), key)


        binding.textPlain.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                binding.textCipher.text = s
                transText(binding.textPlain.text.toString(), key)
            }
        })

        binding.buttonMinus.setOnClickListener {
            key--
            letterShift--
            update()
        }

        binding.buttonPlus.setOnClickListener {
            key++
            letterShift++
            update()
        }
    }

    private fun transText(text: String, k: Int) {
        var output = ""
        for (element in text) {
            var charIn = element
            if (charIn in 'A'..'Z') {
                charIn += k
                if (charIn < 'A') charIn += 26
                else if (charIn > 'Z') charIn -= 26
            } else if (charIn in 'a'..'z') {
                charIn += k
                if (charIn < 'a') charIn += 26
                else if (charIn > 'z') charIn -= 26
            }
            output += charIn
        }
        binding.textCipher.text = output
        binding.textCipher.paintFlags = 0
    }

    private fun updateText() {
        transText(binding.textPlain.text.toString(), key)

    }

    @SuppressLint("SetTextI18n")
    fun update() {
        when (key) {
            0 -> {
                binding.buttonMinus.isEnabled = false
            }
            25 -> {
                binding.buttonPlus.isEnabled = false
            }
            else -> {
                binding.buttonMinus.isEnabled = true
                binding.buttonPlus.isEnabled = true
            }
        }
        binding.textKey.text = key.toString()
        binding.textShiftKey.text = "a -> $letterShift"
        updateText()
    }
}