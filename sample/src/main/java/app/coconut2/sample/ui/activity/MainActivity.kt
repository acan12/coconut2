package app.coconut2.sample.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import app.coconut2.coconut2_mvvm.base.BaseActivity
import app.coconut2.sample.R
import app.coconut2.sample.data.model.entity.UserEntity
import app.coconut2.sample.databinding.ActivityMainBinding
import app.coconut2.sample.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private var userLatest: UserEntity? = null

    private val viewModel: UserViewModel by viewModels()

    override fun inflateBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        binding.textTitlePage.text = resources.getString(R.string.app_name)

        binding.btnInsertData.setOnClickListener {
            doInsertData()
        }
        binding.btnDeleteData.setOnClickListener {
            doDeleteData()
        }

        observerHandler()
    }

    private fun doDeleteData() {

        if (userLatest != null)
            viewModel.deleteLastUser(userLatest!!)
    }

    private fun doInsertData() {
        viewModel.insert(
            UserEntity(
                name = "Dodol 2 Jawa",
                address = "Pondok Indah",
                age = 18
            )
        )
    }

    private fun observerHandler() {
        viewModel.allUsers.observe(this) {
            userLatest = it.last()
            val count = it.size
            Toast.makeText(this, "Data Size = $count", Toast.LENGTH_LONG).show()
        }
    }
}