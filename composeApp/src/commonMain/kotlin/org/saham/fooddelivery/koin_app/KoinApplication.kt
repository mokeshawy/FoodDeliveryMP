package org.saham.fooddelivery.koin_app


import androidx.compose.runtime.Composable
import org.koin.compose.KoinApplication
import org.saham.fooddelivery.core.platform_koin_config.platformKoinConfig
import org.saham.fooddelivery.nav_host.RootNavHost
import org.saham.fooddelivery.theme.MainTheme


@Composable
fun KoinApp() {
    MainTheme(content = {
        KoinApplication(
            application = { platformKoinConfig() },
            content = { RootNavHost() }
        )
    })
}