package ir.nima.balootchallenge.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import dagger.hilt.android.AndroidEntryPoint
import ir.nima.balootchallenge.R
import ir.nima.balootchallenge.data.api.model.Article
import ir.nima.balootchallenge.databinding.FragmentDetailNewsBinding
import ir.nima.balootchallenge.databinding.FragmentNewsBinding

@AndroidEntryPoint
class DetailNewsFragment : BaseFragment(R.layout.fragment_detail_news) {
    private lateinit var _binding: FragmentDetailNewsBinding
    private val binding get() = _binding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentDetailNewsBinding.bind(view)
        binding.apply {
            val arg =arguments?.getParcelable<Article>("article")
            title.text =arg?.title
            description.text = arg?.description
            content.text =arg?.content
            Glide.with(requireContext())
                .load(arg?.urlToImage)
                .placeholder(R.drawable.loading)
                .centerCrop()
                .error(R.drawable.ic_baseline_error_outline_24)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(image)
        }
    }

}