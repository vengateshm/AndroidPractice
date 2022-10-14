package com.android.vengateshm.androidpractice.observable_types

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.android.vengateshm.androidpractice.databinding.ActivityObservableTypesBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ObservableTypesActivity : AppCompatActivity() {

    private lateinit var viewModel: ObservableTypesViewModel
    private lateinit var binding: ActivityObservableTypesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityObservableTypesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(ObservableTypesViewModel::class.java)

        binding.bLivedata.setOnClickListener {
            viewModel.triggerLiveData()
        }
        binding.bStateFlow.setOnClickListener {
            viewModel.triggerStateFlow()
        }
        binding.bFlow.setOnClickListener {
            lifecycleScope.launch {
                viewModel.triggerFlow().collectLatest {
                    binding.tvFlow.text = it
                }
            }
        }
        binding.bSharedFlow.setOnClickListener {
            viewModel.triggerSharedFlow()
        }

        subscribeToObservables()
    }

    private fun subscribeToObservables() {
        // In fragment use viewLifeCycleOwner
        viewModel.mLiveData.observe(this) { data ->
            binding.tvLivedata.text = data
        }

        lifecycleScope.launchWhenStarted {
            // Hot flow - emits value if no collector present
            viewModel.mStateFlow.collectLatest { data ->
                // Emit value when activity recreated
                binding.tvStateFlow.text = data
                Snackbar.make(
                    binding.root,
                    data,
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }

        lifecycleScope.launchWhenStarted {
            // Hot flow - emits value if no collector present
            // One time value
            viewModel.mSharedFlow.collectLatest { data ->
                // Emit value when activity recreated
                binding.tvSharedFlow.text = data
                Snackbar.make(
                    binding.root,
                    data,
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }
}