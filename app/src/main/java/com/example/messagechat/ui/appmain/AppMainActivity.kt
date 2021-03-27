package com.example.messagechat.ui.appmain

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.example.messagechat.R
import com.example.messagechat.data.repository.user.UserRepositoryImpl
import com.example.messagechat.databinding.ActivityAppMainBinding
import com.example.messagechat.utils.Constants
import com.example.messagechat.utils.SocketInstance
import com.github.nkzawa.socketio.client.Socket
import kotlinx.android.synthetic.main.app_bar_main.view.*
import kotlinx.android.synthetic.main.nav_header_main.view.*

class AppMainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityAppMainBinding
    private lateinit var appMainViewModel: AppMainViewModel
    private lateinit var navController: NavController
    private var mSocket: Socket? = null
    private val appMainViewModelFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            val repository = UserRepositoryImpl(app = application)
            @Suppress("UNCHECKED_CAST")
            return AppMainViewModel(repository) as T
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_app_main
        )
        appMainViewModel = ViewModelProvider(this, appMainViewModelFactory).get(AppMainViewModel::class.java)
        binding.appMainViewModel = appMainViewModel

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

        initObservers()
    }

    private fun fetchUserInfo() {
        val pref = getSharedPreferences(Constants.PREF_ACCOUNT_INFO, Context.MODE_PRIVATE)
        val id = pref.getString(Constants.PREF_KEY_ACCOUNT_ID, "")
        val token = pref.getString(Constants.PREF_KEY_ACCOUNT_TOKEN, "")
        if (id != null && token != null) {
            appMainViewModel.fetchUserInfo(Constants.HEADER_TYPE_BEARER + token, id)
        }
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

        appMainViewModel.getIsLoggedIn().observe(this, Observer {
            if (it) {
                fetchUserInfo()
                initSocket()
                mSocket?.connect()
            }
        })
    }

    private fun initSocket() {
        mSocket = SocketInstance.socket

        mSocket?.on(Socket.EVENT_CONNECT) { args ->
            Log.e("tung", "Connected")
        }
        mSocket?.on(Socket.EVENT_DISCONNECT) { args ->
            Log.e("tung", "Disconnected")
        }
        mSocket?.on(Socket.EVENT_CONNECT_ERROR) { args ->
            Log.e("tung", "Connected error")
        }
        mSocket?.on(Socket.EVENT_CONNECT_TIMEOUT) { args ->
            Log.e("tung", "Connected error - time out")
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        //val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}