package com.android.vengateshm.androidpractice.airbnb.mavericks.feature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.airbnb.mvrx.MavericksView
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.android.vengateshm.androidpractice.R
import com.android.vengateshm.androidpractice.databinding.FragmentUserMavericksBinding

class UserFragment : Fragment(R.layout.fragment_user_mavericks), MavericksView {

    private val viewModel: UserViewModel by fragmentViewModel()
    private val _controller = UserListController()
    private var _binding: FragmentUserMavericksBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentUserMavericksBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            userList.adapter = _controller.adapter
        }

        viewModel.getUserList()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun invalidate() {
        withState(viewModel) {
            if (it.userList.isNotEmpty()) {
                _controller.setData(it.userList)
            }
        }
    }
}