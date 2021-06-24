package ir.nima.balootchallenge.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.dynamicfeatures.Constants
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import dagger.hilt.android.AndroidEntryPoint
import ir.nima.balootchallenge.R
import ir.nima.balootchallenge.databinding.FragmentPofileBinding
import ir.nima.balootchallenge.ui.adapter.BottomSheetDialog
import ir.nima.balootchallenge.utils.Constant

@AndroidEntryPoint
class ProfileFragment : BaseFragment(R.layout.fragment_pofile) {
    private lateinit var _binding: FragmentPofileBinding
    private val binding get() = _binding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentPofileBinding.bind(view)
        binding.apply {
            Glide.with(requireContext())
                .load(Constant.IMAGE_URL)
                .placeholder(R.drawable.loading)
                .centerCrop()
                .error(R.drawable.ic_baseline_error_outline_24)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(avatar)
            aboutMe.setOnClickListener {
                val bottomSheetDialog = BottomSheetDialog()
                bottomSheetDialog.show(childFragmentManager,"bottomsheet")
            }
        }
    }
}