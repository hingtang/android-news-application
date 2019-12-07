package com.hing.newsapplication.ui.profile

import androidx.lifecycle.MutableLiveData
import com.hing.newsapplication.ui.base.BaseViewModel
import io.reactivex.disposables.CompositeDisposable

class ProfileViewModel : BaseViewModel() {
    override val disposables = CompositeDisposable()
    override val errorMessage = MutableLiveData<String>()
    override val isLoading = MutableLiveData<Boolean>()
}
