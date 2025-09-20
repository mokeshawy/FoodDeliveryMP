package org.saham.fooddelivery.koin_app.app_module

import org.koin.dsl.module
import org.saham.fooddelivery.core.network.di.networkModule
import org.saham.fooddelivery.features.order_list_screen.di.orderListRepositoryModule
import org.saham.fooddelivery.features.order_list_screen.di.orderListViewModelModule
import org.saham.fooddelivery.features.order_list_screen.di.ordersListUseCaseModule


val appModule = module {
    includes(networkModule)
}


val repositoriesModules = module {
    includes(orderListRepositoryModule)
}

val useCasesModules = module {
    includes(ordersListUseCaseModule)
}

val viewModelsModules = module {
    includes(orderListViewModelModule)
}