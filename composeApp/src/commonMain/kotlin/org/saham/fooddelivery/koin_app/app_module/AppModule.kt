package org.saham.fooddelivery.koin_app.app_module

import org.koin.dsl.module
import org.saham.fooddelivery.core.coroutines_scope.CoroutinesScopeModule
import org.saham.fooddelivery.core.network.di.networkModule
import org.saham.fooddelivery.core.websocket.di.websocketModule
import org.saham.fooddelivery.features.order_details.di.orderDetailsRepositoryModule
import org.saham.fooddelivery.features.order_details.di.orderDetailsViewModelModule
import org.saham.fooddelivery.features.order_details.di.ordersDetailsUseCaseModule
import org.saham.fooddelivery.features.order_list_screen.di.orderListRepositoryModule
import org.saham.fooddelivery.features.order_list_screen.di.orderListViewModelModule
import org.saham.fooddelivery.features.order_list_screen.di.ordersListUseCaseModule


val appModule = module {
    includes(
        networkModule,
        websocketModule,
        CoroutinesScopeModule
    )
}


val repositoriesModules = module {
    includes(
        orderListRepositoryModule,
        orderDetailsRepositoryModule
    )
}

val useCasesModules = module {
    includes(
        ordersListUseCaseModule,
        ordersDetailsUseCaseModule
    )
}

val viewModelsModules = module {
    includes(
        orderListViewModelModule,
        orderDetailsViewModelModule
    )
}