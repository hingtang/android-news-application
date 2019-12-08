package com.hing.newsapplication.ui.newsdetail

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.hing.newsapplication.R
import com.hing.newsapplication.data.Article
import com.hing.newsapplication.navigators.NewsOriginalLinkNavigator
import com.hing.newsapplication.utils.DateTimeHelper
import com.hing.newsapplication.utils.setVisible
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_news_detail.*
import javax.inject.Inject

class NewsDetailActivity : AppCompatActivity(), NewsOriginalLinkNavigator {

    @Inject
    lateinit var dateTimeHelper: DateTimeHelper

    private var article: Article? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_news_detail)

        article = intent?.extras?.getSerializable(EXTRA_ARTICLE) as Article

        initToolbar()
        initData()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.let {
            if (it.itemId == android.R.id.home) {
                this.finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun openNewsOriginalLink(url: String) {
        startActivity(Intent(this, NewsDetailActivity::class.java).apply {
            putExtra(EXTRA_ARTICLE, article)
        })
    }

    private fun initToolbar() {
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeButtonEnabled(true)
            it.title = getString(R.string.title_news_detail)
        }
    }

    private fun initData() {
        article?.let {
            Glide.with(applicationContext)
                .load(it.urlToImage)
                .placeholder(R.drawable.ic_no_image)
                .into(imgv_news)

            tv_title.text = it.title
            tv_author.text = it.author
            tv_author.setVisible(!tv_author.text.isNullOrEmpty())

            tv_publishedAt.text = dateTimeHelper.getDateString(it.publishedAt)
            tv_publishedAt.setVisible(!tv_publishedAt.text.isNullOrEmpty())

            tv_content.text = if (it.description.isNullOrEmpty()) it.content else it.description
            tv_content.setVisible(!tv_content.text.isNullOrEmpty())

            tv_link.text = it.url
            tv_link.setVisible(!tv_link.text.isNullOrEmpty())
        }
    }

    companion object {
        const val EXTRA_ARTICLE = "ARTICLE"
    }
}
