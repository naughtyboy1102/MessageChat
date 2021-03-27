package com.example.messagechat.ui.login

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.messagechat.R
import com.example.messagechat.data.repository.login.LoginRepositoryImpl
import com.example.messagechat.databinding.FragmentLoginBinding
import com.example.messagechat.ui.appmain.AppMainViewModel
import com.example.messagechat.utils.Constants
import com.example.messagechat.utils.UtilMethods

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var appMainViewModel: AppMainViewModel
    private lateinit var navController: NavController
    private val loginViewModelFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            val repository = LoginRepositoryImpl(app = requireActivity().application)
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(repository) as T
        }
    }

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        loginViewModel = ViewModelProvider(this, loginViewModelFactory).get(LoginViewModel::class.java)
        appMainViewModel = ViewModelProvider(requireActivity()).get(AppMainViewModel::class.java)
        binding.loginViewModel = loginViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        initListeners()
        initObservers()
    }

    private fun initObservers() {
        loginViewModel.getLoginResponse().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                val pref = requireActivity().getSharedPreferences(Constants.PREF_ACCOUNT_INFO, Context.MODE_PRIVATE)
                val loginPref = pref.edit()
                loginPref.putString(Constants.PREF_KEY_ACCOUNT_ID, it.id)
                loginPref.putString(Constants.PREF_KEY_ACCOUNT_TOKEN, it.token)
                loginPref.putString(Constants.PREF_KEY_ACCOUNT_EMAIL, it.email)
                loginPref.apply()
                UtilMethods.hideLoading()
                appMainViewModel.setIsLoggedIn(true)
                navController.navigate(R.id.action_nav_loginFragment_to_nav_homeFragment)
            } else {
                UtilMethods.hideLoading()
                UtilMethods.showLongToast(requireContext(), "Login failed!")
            }
        })
    }

    private fun initListeners() {
        binding.btnFragmentLoginLogin.setOnClickListener {
            UtilMethods.showLoading(requireContext())
            if (UtilMethods.isConnectedToInternet(requireContext())) {
                loginViewModel.doLogin(binding.edtFragmentLoginEmail.text.toString(), binding.edtFragmentLoginPassword.text.toString())
            } else {
                UtilMethods.hideLoading()
                UtilMethods.showLongToast(requireContext(), "No internet connection!")
            }

        }

        binding.btnFragmentLoginCreateAccount.setOnClickListener {
            navController.navigate(R.id.nav_createAccountFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        loginViewModel.getDisposable().dispose()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}