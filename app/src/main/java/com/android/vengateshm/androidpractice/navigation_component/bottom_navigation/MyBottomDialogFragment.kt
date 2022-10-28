package com.android.vengateshm.androidpractice.navigation_component.bottom_navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.vengateshm.androidpractice.databinding.FragmentMenuDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MyBottomDialogFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentMenuDialogBinding? = null

    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMenuDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { pArgs ->
            val title = pArgs.getString("title") ?: "Menu"
            binding.tvTitle.text = title
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}