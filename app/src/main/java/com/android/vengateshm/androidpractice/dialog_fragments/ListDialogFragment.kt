package com.android.vengateshm.androidpractice.dialog_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.vengateshm.androidpractice.R
import com.android.vengateshm.androidpractice.databinding.ListDialogBinding

class ListDialogFragment<T : DialogListable>(
    private val listItems: List<T>,
    private val onItemSelected: ((T) -> Unit)?,
) : DialogFragment() {

    private var _binding: ListDialogBinding? = null

    private val binding: ListDialogBinding
        get() = _binding!!

    private lateinit var listDialogAdapter: ListDialogAdapter<T>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.Theme_AndroidPractice_FullWidthDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = ListDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        listDialogAdapter = ListDialogAdapter(this::onItemSelected)
        listDialogAdapter.setItems(listItems)

        with(binding.rvList) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = listDialogAdapter
        }
    }

    private fun onItemSelected(t: T) {
        onItemSelected?.invoke(t)
        dismiss()
    }
}