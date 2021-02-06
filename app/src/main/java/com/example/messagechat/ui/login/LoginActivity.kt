package com.example.messagechat.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.messagechat.R
import com.example.messagechat.databinding.ActivityLoginBinding
import com.example.messagechat.ui.createaccount.CreateAccountActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_login
        )

        binding.btnActivityLoginLogin.setOnClickListener {

        }

        binding.btnActivityLoginCreateAccount.setOnClickListener {
            val createAccountIntent = Intent(this, CreateAccountActivity::class.java)
            startActivity(createAccountIntent)
        }
    }
}