package com.example.messagechat.utils

import com.github.nkzawa.socketio.client.IO
import com.github.nkzawa.socketio.client.Socket
import java.lang.RuntimeException
import java.net.URISyntaxException

object SocketInstance {
    private var mSocket: Socket? = null
//
//    init {
//        try {
//            mSocket = IO.socket(Constants.BASE_URL)
//        } catch (e: URISyntaxException) {
//            throw RuntimeException(e)
//        }
//    }
   val socket: Socket
    get() {
        if (mSocket == null) {
                    try {
            mSocket = IO.socket(Constants.BASE_URL)
        } catch (e: URISyntaxException) {
            throw RuntimeException(e)
        }
        }
        return mSocket!!
    }

    //fun getSocket() = socket

}