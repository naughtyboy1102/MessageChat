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

class ContactsFragment : Fragment() {

    private lateinit var backPressedCallback: OnBackPressedCallback

    private lateinit var viewModel: ContactsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.contacts_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    /* Custom back button to update bottom nav but this is not necessary any more because we can
        use bottomNavController.addOnDestinationChangedListener to update it when destination changed
        homeViewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
        backPressedCallback =  object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                isEnabled = false
                requireActivity().onBackPressed()
                homeViewModel.setIsBottomNavChatSelected(true)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, backPressedCallback) */
}