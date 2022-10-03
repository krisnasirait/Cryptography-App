package com.kingtech.cryptography.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kingtech.cryptography.R
import com.kingtech.cryptography.databinding.FragmentRailFenceCipherBinding

class RailFenceCipherFragment : Fragment() {

    private lateinit var binding: FragmentRailFenceCipherBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rail_fence_cipher, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}