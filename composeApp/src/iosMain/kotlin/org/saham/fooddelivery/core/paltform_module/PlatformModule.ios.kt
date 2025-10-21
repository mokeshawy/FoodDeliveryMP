package org.saham.fooddelivery.core.paltform_module

import org.koin.core.module.Module
import org.koin.dsl.module
import org.saham.fooddelivery.core.local_database.AppDatabase
import org.saham.fooddelivery.core.local_database.getDatabaseBuilder
import org.saham.fooddelivery.core.local_database.getRoomDatabase
import org.saham.fooddelivery.koin_app.app_module.appModule
import org.saham.fooddelivery.koin_app.app_module.repositoriesModules
import org.saham.fooddelivery.koin_app.app_module.useCasesModules
import org.saham.fooddelivery.koin_app.app_module.viewModelsModules


actual fun platformKoinConfig() {
    org.koin.core.context.startKoin {
        modules(
            appModule,
            repositoriesModules,
            useCasesModules,
            viewModelsModules,
            platformModule()
        )
    }
}

actual fun platformModule(): Module = module {
    single<AppDatabase> {
        val builder = getDatabaseBuilder()
        getRoomDatabase(builder)
    }
}