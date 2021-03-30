package com.example.messagechat.ui.start

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.messagechat.R
import com.example.messagechat.ui.appmain.AppMainViewModel
import com.example.messagechat.utils.Constants


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StartFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StartFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var btnLogin: Button
    private lateinit var navController: NavController
    private lateinit var appMainViewModel: AppMainViewModel


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
        val pref = requireActivity().getSharedPreferences(Constants.PREF_ACCOUNT_INFO, Context.MODE_PRIVATE);
        val token = pref.getString(Constants.PREF_KEY_ACCOUNT_TOKEN, null)
        navController = findNavController()

        if (token != null) {
            appMainViewModel = ViewModelProvider(requireActivity()).get(AppMainViewModel::class.java)
            appMainViewModel.setIsLoggedIn(true)
            navController.navigate(R.id.action_nav_startFragment_to_nav_homeFragment)
        }
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //view.windowInsetsController.hide(WindowInsets.Type.statusBars())
        btnLogin = view.findViewById(R.id.btn_start_activity_login)
        //navController = findNavController() //requireActivity().findNavController(R.id.nav_host_fragment)

        btnLogin.setOnClickListener {
            navController.navigate(R.id.action_nav_startFragment_to_nav_loginFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        val supportActionBar: ActionBar? =
            (requireActivity() as AppCompatActivity).supportActionBar
        supportActionBar?.hide()
        requireActivity().window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    override fun onStop() {
        super.onStop()
        val supportActionBar: ActionBar? =
            (requireActivity() as AppCompatActivity).supportActionBar
        supportActionBar?.show()
        requireActivity().window.clearFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment StartFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StartFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}