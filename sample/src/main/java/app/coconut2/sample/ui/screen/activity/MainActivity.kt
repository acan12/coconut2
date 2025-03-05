package app.coconut2.sample.ui.screen.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import androidx.core.view.isVisible
import app.coconut2.coconut2_mvvm.base.BaseActivity
import app.coconut2.coconut2_mvvm.network.ConnectionManager
import app.coconut2.sample.R
import app.coconut2.sample.data.local.entity.UserEntity
import app.coconut2.sample.databinding.ActivityMainBinding
import app.coconut2.sample.ui.vm.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    @Inject lateinit var connectionManager: ConnectionManager

    private var userLatest: UserEntity? = null
    private var allUserSize: Int? = null

    private val viewModel: UserViewModel by viewModels()

    override fun inflateBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setupConnection(connectionManager) { isConnect ->
            showShortToast("Inside observer connection = $isConnect")
        }

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

        val hasLast = (allUserSize ?: 0) > 1

        if (userLatest != null)
            viewModel.deleteLastUser(userLatest!!)
        binding.btnDeleteData.isVisible = hasLast
    }

    private fun doInsertData() {
        val languages = viewModel.userLanguage
        val user = UserEntity(
            name = "Dodol 2 Jawa",
            address = "Pondok Indah",
            age = 18
        )
        viewModel.insert(user)
        userLatest = user

        val hasData = userLatest != null
        binding.btnDeleteData.isVisible = hasData
    }

    private fun observerHandler() {
        viewModel.allUsers.observe(this) {
            if (it.isNotEmpty()) {
                userLatest = it.last()
                allUserSize = it.size
                Toast.makeText(this, "Data Size = $allUserSize", Toast.LENGTH_SHORT).show()
            } else userLatest = null
        }
    }
}