package com.hing.newsapplication.ui.topheadlinenews

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hing.newsapplication.R
import com.hing.newsapplication.data.Article
import com.hing.newsapplication.utils.DateTimeHelper
import com.hing.newsapplication.utils.setVisible


/**
 * Created by HingTang on 2019-12-08.
 */
class NewsListAdapter(
    private val onItemClick: (article: Article) -> Unit,
    private val dateTimeHelper: DateTimeHelper
) : RecyclerView.Adapter<NewsListAdapter.NewsViewHolder>() {
    private val newsList: MutableList<Article> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(newsList[position])
    }

    fun updateNewsList(news: List<Article>) {
        newsList.clear()
        newsList.addAll(news)
        notifyDataSetChanged()
    }

    fun insertNewsList(news: List<Article>) {
        newsList.addAll(news)
        notifyItemRangeInserted(newsList.size - news.size, news.size)
    }

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val newsImage = lazy { itemView.findViewById<ImageView>(R.id.imgv_news) }
        private val titleText = lazy { itemView.findViewById<TextView>(R.id.tv_title) }
        private val descriptionText = lazy { itemView.findViewById<TextView>(R.id.tv_description) }
        private val authorText = lazy { itemView.findViewById<TextView>(R.id.tv_author) }
        private val publishedAtText = lazy { itemView.findViewById<TextView>(R.id.tv_publishedAt) }

        init {
            itemView.setOnClickListener { onItemClick(newsList[adapterPosition]) }
        }

        @SuppressLint("SetTextI18n")
        fun bind(article: Article) {
            Glide.with(itemView.context)
                .load(article.urlToImage)
                .placeholder(R.drawable.ic_no_image)
                .into(newsImage.value)

            titleText.value.text = article.title

            descriptionText.value.text =
                if (article.description.isNullOrEmpty()) article.content else article.description
            descriptionText.value.setVisible(!descriptionText.value.text.isNullOrEmpty())

            authorText.value.text = article.author
            authorText.value.setVisible(!authorText.value.text.isNullOrEmpty())

            publishedAtText.value.text = dateTimeHelper.getDateString(article.publishedAt)
            publishedAtText.value.setVisible(!publishedAtText.value.text.isNullOrEmpty())
        }
    }
}
