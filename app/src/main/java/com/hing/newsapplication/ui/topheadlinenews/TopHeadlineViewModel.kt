package com.hing.newsapplication.ui.topheadlinenews

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hing.newsapplication.data.Article
import com.hing.newsapplication.di.IOScheduler
import com.hing.newsapplication.di.MainScheduler
import com.hing.newsapplication.domains.GetTopHeadlineNewsUseCase
import com.hing.newsapplication.ui.base.BaseViewModel
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class TopHeadlineViewModel @Inject constructor(
    private val getTopHeadlineNewsUseCase: GetTopHeadlineNewsUseCase,
    @MainScheduler private val mainScheduler: Scheduler,
    @IOScheduler private val ioScheduler: Scheduler
) : BaseViewModel() {
    override val disposables = CompositeDisposable()
    override val errorMessage = MutableLiveData<String>()
    override val isLoading = MutableLiveData<Boolean>()

    private val _newsList = MutableLiveData<List<Article>>()
    private var _currentPage: Int = 1

    val newsList: LiveData<List<Article>>
        get() = _newsList

    fun getCurrentPage() = _currentPage

    fun getNewsList(page: Int, pageSize: Int) {
        getTopHeadlineNewsUseCase.execute(page, pageSize)
            .subscribeOn(ioScheduler)
            .observeOn(mainScheduler)
            .doOnSubscribe {
                isLoading.value = page == 1
            }
            .subscribe({
                _currentPage = page
                _newsList.value = it.articles
                isLoading.value = false
            }, {
                errorMessage.value = "${it.message}"
                isLoading.value = false
            }).let { disposables.add(it) }
    }
}
