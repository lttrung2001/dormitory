package com.lttrung.dormitory.ui.register

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
class RegisterViewModel @Inject constructor(
    private val useCase: RegisterUseCase
) : ViewModel() {
    val registerLiveData: MutableLiveData<Resource<String>> by lazy {
        MutableLiveData<Resource<String>>()
    }

    private val composite: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    private var registerDisposable: Disposable? = null

    private val registerObserver: Consumer<String> by lazy {
        Consumer {
            registerLiveData.postValue(Resource.Success(it))
        }
    }

    fun register(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            registerLiveData.postValue(Resource.Loading())
            registerDisposable?.let { composite.remove(it) }
            registerDisposable =
                useCase.register(username, password).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(registerObserver) { t ->
                        registerLiveData.postValue(Resource.Error(t))
                    }
            registerDisposable?.let { composite.add(it) }
        }.start()
    }
}