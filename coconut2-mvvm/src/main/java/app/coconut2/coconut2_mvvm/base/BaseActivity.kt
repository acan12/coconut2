package app.coconut2.coconut2_mvvm.base

import android.content.ComponentCallbacks2
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import app.coconut2.coconut2_mvvm.core.datasource.helper.NetworkManagerHelper

abstract class BaseActivity<VB: ViewBinding> : AppCompatActivity(), ComponentCallbacks2 {
    private var _binding : VB? = null
    protected val binding : VB
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

    protected fun setupConnection(context: Context, observe: Observer<Boolean>) {
        NetworkManagerHelper(context).observe(this, observe)
    }
}