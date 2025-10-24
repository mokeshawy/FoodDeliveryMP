package org.saham.fooddelivery.core.platform_koin_config

import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.saham.fooddelivery.core.local_database.AppDatabase
import org.saham.fooddelivery.core.local_database.getDatabaseBuilder
import org.saham.fooddelivery.core.local_database.getRoomDatabase
import org.saham.fooddelivery.koin_app.app_module.platformModule


actual fun platformKoinConfig() {
    startKoin {
        platformModule(
            onMoreModule = { iosModule }
        )
    }
}

private val iosModule = module {
    single<AppDatabase> {
        val builder = getDatabaseBuilder()
        getRoomDatabase(builder)
    }
}