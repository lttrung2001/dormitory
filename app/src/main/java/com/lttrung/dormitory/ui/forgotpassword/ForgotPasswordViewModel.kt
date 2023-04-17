package com.lttrung.dormitory.ui.forgotpassword

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lttrung.dormitory.domain.usecases.ForgotPasswordUseCase
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
class ForgotPasswordViewModel @Inject constructor(
    private val forgotPasswordUseCase: ForgotPasswordUseCase
) : ViewModel() {
    private val composite = CompositeDisposable()
    internal val forgotPasswordLiveData: MutableLiveData<Resource<Unit>> =
        MutableLiveData<Resource<Unit>>()
    private val forgotPasswordObserver: Consumer<Unit> by lazy {
        Consumer {
            forgotPasswordLiveData.postValue(Resource.Success(it))
        }
    }
    private var forgotPasswordDisposable: Disposable? = null
    internal fun forgotPassword(username: String) {
        // Async
        viewModelScope.launch(Dispatchers.IO) {
            forgotPasswordLiveData.postValue(Resource.Loading())
            forgotPasswordDisposable?.let { composite.remove(it) }
            forgotPasswordDisposable =
                forgotPasswordUseCase.execute(username)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(forgotPasswordObserver) { t: Throwable ->
                        t.message?.let { forgotPasswordLiveData.postValue(Resource.Error(t.message!!)) }
                    }
            forgotPasswordDisposable?.let { composite.add(it) }
        }.start()
    }
}