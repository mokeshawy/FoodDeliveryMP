package org.saham.fooddelivery

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform