package com.lttrung.dormitory.ui.roommanagement.updateRoomManagement

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lttrung.dormitory.domain.data.network.models.Room
import com.lttrung.dormitory.domain.usecases.UpdateRoomManagementUseCase
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
class UpdateRoomManagementViewModel @Inject constructor(
    private val useCase: UpdateRoomManagementUseCase
) : ViewModel() {
    internal val updateRoomManagementLiveData: MutableLiveData<Resource<Room>> by lazy {
        MutableLiveData<Resource<Room>>()
    }

    private val composite: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    private var updateRoomDisposable: Disposable? = null

    private val updateRoomObserver: Consumer<Room> by lazy {
        Consumer {
            updateRoomManagementLiveData.postValue(Resource.Success(it));
        }
    }

    internal fun updateRoom(room: Room) {
        viewModelScope.launch(Dispatchers.IO) {
            updateRoomManagementLiveData.postValue(Resource.Loading())
            updateRoomDisposable?.let { composite.remove(it) }
            updateRoomDisposable =
                useCase.execute(room).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(updateRoomObserver) { t ->
                        t.message?.let { updateRoomManagementLiveData.postValue(Resource.Error(t.message!!)) }
                    }
            updateRoomDisposable?.let { composite.add(it) }
        }.start()
    }
}