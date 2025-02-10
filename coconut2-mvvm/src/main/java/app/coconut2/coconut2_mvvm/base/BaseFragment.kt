package app.coconut2.coconut2_mvvm.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewbinding.ViewBinding
import app.coconut2.coconut2_mvvm.core.ui.ext.avoidDoubleClicks
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseFragment<VB : ViewBinding> : Fragment(), View.OnClickListener {
    private var _binding: ViewBinding? = null
    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB

    @Suppress("UNCHECKED_CAST")
    protected val binding: VB
        get() = _binding as VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater, container, false)
        return requireNotNull(_binding).root
    }

    override fun onClick(v: View?) {
        v.avoidDoubleClicks()
    }

    fun BottomSheetDialogFragment.show(fragmentManager: FragmentManager, tag: String? = null) {
        show(fragmentManager, tag)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    abstract fun setup()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun showDialogFragment(fragment: BottomSheetDialogFragment) {
        fragment.show(childFragmentManager, "tag")
    }
}
