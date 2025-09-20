package org.saham.fooddelivery.core.websocket

interface WebSocketHelper {

    var onMessageReceived: ((String) -> Unit)?

    fun connect()
    fun sendMessage(message: String)
    fun close()
}