package com.lttrung.dormitory.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lttrung.dormitory.database.data.network.login.LoginResponseBody
import com.lttrung.dormitory.exceptions.UnverifiedEmailException
import com.lttrung.dormitory.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.Action
import io.reactivex.rxjava3.functions.Consumer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {
    private val composite = CompositeDisposable()
    var loginLiveData: MutableLiveData<Resource<LoginResponseBody>> =
        MutableLiveData<Resource<LoginResponseBody>>()
    private val loginObserver: Consumer<LoginResponseBody> by lazy {
        Consumer {
            loginLiveData.postValue(Resource.Success(it))
        }
    }
    private var loginDisposable: Disposable? = null
    fun login(username: String, password: String) {
        // Async
        viewModelScope.launch(Dispatchers.IO) {
            loginLiveData.postValue(Resource.Loading())
            loginDisposable?.let { composite.remove(it) }
            loginDisposable =
                loginUseCase.login(username, password).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(loginObserver) { t: Throwable ->
                        loginLiveData.postValue(Resource.Error(t))
                    }
            loginDisposable?.let { composite.add(it) }
        }.start()
    }
}