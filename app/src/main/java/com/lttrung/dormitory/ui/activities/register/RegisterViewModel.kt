package com.lttrung.dormitory.ui.activities.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lttrung.dormitory.domain.usecases.RegisterUseCase
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
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {
    internal val registerLiveData: MutableLiveData<Resource<Unit>> by lazy {
        MutableLiveData<Resource<Unit>>()
    }

    private val composite: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    private var registerDisposable: Disposable? = null

    private val registerObserver: Consumer<Unit> by lazy {
        Consumer {
            registerLiveData.postValue(Resource.Success(it))
        }
    }

    internal fun register(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            registerLiveData.postValue(Resource.Loading())
            registerDisposable?.let { composite.remove(it) }
            registerDisposable =
                registerUseCase.execute(username, password).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(registerObserver) { t ->
                        t.message?.let { registerLiveData.postValue(Resource.Error(t.message!!)) }
                    }
            registerDisposable?.let { composite.add(it) }
        }.start()
    }
}