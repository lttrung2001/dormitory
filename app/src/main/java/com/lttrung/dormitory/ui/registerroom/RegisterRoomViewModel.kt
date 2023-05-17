package com.lttrung.dormitory.ui.registerroom

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lttrung.dormitory.domain.data.local.UserLocal
import com.lttrung.dormitory.domain.usecases.RegisterRoomUseCase
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
class RegisterRoomViewModel @Inject constructor(
    private val registerRoomUseCase: RegisterRoomUseCase,
    private val userLocal: UserLocal
) : ViewModel() {
    internal var roomId = 0
    internal val studentId = viewModelScope.launch(Dispatchers.IO) {
        userLocal.getCurrentUser()?.studentId
    }

    internal val registerRoomLiveData: MutableLiveData<Resource<Boolean>> by lazy {
        MutableLiveData<Resource<Boolean>>()
    }
    private val composite: CompositeDisposable by lazy {
        CompositeDisposable()
    }
    private var registerRoomDisposable: Disposable? = null
    private val registerRoomObserver: Consumer<Boolean> by lazy {
        Consumer {
            registerRoomLiveData.postValue(Resource.Success(it))
        }
    }

    internal fun registerRoom(roomId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            registerRoomLiveData.postValue(Resource.Loading())
            registerRoomDisposable?.let { composite.remove(it) }
            registerRoomDisposable = registerRoomUseCase.execute(roomId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(registerRoomObserver) { t ->
                    t.message?.let { registerRoomLiveData.postValue(Resource.Error(t.message!!)) }
                }
            registerRoomDisposable?.let { composite.add(it) }
        }.start()
    }
}