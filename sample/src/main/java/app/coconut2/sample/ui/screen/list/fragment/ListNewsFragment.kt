package app.coconut2.sample.ui.screen.list.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import app.coconut2.coconut2_mvvm.base.BaseFragment
import app.coconut2.sample.databinding.FragmentListNewsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListNewsFragment : BaseFragment<FragmentListNewsBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentListNewsBinding
        get() = FragmentListNewsBinding::inflate

    override fun setup() {

    }

}