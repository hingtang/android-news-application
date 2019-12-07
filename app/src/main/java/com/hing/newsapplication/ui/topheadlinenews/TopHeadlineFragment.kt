package com.hing.newsapplication.ui.topheadlinenews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hing.newsapplication.R
import com.hing.newsapplication.data.Article
import com.hing.newsapplication.databinding.TopHeadlineFragmentBinding
import com.hing.newsapplication.listeners.OnLoadMoreListener
import com.hing.newsapplication.navigators.NewsItemNavigator
import com.hing.newsapplication.utils.DateTimeHelper
import com.hing.newsapplication.utils.NetworkHelper
import com.hing.newsapplication.utils.showSnackbar
import com.hing.newsapplication.utils.showToast
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class TopHeadlineFragment : Fragment(), NewsItemNavigator {
    @Inject
    lateinit var networkHelper: NetworkHelper
    @Inject
    lateinit var dateTimeHelper: DateTimeHelper
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: TopHeadlineViewModel
    private lateinit var newsListAdapter: NewsListAdapter
    private lateinit var binding: TopHeadlineFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        AndroidSupportInjection.inject(this)
        binding = DataBindingUtil.inflate(inflater, R.layout.top_headline_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TopHeadlineViewModel::class.java)
        binding.lifecycleOwner = this

        binding.viewModel = viewModel
        initRecyclerView()
        initData()
    }

    override fun onStart() {
        super.onStart()
        if (!networkHelper.isConnectedToInternet()) {
            binding.newsList.showSnackbar(context?.getString(R.string.no_internet_connection) ?: "")
        }
    }

    override fun openNewsDetail(article: Article) {
    }

    private fun initData() {
        with(viewModel) {
            newsList.observe(viewLifecycleOwner, Observer {
                if (getCurrentPage() > 1) {
                    newsListAdapter.insertNewsList(it)
                } else {
                    newsListAdapter.updateNewsList(it)
                }
            })

            isLoading.observe(viewLifecycleOwner, Observer {
                progress_bar.visibility = if (isLoading.value == true) View.VISIBLE else View.GONE
            })

            errorMessage.observe(viewLifecycleOwner, Observer {
                binding.newsList.showToast(it)
            })

            getNewsList(1, PER_PAGE_ITEM)
        }
    }

    private fun initRecyclerView() {
        newsListAdapter = NewsListAdapter({ article: Article -> openNewsDetail(article) }, dateTimeHelper)
        val linearLayoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)

        with(binding.newsList) {
            adapter = newsListAdapter
            setHasFixedSize(true)
            setItemViewCacheSize(20)
            addOnScrollListener(object : OnLoadMoreListener(VISIBLE_THRESHOLD, linearLayoutManager) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                    val currentPage = viewModel.newsList.value?.size ?: 0 / PER_PAGE_ITEM
                    viewModel.getNewsList(currentPage, PER_PAGE_ITEM)
                }
            })
        }
    }

    companion object {
        private const val VISIBLE_THRESHOLD = 10
        private const val PER_PAGE_ITEM = 30
    }
}
