package org.saham.fooddelivery.core.local_database

import org.koin.core.module.Module
import org.koin.dsl.module

val databaseModule: Module = module {
    single<OrderDao> { get<AppDatabase>().getDao() }
}