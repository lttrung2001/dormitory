package com.lttrung.dormitory.ui.activities.verifycode

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lttrung.dormitory.domain.usecases.VerifyCodeUseCase
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
    private val verifyCodeUseCase: VerifyCodeUseCase
) : ViewModel() {
    internal val verifyCodeLiveData: MutableLiveData<Resource<Unit>> by lazy {
        MutableLiveData<Resource<Unit>>()
    }
    private val composite: CompositeDisposable by lazy {
        CompositeDisposable()
    }
    private var verifyCodeDisposable: Disposable? = null
    private val verifyCodeObserver: Consumer<Unit> by lazy {
        Consumer {
            verifyCodeLiveData.postValue(Resource.Success(it))
        }
    }

    internal fun verifyCode(username: String, password: String, otp: String) {
        viewModelScope.launch(Dispatchers.IO) {
            verifyCodeLiveData.postValue(Resource.Loading())
            verifyCodeDisposable?.let { composite.remove(it) }
            verifyCodeDisposable = verifyCodeUseCase.execute(username, password, otp)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(verifyCodeObserver) { t ->
                    t.message?.let { verifyCodeLiveData.postValue(Resource.Error(t.message!!)) }
                }
            verifyCodeDisposable?.let { composite.add(it) }
        }.start()
    }
}