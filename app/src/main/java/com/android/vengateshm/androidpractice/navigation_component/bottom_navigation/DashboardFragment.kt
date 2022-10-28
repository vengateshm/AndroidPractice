package com.android.vengateshm.androidpractice.navigation_component.bottom_navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.android.vengateshm.androidpractice.R
import com.android.vengateshm.androidpractice.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    private val binding
        get() = _binding!!

    private val viewModel: DashBoardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnOpenDialog.setOnClickListener {
            findNavController().navigate(DashboardFragmentDirections.actionDashboardFragmentToMyDialogFragment(
                title = "Dialog Menu"
            ))
        }

        binding.btnOpenBottomDialog.setOnClickListener {
            findNavController().navigate(DashboardFragmentDirections.actionDashboardFragmentToMyBottomDialogFragment(
                title = "Bottom Dialog Menu"
            ))
        }

        binding.btnOpenActivity.setOnClickListener {
            findNavController().navigate(
                R.id.sampleDestinationActivity,
                bundleOf("text" to "Halo!")
            )
        }

        val scrollPosition = viewModel.scrollPosition
        binding.scrollView.scrollTo(scrollPosition.first, scrollPosition.second)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.scrollPosition = Pair(binding.scrollView.scrollX, binding.scrollView.scrollY)
        _binding = null
    }
}