package ir.nima.balootchallenge.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import ir.nima.balootchallenge.R
import ir.nima.balootchallenge.data.db.entities.ArticleEntity
import ir.nima.balootchallenge.databinding.ItemBinding

class LocalNewsAdapter(private val context: Context) :
    ListAdapter<ArticleEntity, LocalNewsAdapter.MyViewHolder>(userDifferCallback) {


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind((currentItem))
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
                        Toast.makeText(context,"check your internet connection and try later.",Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }

        fun bind(article: ArticleEntity) {
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
        private val userDifferCallback = object : DiffUtil.ItemCallback<ArticleEntity>() {
            override fun areItemsTheSame(oldItem: ArticleEntity, newItem: ArticleEntity): Boolean =
                oldItem.title == newItem.title

            override fun areContentsTheSame(oldItem: ArticleEntity, newItem: ArticleEntity): Boolean =
                oldItem == newItem

        }
    }

}