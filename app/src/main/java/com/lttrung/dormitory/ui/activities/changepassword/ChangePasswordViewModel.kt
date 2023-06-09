package com.lttrung.dormitory.ui.activities.changepassword

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lttrung.dormitory.domain.usecases.ChangePasswordUseCase
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
class ChangePasswordViewModel @Inject constructor(private val changePasswordUseCase: ChangePasswordUseCase) :
    ViewModel() {
    private val composite = CompositeDisposable()
    internal val changePasswordLiveData: MutableLiveData<Resource<Unit>> =
        MutableLiveData<Resource<Unit>>()
    private val changePasswordObserver: Consumer<Unit> by lazy {
        Consumer {
            changePasswordLiveData.postValue(Resource.Success(it))
        }
    }
    private var changePasswordDisposable: Disposable? = null
    internal fun changePassword(currentPassword: String, newPassword: String) {
        // Async
        viewModelScope.launch(Dispatchers.IO) {
            changePasswordLiveData.postValue(Resource.Loading())
            changePasswordDisposable?.let { composite.remove(it) }
            changePasswordDisposable =
                changePasswordUseCase.execute(currentPassword, newPassword)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(changePasswordObserver) { t: Throwable ->
                        t.message?.let { changePasswordLiveData.postValue(Resource.Error(t.message!!)) }
                    }
            changePasswordDisposable?.let { composite.add(it) }
        }.start()
    }
}