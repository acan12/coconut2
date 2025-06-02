package app.coconut2.sample.ui.screen.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import app.coconut2.coconut2_mvvm.base.BaseFragment
import app.coconut2.sample.databinding.FragmentDemoBinding

class DemoFragment : BaseFragment<FragmentDemoBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDemoBinding
        get() = FragmentDemoBinding::inflate

    override fun setup() {
        binding.labelPage.text = "Demo Fragment"
        Toast.makeText(requireContext(), "Setup called", Toast.LENGTH_LONG).show()
    }

}