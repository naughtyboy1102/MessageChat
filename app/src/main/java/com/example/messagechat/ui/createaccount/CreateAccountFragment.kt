package com.example.messagechat.ui.createaccount

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.messagechat.R
import com.example.messagechat.databinding.FragmentCreateAccountBinding
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CreateAccountFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateAccountFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentCreateAccountBinding
    private lateinit var viewModel: CreateAccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //DataBindingUtil.setDefaultComponent(this) -> for using binding adapter
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_account, container, false)
        viewModel = ViewModelProvider(this).get(CreateAccountViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners() {
        binding.btnGenerateAvatar.setOnClickListener {
            val random = Random()
            val avatar = random.nextInt(12)

            var userAvatar = if (avatar < 10) "ic_user_avatar0$avatar" else "ic_user_avatar$avatar"
            val resId = resources.getIdentifier(userAvatar, "drawable", activity?.packageName)

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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                activity?.finish()
            }
        }
        return super.onOptionsItemSelected(item)
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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CreateAccountFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CreateAccountFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}