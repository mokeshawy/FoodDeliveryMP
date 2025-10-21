package org.saham.fooddelivery.core.background_services

import platform.Foundation.NSTimer


class BackgroundServiceIos : BackgroundService {
    private var timer: NSTimer? = null

    override fun start() {
        timer = NSTimer.scheduledTimerWithTimeInterval(interval = 30.0, repeats = true) {
            println("BackgroundServiceIos: performing periodic task")
            // TODO: Call your shared logic here
        }
    }

    override fun stop() {
        println("BackgroundServiceIos: stopped")
        timer?.invalidate()
        timer = null
    }
}