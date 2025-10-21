package org.saham.fooddelivery.core.paltform_module

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module
import org.saham.fooddelivery.core.local_database.AppDatabase
import org.saham.fooddelivery.core.local_database.getDatabaseBuilder
import org.saham.fooddelivery.core.local_database.getRoomDatabase
import org.saham.fooddelivery.koin_app.app_module.appModule
import org.saham.fooddelivery.koin_app.app_module.repositoriesModules
import org.saham.fooddelivery.koin_app.app_module.useCasesModules
import org.saham.fooddelivery.koin_app.app_module.viewModelsModules


private lateinit var appContext: Application

fun initKoin(app: Application) {
    appContext = app
}

actual fun platformKoinConfig() {
    org.koin.core.context.startKoin {
        androidContext(appContext)
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
        val builder = getDatabaseBuilder(context = get())
        getRoomDatabase(builder)
    }
}
