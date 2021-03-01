package com.example.messagechat

import com.github.nkzawa.socketio.client.IO
import com.github.nkzawa.socketio.client.Socket
import java.lang.RuntimeException
import java.net.URISyntaxException

private const val URL_SERVER = "http://192.168.1.97:3000" //10.0.2.2 or 10.0.2.3

class SocketInstance {
    private var mSocket: Socket?

    init {
        try {
            mSocket = IO.socket(URL_SERVER)
        } catch (e: URISyntaxException) {
            throw RuntimeException(e)
        }
    }

    fun getSocket() = mSocket

}