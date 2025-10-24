package org.saham.fooddelivery.core.platform_koin_config

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.saham.fooddelivery.core.local_database.AppDatabase
import org.saham.fooddelivery.core.local_database.getDatabaseBuilder
import org.saham.fooddelivery.core.local_database.getRoomDatabase
import org.saham.fooddelivery.koin_app.app_module.platformModule


private lateinit var appContext: Application

fun initKoin(app: Application) {
    appContext = app
}

actual fun platformKoinConfig() {
    startKoin {
        androidContext(appContext)
        platformModule(
            onMoreModule = { androidModule }
        )
    }
}


private val androidModule = module {
    single<AppDatabase> {
        val builder = getDatabaseBuilder(context = get())
        getRoomDatabase(builder)
    }
}
