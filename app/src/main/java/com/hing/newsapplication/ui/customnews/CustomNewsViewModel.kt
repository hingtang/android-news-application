package com.hing.newsapplication.ui.customnews

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hing.newsapplication.data.Article
import com.hing.newsapplication.di.IOScheduler
import com.hing.newsapplication.di.MainScheduler
import com.hing.newsapplication.domains.GetCustomNewsUseCase
import com.hing.newsapplication.domains.GetKeywordUseCase
import com.hing.newsapplication.domains.GetUsernameUseCase
import com.hing.newsapplication.ui.base.BaseViewModel
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class CustomNewsViewModel @Inject constructor(
    private val getCustomNewsUseCase: GetCustomNewsUseCase,
    private val getKeywordUseCase: GetKeywordUseCase,
    private val getUsernameUseCase: GetUsernameUseCase,
    @MainScheduler private val mainScheduler: Scheduler,
    @IOScheduler private val ioScheduler: Scheduler
) : BaseViewModel() {
    override val disposables = CompositeDisposable()
    override val errorMessage = MutableLiveData<String>()
    override val isLoading = MutableLiveData<Boolean>()

    private val _username: MutableLiveData<String> = MutableLiveData()
    private var _keyword: MutableLiveData<String> = MutableLiveData()
    private val _newsList = MutableLiveData<List<Article>>()
    private var _currentPage: Int = 1

    val username: LiveData<String>
        get() = _username

    val keyword: LiveData<String>
        get() = _keyword

    val newsList: LiveData<List<Article>>
        get() = _newsList

    fun getCurrentPage() = _currentPage

    fun getNewsList(keyword: String, page: Int, pageSize: Int) {
        getCustomNewsUseCase.execute(keyword, page, pageSize)
            .subscribeOn(ioScheduler)
            .observeOn(mainScheduler)
            .doOnSubscribe {
                isLoading.value = page == 1
            }
            .subscribe({
                _currentPage = page
                _newsList.value = it.articles
                isLoading.value = false
                errorMessage.value = "keyword $keyword"
            }, {
                errorMessage.value = "${it.message}"
                isLoading.value = false
            }).let { disposables.add(it) }
    }

    fun getUserKeyword() {
        getKeywordUseCase.execute()
            .subscribeOn(ioScheduler)
            .observeOn(mainScheduler)
            .doOnSubscribe {
                isLoading.value = true
            }
            .subscribe({
                _keyword.value = it
                isLoading.value = false
                errorMessage.value = "getUserKeyword $it"
            }, {
                errorMessage.value = "${it.message}"
                isLoading.value = false
            }).let { disposables.add(it) }
    }

    fun getUsername() {
        getUsernameUseCase.execute()
            .subscribeOn(ioScheduler)
            .observeOn(mainScheduler)
            .doOnSubscribe {
                isLoading.value = true
            }
            .subscribe({
                _username.value = it
                isLoading.value = false
                errorMessage.value = "getUsername $it"
            }, {
                errorMessage.value = "${it.message}"
                isLoading.value = false
            }).let { disposables.add(it) }
    }
}
