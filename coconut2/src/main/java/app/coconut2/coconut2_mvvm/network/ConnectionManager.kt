package app.coconut2.coconut2_mvvm.network

import android.annotation.SuppressLint
import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import androidx.lifecycle.LiveData
import app.coconut2.coconut2_mvvm.core.datasource.helper.NetworkConnectionReceiver
import javax.inject.Inject


class ConnectionManager @Inject constructor(var context: Context) : LiveData<Boolean>() {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    @SuppressLint("ObsoleteSdkInt")
    override fun onActive() {
        super.onActive()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager.registerDefaultNetworkCallback(getNetworkCallback())
        } else {
            val networkConnectivityReceiver = NetworkConnectionReceiver(
                onConnected = { postValue(true) },
                onDisconnect = { postValue(false) },
            )
            val filterNetworkConnectivity = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
            context.registerReceiver(
                networkConnectivityReceiver,
                filterNetworkConnectivity
            )
        }
    }

    override fun onInactive() {
        super.onInactive()
        try {
            connectivityManager.unregisterNetworkCallback(getNetworkCallback())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getNetworkCallback() = object : ConnectivityManager.NetworkCallback() {

        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            postValue(true)
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            postValue(false)
        }
    }
}