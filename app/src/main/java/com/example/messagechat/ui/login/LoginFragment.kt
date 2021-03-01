package com.example.messagechat.ui.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.messagechat.R
import com.example.messagechat.SocketInstance
import com.example.messagechat.databinding.FragmentLoginBinding
import com.github.nkzawa.socketio.client.Socket

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
    private var mSocket = SocketInstance().getSocket()


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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initSocketListeners()
        mSocket?.connect()
    }

    private fun initListeners() {
        binding.btnFragmentLoginLogin.setOnClickListener {

        }

        binding.btnFragmentLoginCreateAccount.setOnClickListener {
            //val createAccountIntent = Intent(this, CreateAccountActivity::class.java)
            //tartActivity(createAccountIntent)
            findNavController().navigate(R.id.createAccountFragment)
        }
    }

    private fun initSocketListeners() {
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

    override fun onDestroy() {
        super.onDestroy()
        mSocket?.disconnect()
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