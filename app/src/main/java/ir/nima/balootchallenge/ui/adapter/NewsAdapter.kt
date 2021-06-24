package ir.nima.balootchallenge.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import ir.nima.balootchallenge.R
import ir.nima.balootchallenge.data.api.model.Article
import ir.nima.balootchallenge.databinding.ItemBinding

class NewsAdapter(private val clickListener: onClick) :
    PagingDataAdapter<Article, NewsAdapter.MyViewHolder>(userDifferCallback) {


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind((currentItem))
            clickListener.insertToDb(currentItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    inner class MyViewHolder(private val binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    if (item != null) {
                        clickListener.itemClick(item)
                    }
                }
            }

        }

        fun bind(article: Article) {
            binding.apply {
                title.text = article.title
                Glide.with(itemView)
                    .load(article.urlToImage)
                    .placeholder(R.drawable.loading)
                    .centerCrop()
                    .error(R.drawable.ic_baseline_error_outline_24)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(image)
            }


        }
    }

    companion object {
        private val userDifferCallback = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean =
                oldItem.author == newItem.author

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean =
                oldItem == newItem

        }
    }

    interface onClick {
        fun itemClick(article: Article)
        fun insertToDb(article: Article)
    }
}