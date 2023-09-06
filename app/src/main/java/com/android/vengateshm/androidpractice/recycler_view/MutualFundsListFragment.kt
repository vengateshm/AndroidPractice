package com.android.vengateshm.androidpractice.recycler_view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.vengateshm.androidpractice.databinding.FragmentMutualFundsListBinding

class MutualFundsListFragment : Fragment() {

    private var _binding: FragmentMutualFundsListBinding? = null
    val binding
        get() = _binding!!

    var currentSelectedReturnYr = ReturnYr.ONE
    private lateinit var mutualFundAdapter: MutualFundAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMutualFundsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.tvReturns) {
            text = "<> ${ReturnYr.ONE.value}"
            setOnClickListener {
                onReturnsFilterClicked { newReturn ->
                    text = "<> ${newReturn.value}"
                }
            }
        }

        mutualFundAdapter = MutualFundAdapter()
        mutualFundAdapter.mutualFundList = mutualFundList.toMutableList()
        with(binding.rvMutualFunds) {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
            adapter = mutualFundAdapter
        }
    }

    private fun onReturnsFilterClicked(onNewReturn: (ReturnYr) -> Unit) {
        currentSelectedReturnYr = getNextReturn(currentSelectedReturnYr)
        onNewReturn(currentSelectedReturnYr)
        mutualFundAdapter.currentSelectedReturnYr = currentSelectedReturnYr
        mutualFundAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}