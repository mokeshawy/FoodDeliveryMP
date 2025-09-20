package org.saham.fooddelivery.koin_app


import androidx.compose.runtime.Composable
import org.koin.compose.KoinApplication
import org.saham.fooddelivery.koin_app.app_module.appModule
import org.saham.fooddelivery.nav_host.RootNavHost
import org.saham.fooddelivery.theme.MainTheme


@Composable
fun KoinApp() {
    MainTheme(content = {
        KoinApplication(application = { modules(appModule) }, content = { RootNavHost() })
    })
}