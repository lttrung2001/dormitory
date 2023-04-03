package com.lttrung.dormitory.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lttrung.dormitory.database.data.network.login.LoginResponseBody
import com.lttrung.dormitory.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.Consumer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {
    private val composite = CompositeDisposable()
    var loginLiveData: MutableLiveData<Resource<LoginResponseBody>> =
        MutableLiveData<Resource<LoginResponseBody>>()
    private val loginObserver: Consumer<LoginResponseBody> =
        Consumer { loginResponseBody: LoginResponseBody ->
            loginLiveData.postValue(
                Resource.Success(loginResponseBody)
            )
        }
    private var loginDisposable: Disposable? = null
    fun login(username: String?, password: String?) {
        // Async
        viewModelScope.launch(Dispatchers.IO) {
            loginLiveData.postValue(Resource.Loading())
            if (loginDisposable != null) {
                composite.remove(loginDisposable!!)
            }
            loginDisposable =
                loginUseCase.login(username!!, password!!).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(loginObserver) { t: Throwable ->
                        loginLiveData.postValue(
                            Resource.Error(
                                Objects.requireNonNull<String?>(t.message)
                            )
                        )
                    }
            composite.add(loginDisposable!!)
        }.start()
    }
}