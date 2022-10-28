package com.android.vengateshm.androidpractice.navigation_component.bottom_navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.android.vengateshm.androidpractice.R
import com.android.vengateshm.androidpractice.databinding.FragmentMyDialogBinding

class MyDialogFragment : DialogFragment() {

    private var _binding: FragmentMyDialogBinding? = null

    private val binding
        get() = _binding!!

    /*override fun getTheme(): Int {
        return R.style.DialogFragmentTheme
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_FRAME, R.style.DialogFragmentTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMyDialogBinding.inflate(inflater, container, false)
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

    /*override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        val bind =
            FragmentMyDialogBinding.inflate(layoutInflater, null, false)
        alertDialogBuilder.setView(bind.root)
        arguments?.let { pArgs ->
            val title = pArgs.getString("title") ?: "Menu"
            bind.tvTitle.text = title
        }
        return alertDialogBuilder.create()
    }*/

    /*override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setMessage(getString(R.string.sample_message))
            .setPositiveButton(getString(R.string.ok)) { _,_ -> }
            .create()*/
}