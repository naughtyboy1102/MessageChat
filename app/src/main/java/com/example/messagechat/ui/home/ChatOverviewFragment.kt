package com.example.messagechat.ui.home

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import com.example.messagechat.R

class ChatOverviewFragment : Fragment() {

    companion object {
        fun newInstance() = ChatOverviewFragment()
    }

    private lateinit var viewModel: ChatOverviewViewModel
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var backPressedCallback: OnBackPressedCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /* homeViewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
        backPressedCallback =  object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                homeViewModel.setIsBottomNavChatSelected(false)
                Log.e("tung","Back pressed")
                isEnabled = false
                requireActivity().onBackPressed()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, backPressedCallback)
        */
        return inflater.inflate(R.layout.chat_overview_fragment, container, false)
    }


}