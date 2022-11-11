package dev.vengateshm.stock_exchange

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.pusher.client.Pusher
import com.pusher.client.PusherOptions
import com.pusher.client.connection.ConnectionEventListener
import com.pusher.client.connection.ConnectionState
import com.pusher.client.connection.ConnectionStateChange
import dev.vengateshm.stock_exchange.databinding.ActivityStockBinding
import org.json.JSONObject

class StockActivity : ComponentActivity() {

    private lateinit var binding: ActivityStockBinding

    private val mAdapter = StockListAdapter(ArrayList())

    private val options = PusherOptions().apply {
        setCluster("ap2")
    }
    private val pusher = Pusher("f6512674428f1cada78c", options)
    private val channel = pusher.subscribe("stock-channel")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStockBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        pusher.connect(object : ConnectionEventListener {
            override fun onConnectionStateChange(change: ConnectionStateChange) {
                Log.i("Pusher",
                    "State changed from ${change.previousState} to ${change.currentState}")
            }

            override fun onError(
                message: String,
                code: String,
                e: Exception,
            ) {
                Log.i("Pusher",
                    "There was a problem connecting! code ($code), message ($message), exception($e)")
            }
        }, ConnectionState.ALL)

        MyStockList.stockList.forEachIndexed { index, stockModel ->
            if (!mAdapter.contains(stockModel)) {
                mAdapter.addItem(stockModel)
                channel.bind(stockModel.name) { pusherEvent ->
                    val jsonObject = JSONObject(pusherEvent.data)
                    runOnUiThread {
                        mAdapter.updateItem(
                            StockModel(
                                pusherEvent.eventName,
                                jsonObject.getDouble("currentValue"),
                                jsonObject.getDouble("changePercent")
                            )
                        )
                    }
                }
            }
        }

        channel.bind("Amazon") { event ->
            Log.i("Pusher", "Received event with data: $event")
        }
    }

    private fun setupRecyclerView() {
        with(binding.recyclerView) {
            layoutManager = LinearLayoutManager(this@StockActivity)
            adapter = mAdapter
            addItemDecoration(
                DividerItemDecoration(binding.recyclerView.context, DividerItemDecoration.VERTICAL)
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        MyStockList.stockList.forEachIndexed { index, stockModel ->
            channel.unbind(stockModel.name) {

            }
        }
    }
}