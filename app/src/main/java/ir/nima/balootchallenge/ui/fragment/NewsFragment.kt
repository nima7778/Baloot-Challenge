package ir.nima.balootchallenge.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import ir.nima.balootchallenge.R
import ir.nima.balootchallenge.data.api.model.Article
import ir.nima.balootchallenge.data.db.entities.ArticleEntity
import ir.nima.balootchallenge.databinding.FragmentNewsBinding
import ir.nima.balootchallenge.ui.adapter.LocalNewsAdapter
import ir.nima.balootchallenge.ui.adapter.NewsAdapter
import ir.nima.balootchallenge.ui.adapter.StateLoaderAdapter
import ir.nima.balootchallenge.ui.viewModel.NewsViewModel

class NewsFragment : BaseFragment(R.layout.fragment_news), NewsAdapter.onClick {
    private lateinit var _binding: FragmentNewsBinding
    private val binding get() = _binding
    private val viewModel: NewsViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentNewsBinding.bind(view)
        val adapter = NewsAdapter(this)
        val offlineAdapter = LocalNewsAdapter(requireContext())
        binding.apply {
            recyclerview.setHasFixedSize(true)
            recyclerview.layoutManager = LinearLayoutManager(requireContext())

        }
        if (isNetworkConnected(requireContext())){
            viewModel.all.observe(viewLifecycleOwner) {
                binding.recyclerview.adapter = adapter
                binding.recyclerview.adapter = adapter.withLoadStateHeaderAndFooter(
                    header = StateLoaderAdapter { adapter.retry() },
                    footer = StateLoaderAdapter { adapter.retry() }
                )
                adapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        }else{
            viewModel.getAll()
            viewModel.getDataFromDb.observe(viewLifecycleOwner){
                binding.recyclerview.adapter = offlineAdapter
                offlineAdapter.submitList(it)
            }
        }


        adapter.addLoadStateListener { loadState ->
            binding.apply {
                progressbar.isVisible = loadState.source.refresh is LoadState.Loading
                recyclerview.isVisible = loadState.source.refresh is LoadState.NotLoading
                retry.isVisible = loadState.source.refresh is LoadState.Error
                textViewError.isVisible = loadState.source.refresh is LoadState.Error
                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> {
                        retry.visibility = View.VISIBLE
                        loadState.refresh as LoadState.Error

                    }
                    else -> null
                }
                errorState?.let {
                    textViewError.text = it.error.message


                }
                // No result
                if (loadState.source.refresh is LoadState.NotLoading &&
                    loadState.append.endOfPaginationReached &&
                    adapter.itemCount < 1
                ) {

                    recyclerview.isVisible = false
                    textViewEmpty.isVisible = true

                } else {
                    textViewEmpty.isVisible = false
                }

                retry.setOnClickListener(View.OnClickListener {
                    adapter.retry()
                })
            }
        }
    }

    override fun itemClick(article: Article) {
        val bundle = Bundle()
        bundle.putParcelable("article",article)
        findNavController().navigate(R.id.action_mainPageFragment_to_detailNewsFragment,bundle)
    }

    override fun insertToDb(article: Article) {
        viewModel.insert(
            ArticleEntity(
                0,
                "",
                article.content,
                article.description,
                article.publishedAt,
                article.title,
                article.url,
                article.urlToImage
            )
        )
    }
}