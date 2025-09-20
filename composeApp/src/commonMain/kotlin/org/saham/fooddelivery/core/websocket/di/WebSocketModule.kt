package org.saham.fooddelivery.core.websocket.di

import org.koin.dsl.module
import org.saham.fooddelivery.core.websocket.WebSocketHelper
import org.saham.fooddelivery.core.websocket.WebSocketManagerImpl


val websocketModule = module {
    single<WebSocketHelper> { WebSocketManagerImpl(coroutineScope = get()) }
}

