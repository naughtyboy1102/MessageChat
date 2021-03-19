package com.example.messagechat.ui.appmain

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.example.messagechat.R
import com.example.messagechat.databinding.ActivityAppMainBinding
import kotlinx.android.synthetic.main.app_bar_main.view.*
import kotlinx.android.synthetic.main.nav_header_main.view.*

class AppMainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityAppMainBinding
    private lateinit var appMainViewModel: AppMainViewModel
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_app_main
        )
        appMainViewModel = ViewModelProvider(this).get(AppMainViewModel::class.java)
        binding.appMainViewModel = appMainViewModel
        //binding.lifecycleOwner = this

        //Setup navigation
        setSupportActionBar(binding.drawerLayout.toolbar)
        navController = findNavController(R.id.nav_host_fragment)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_homeFragment //, R.id.nav_gallery, R.id.nav_slideshow
            ), binding.drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)
        //End setup navigation
        //binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        //initListeners()
        initObservers()
        appMainViewModel.fetchUserInfo()
    }

    private fun initObservers() {
        appMainViewModel.getUser().observe(this, Observer {
            if (!it.userAvatar.equals("")) {
                val resId = resources.getIdentifier(it.userAvatar, "drawable", packageName)
                ResourcesCompat.getDrawable(resources, resId, null)?.let { drawable ->
                    binding.navView.getHeaderView(0).tv_navheader_useravatar.setImageDrawable(drawable)
                }
            }
            binding.navView.getHeaderView(0).tv_navheader_username.text = it.userName//appMainViewModel.getUserName()
            binding.navView.getHeaderView(0).tv_navheader_useremail.text = it.userEmail//appMainViewModel.getUserEmail()
        })
    }

    private fun initListeners() {
        navController.addOnDestinationChangedListener { _, destination, _ -> //if the parameter is never used it can be rename to '_'
            if(destination.id == R.id.nav_startFragment || destination.id == R.id.nav_loginFragment
                || destination.id == R.id.nav_createAccountFragment) {
                binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            } else {
                binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            }
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        //val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}