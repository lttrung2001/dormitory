package com.lttrung.dormitory.ui.verifycode

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lttrung.dormitory.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.Consumer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VerifyCodeViewModel @Inject constructor(
    private val useCase: VerifyCodeUseCase
) : ViewModel() {
    val verifyCodeLiveData: MutableLiveData<Resource<String>> by lazy {
        MutableLiveData<Resource<String>>()
    }
    private val composite: CompositeDisposable by lazy {
        CompositeDisposable()
    }
    private var verifyCodeDisposable: Disposable? = null
    private val verifyCodeObserver: Consumer<String> by lazy {
        Consumer {
            verifyCodeLiveData.postValue(Resource.Success(it))
        }
    }

    fun verifyCode(username: String, password: String, otp: String) {
        viewModelScope.launch(Dispatchers.IO) {
            verifyCodeLiveData.postValue(Resource.Loading())
            verifyCodeDisposable?.let { composite.remove(it) }
            verifyCodeDisposable = useCase.verifyCode(username, password, otp)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(verifyCodeObserver) { t ->
                    t.message?.let { verifyCodeLiveData.postValue(Resource.Error(t.message!!)) }
                }
            verifyCodeDisposable?.let { composite.add(it) }
        }.start()
    }
}