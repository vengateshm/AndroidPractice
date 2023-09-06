package com.android.vengateshm.androidpractice.recycler_view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.vengateshm.androidpractice.databinding.FragmentStockListBinding
import kotlin.random.Random

class StockListFragment : Fragment() {

    private var _binding: FragmentStockListBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var stockListAdapter: StockListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentStockListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvRefresh.setOnClickListener {
            onRefreshClicked()
        }

        stockListAdapter = StockListAdapter()
        stockListAdapter.stockList = stockDataList.toMutableList()

        with(binding.rvStocks) {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(), DividerItemDecoration.VERTICAL
                )
            )
            //itemAnimator = null
            adapter = stockListAdapter
        }
    }

    private fun onRefreshClicked() {
        stockListAdapter.stockList.map { stock ->
            val updatedPrice = generateRandomPrice()
            stock.copy(price = updatedPrice)
        }.also { updatedStockList ->
            // DiffUtil
//            stockListAdapter.updateData(updatedStockList)
            // Notify Dataset changed
            /*stockListAdapter.stockList = updatedStockList
            stockListAdapter.notifyDataSetChanged()*/
            // Partial updates
            updatedStockList.forEachIndexed { index, stock ->
                stockListAdapter.updateItemWithPayload(index, stock)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun generateRandomPrice(): Double {
        val randomPrice = Random.nextDouble(10.0, 5000.0)
        return "%.2f".format(randomPrice).toDouble()
    }
}