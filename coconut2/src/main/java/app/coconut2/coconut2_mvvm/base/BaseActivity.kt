package app.coconut2.coconut2_mvvm.base

import android.content.ComponentCallbacks2
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import app.coconut2.coconut2_mvvm.network.ConnectionManager

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity(), ComponentCallbacks2 {
    private var _binding: VB? = null
    protected val binding: VB
        get() = _binding!!

    abstract fun inflateBinding(): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = inflateBinding()
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    protected fun setupConnection(
        connectionManager: ConnectionManager,
        onConnectAction: (isConnect: Boolean) -> Unit
    ) {
        connectionManager.observe(this, { isConnect ->
            onConnectAction.invoke(isConnect)
        })
    }

    protected fun showShortToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    protected fun showLongToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}