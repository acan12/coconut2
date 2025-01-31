package app.coconut2.coconut2_mvvm.base.datasource.helper

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import androidx.lifecycle.LiveData

class NetworkManagerHelper(private val context: Context) : LiveData<Boolean>() {
    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

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


class NetworkConnectionReceiver(private val onConnected: () -> Unit = {}, private val onDisconnect: () -> Unit = {}) : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        try {
            if (isOnline(context)) {
                onConnected.invoke()
            } else {
                onDisconnect.invoke()
            }
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }
    }

    private fun isOnline(context: Context): Boolean {
        return try {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = cm.activeNetworkInfo
            //should check null because in airplane mode it will be null
            netInfo != null && netInfo.isConnected
        } catch (e: NullPointerException) {
            e.printStackTrace()
            false
        }
    }
}