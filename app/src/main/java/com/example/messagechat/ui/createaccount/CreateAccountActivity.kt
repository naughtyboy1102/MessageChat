package com.example.messagechat.ui.createaccount

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.messagechat.R
import com.example.messagechat.databinding.ActivityCreateAccountBinding
import java.util.*

class CreateAccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateAccountBinding
    private lateinit var viewModel: CreateAccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //DataBindingUtil.setDefaultComponent(this) -> for using binding adapter
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_create_account
        )
        viewModel = ViewModelProvider(this).get(CreateAccountViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        initListeners()
    }

    private fun initListeners() {
        binding.btnGenerateAvatar.setOnClickListener {
            val random = Random()
            val avatar = random.nextInt(12)

            var userAvatar = if (avatar < 10) "ic_user_avatar0$avatar" else "ic_user_avatar$avatar"
            val resId = resources.getIdentifier(userAvatar, "drawable", packageName)

            ResourcesCompat.getDrawable(resources, resId, null)?.let { viewModel.setUserAvatar(it) }
        }

        binding.btnGenerateColor.setOnClickListener {
            val random = Random()
            val r = random.nextInt(255)
            val g = random.nextInt(255)
            val b = random.nextInt(255)
            //binding.btnGenerateAvatar.setBackgroundColor(Color.rgb(r, g, b))
            viewModel.setUserAvatarColor(Color.rgb(r, g, b))


            /* convert to 0 - 1
            val saveR = r / 255
            val saveG = g / 255
            val saveB = b / 255

             */
           // avatarColor = "[$r, $g, $b, 1]"

        }

        binding.btnSelectAvatar.setOnClickListener {

        }

        binding.btnCreateAccount.setOnClickListener {

        }
    }

    /* Using binding adapter

    implement DataBindingComponent

    override fun getCreateAccountBindingAdapters(): CreateAccountBindingAdapters {
        return CreateAccountBindingAdapters()
    }

    inner class CreateAccountBindingAdapters {
        @BindingAdapter("app:backgroundColor")
        fun setBackgroundColor(view: ImageView, color: Int?) {
            if (color == null) {
                view.setBackgroundColor(resources.getColor(R.color.colorTransparent))
            } else view.setBackgroundColor(color)
        }
    }
    */

}