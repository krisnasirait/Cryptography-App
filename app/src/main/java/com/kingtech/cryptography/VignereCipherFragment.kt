package com.kingtech.cryptography

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import java.util.*


class VignereCipherFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vignere_cipher, container, false)
    }

    fun encrypt(Message: String, Key: String): String? {
        var message = Message
        var eMessage = ""
        message = Message.uppercase(Locale.getDefault())
        var i = 0
        var j = 0
        while (i < Message.length) {
            val letter = Message[i]
            eMessage += ((letter.code - 65 + (Key[j].code - 65)) % 26 + 65).toChar()
            j = ++j % Key.length
            i++
        }
        return eMessage
    }
}