package com.example.messagechat.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.messagechat.R
import com.example.messagechat.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    private lateinit var bottomNavController: NavController


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.viewModel = viewModel
        setHasOptionsMenu(true)

        //val textView: TextView = root.findViewById(R.id.text_home)
       // homeViewModel.text.observe(viewLifecycleOwner, Observer {
        //    textView.text = it
       // })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomNavController = Navigation.findNavController(binding.root.findViewById(R.id.nav_host_fragment_home))
        NavigationUI.setupWithNavController(binding.bottomNavFragmentHome, bottomNavController)
        initListeners()
    }

    private fun initListeners() {
        binding.lnlChat.setOnClickListener {
            if (!viewModel.getIsBottomNavChatSelected()) {
                updateBottomNavChat()
                bottomNavController.navigate(R.id.action_contactsFragment_to_chatOverviewFragment)
            }
        }

        binding.lnlContacts.setOnClickListener {
            if (viewModel.getIsBottomNavChatSelected()) {
                updateBottomNavContacts()
                bottomNavController.navigate(R.id.action_chatOverviewFragment_to_contactsFragment)
            }
        }
    }

    private fun updateBottomNavChat() {
        ResourcesCompat.getDrawable(resources, R.drawable.ic_bottom_nav_chat_selected, null)?.let {
            viewModel.setBottomNavChatDrawable(it)
            viewModel.setBottomNavChatColor(Color.WHITE)
            ResourcesCompat.getDrawable(resources, R.drawable.ic_bottom_nav_contact_normal, null)?.let { drawable ->
                viewModel.setBottomNavContactsDrawable(drawable)
                viewModel.setBottomNavContactsTextColor(resources.getColor(R.color.colorBottomNavItemNormal))
            }
        }
        viewModel.setIsBottomNavChatSelected(true)
    }

    private fun updateBottomNavContacts() {
        ResourcesCompat.getDrawable(resources, R.drawable.ic_bottom_nav_contact_selected, null)?.let {
            viewModel.setBottomNavContactsDrawable(it)
            viewModel.setBottomNavContactsTextColor(Color.WHITE)
            ResourcesCompat.getDrawable(resources, R.drawable.ic_bottom_nav_chat_normal, null)?.let { drawable ->
                viewModel.setBottomNavChatDrawable(drawable)
                viewModel.setBottomNavChatColor(resources.getColor(R.color.colorBottomNavItemNormal))
            }
        }
        viewModel.setIsBottomNavChatSelected(false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_home_menu, menu)
    }
}