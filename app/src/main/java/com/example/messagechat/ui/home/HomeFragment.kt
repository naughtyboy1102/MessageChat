package com.example.messagechat.ui.home

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.messagechat.R
import com.example.messagechat.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    private lateinit var bottomNavController: NavController
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.viewModel = homeViewModel
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Using for <FragmentContainerView> in layout file
        val nestedNavHostFragment = childFragmentManager.findFragmentById(R.id.nav_host_fragment_home) as NavHostFragment
        bottomNavController = nestedNavHostFragment.navController
        // Using for <fragment> in layout file
        //bottomNavController = Navigation.findNavController(binding.root.findViewById(R.id.nav_host_fragment_home))
        // Setup this for using bottom nav as menu file such as activity_main_drawer
        //NavigationUI.setupWithNavController(binding.bottomNavFragmentHome, bottomNavController)

        initListeners()
    }

    private fun initListeners() {
        binding.lnlChat.setOnClickListener {
            if (!homeViewModel.getIsBottomNavChatSelected()) {
                homeViewModel.setIsBottomNavChatSelected(true)
                bottomNavController.navigate(R.id.action_contactsFragment_to_chatOverviewFragment)
            }
        }

        binding.lnlContacts.setOnClickListener {
            if (homeViewModel.getIsBottomNavChatSelected()) {
                homeViewModel.setIsBottomNavChatSelected(false)
                bottomNavController.navigate(R.id.action_chatOverviewFragment_to_contactsFragment)
            }
        }

        bottomNavController.addOnDestinationChangedListener { _, destination, _ -> //if the parameter is never used it can be rename to '_'
            if(destination.id == R.id.chatOverviewFragment) {
                updateBottomNavChat()
            } else {
                updateBottomNavContacts()
            }
        }
    }

    /* Other solution to update bottom nav
    private fun initObservers() {
        homeViewModel.getIsBottomNavChatSelected().observe(viewLifecycleOwner, Observer {
            if (it) {
                updateBottomNavChat()
            } else {
                updateBottomNavContacts()
            }
        })
    }*/

    private fun updateBottomNavChat() {
        ResourcesCompat.getDrawable(resources, R.drawable.ic_bottom_nav_chat_selected, null)?.let {
            homeViewModel.setBottomNavChatDrawable(it)
            homeViewModel.setBottomNavChatColor(Color.WHITE)
            ResourcesCompat.getDrawable(resources, R.drawable.ic_bottom_nav_contact_normal, null)?.let { drawable ->
                homeViewModel.setBottomNavContactsDrawable(drawable)
                homeViewModel.setBottomNavContactsTextColor(resources.getColor(R.color.colorBottomNavItemNormal))
            }
        }
    }

    private fun updateBottomNavContacts() {
        ResourcesCompat.getDrawable(resources, R.drawable.ic_bottom_nav_contact_selected, null)?.let {
            homeViewModel.setBottomNavContactsDrawable(it)
            homeViewModel.setBottomNavContactsTextColor(Color.WHITE)
            ResourcesCompat.getDrawable(resources, R.drawable.ic_bottom_nav_chat_normal, null)?.let { drawable ->
                homeViewModel.setBottomNavChatDrawable(drawable)
                homeViewModel.setBottomNavChatColor(resources.getColor(R.color.colorBottomNavItemNormal))
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_home_menu, menu)
    }
}