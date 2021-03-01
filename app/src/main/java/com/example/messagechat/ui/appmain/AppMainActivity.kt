package com.example.messagechat.ui.appmain

import android.content.Intent
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import com.example.messagechat.R
import com.example.messagechat.databinding.ActivityAppMainBinding
import com.example.messagechat.ui.login.LoginActivity
import kotlinx.android.synthetic.main.app_bar_main.view.*

class AppMainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityAppMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_app_main
        )
        //binding.lifecycleOwner = this
        login()

        //Setup navigation
        setSupportActionBar(binding.drawerLayout.toolbar)
        navController = findNavController(R.id.nav_host_fragment)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home //, R.id.nav_gallery, R.id.nav_slideshow
            ), binding.drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)
        //End setup navigation

        initListeners()
    }

    private fun login() {
        val intent: Intent = Intent(this, LoginActivity::class.java)

    }

    private fun initListeners() {

        /*binding.navHeaderInclude.btn_navheader_login.setOnClickListener {
            //val loginIntent = Intent(this, LoginActivity::class.java)
            //startActivity(loginIntent)

            navController.navigate(R.id.action_nav_home_to_loginActivity)
        }

        binding.navHeaderInclude.btn_navheader_addchannel.setOnClickListener {

        }

        binding.drawerLayout.btn_main_sendmsg.setOnClickListener {

        }*/
    }


    override fun onSupportNavigateUp(): Boolean {
        //val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}