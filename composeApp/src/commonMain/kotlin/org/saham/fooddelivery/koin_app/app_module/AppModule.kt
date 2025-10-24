package org.saham.fooddelivery.koin_app.app_module

import com.shared.core.di.coreModule
import org.koin.core.KoinApplication
import org.koin.core.module.Module
import org.koin.dsl.module
import org.saham.fooddelivery.core.di.networkModule
import org.saham.fooddelivery.core.local_database.databaseModule
import org.saham.fooddelivery.features.order_details_screen.di.orderDetailsRepositoryModule
import org.saham.fooddelivery.features.order_details_screen.di.orderDetailsViewModelModule
import org.saham.fooddelivery.features.order_details_screen.di.ordersDetailsUseCaseModule
import org.saham.fooddelivery.features.order_list_screen.di.orderListRepositoryModule
import org.saham.fooddelivery.features.order_list_screen.di.orderListViewModelModule
import org.saham.fooddelivery.features.order_list_screen.di.ordersListUseCaseModule

fun KoinApplication.platformModule(onMoreModule : () -> Module) = modules(
    onMoreModule(),
    appModule,
    repositoriesModules,
    useCasesModules,
    viewModelsModules,
)


val appModule = module {
    includes(
        coreModule,
        networkModule,
        databaseModule
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