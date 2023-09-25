package com.android.vengateshm.androidpractice.app_clones.whatsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.vengateshm.androidpractice.app_clones.whatsapp.adapters.ChannelHorizontalListAdapter
import com.android.vengateshm.androidpractice.app_clones.whatsapp.adapters.ChannelsVerticalListAdapter
import com.android.vengateshm.androidpractice.app_clones.whatsapp.adapters.StatusAdapter
import com.android.vengateshm.androidpractice.app_clones.whatsapp.models.Channel
import com.android.vengateshm.androidpractice.app_clones.whatsapp.models.Status
import com.android.vengateshm.androidpractice.databinding.FragmentUpdatesBinding

class UpdatesFragment : Fragment() {

    private var _binding: FragmentUpdatesBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var statusAdapter: StatusAdapter
    private lateinit var channelsVerticalListAdapter: ChannelsVerticalListAdapter
    private lateinit var channelHorizontalListAdapter: ChannelHorizontalListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentUpdatesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            statusAdapter = StatusAdapter()
            statusAdapter.statusList = Status.getStatusList()
            rvStatusList.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            rvStatusList.adapter = statusAdapter

            channelsVerticalListAdapter = ChannelsVerticalListAdapter()
            channelsVerticalListAdapter.channelsList = Channel.getChannelList()
            rvChannelsVerticalList.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            rvChannelsVerticalList.adapter = channelsVerticalListAdapter

            channelHorizontalListAdapter = ChannelHorizontalListAdapter()
            channelHorizontalListAdapter.channelList = Channel.getFindChannelList()
            rvChannelsHorizontalList.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            rvChannelsHorizontalList.adapter = channelHorizontalListAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}