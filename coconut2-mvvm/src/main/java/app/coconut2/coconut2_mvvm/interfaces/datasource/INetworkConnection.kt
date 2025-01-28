package app.coconut2.coconut2_mvvm.interfaces.datasource

interface INetworkConnection {
    fun handleNoConnectionInternet()
    fun callbackReConnectingNetwork()
}