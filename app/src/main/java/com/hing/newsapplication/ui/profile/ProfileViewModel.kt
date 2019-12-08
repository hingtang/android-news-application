package com.hing.newsapplication.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hing.newsapplication.di.IOScheduler
import com.hing.newsapplication.di.MainScheduler
import com.hing.newsapplication.domains.GetKeywordUseCase
import com.hing.newsapplication.domains.GetUsernameUseCase
import com.hing.newsapplication.domains.RegisterUsernameUseCase
import com.hing.newsapplication.domains.UpdateKeywordUseCase
import com.hing.newsapplication.ui.base.BaseViewModel
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val registerUsernameUseCase: RegisterUsernameUseCase,
    private val updateKeywordUseCase: UpdateKeywordUseCase,
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

    val username: LiveData<String>
        get() = _username

    val keyword: LiveData<String>
        get() = _keyword

    fun registerUsername(username: String) {
        registerUsernameUseCase.execute(username)
            .subscribeOn(ioScheduler)
            .observeOn(mainScheduler)
            .doOnSubscribe {
                isLoading.value = true
            }
            .subscribe({
                _username.value = username
                isLoading.value = false
            }, {
                errorMessage.value = "${it.message}"
                isLoading.value = false
            }).let { disposables.add(it) }
    }

    fun updateKeyword(keyword: String) {
        updateKeywordUseCase.execute(keyword)
            .subscribeOn(ioScheduler)
            .observeOn(mainScheduler)
            .doOnSubscribe {
                isLoading.value = true
            }
            .subscribe({
                _keyword.value = keyword
                isLoading.value = false
                errorMessage.value = "Save successfully!"
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
            }, {
                errorMessage.value = "${it.message}"
                isLoading.value = false
            }).let { disposables.add(it) }
    }

    fun getUsername(){
        getUsernameUseCase.execute()
            .subscribeOn(ioScheduler)
            .observeOn(mainScheduler)
            .doOnSubscribe {
                isLoading.value = true
            }
            .subscribe({
                _username.value = it
                isLoading.value = false
            }, {
                errorMessage.value = "${it.message}"
                isLoading.value = false
            }).let { disposables.add(it) }
    }
}
