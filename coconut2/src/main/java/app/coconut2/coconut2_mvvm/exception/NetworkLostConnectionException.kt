package app.coconut2.coconut2_mvvm.exception

import java.io.IOException

class NetworkLostConnectionException : IOException() {
    override val message: String?
        get() = "No network available, please check your WiFi or Data connection"
}