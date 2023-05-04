package com.lttrung.dormitory.ui.roomManagement.detailRoomManagement

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lttrung.dormitory.domain.data.network.models.Room
import com.lttrung.dormitory.domain.usecases.DeleteRoomManagementUseCase
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
class DetailRoomManagementViewModel @Inject constructor(
    private val useCase: DeleteRoomManagementUseCase
) : ViewModel() {
    internal val deleteRoomManagementLiveData: MutableLiveData<Resource<Room>> by lazy {
        MutableLiveData<Resource<Room>>()
    }

    private val composite: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    private var roomsDisposable: Disposable? = null

    private val roomsObserver: Consumer<Room> by lazy {
        Consumer {
            deleteRoomManagementLiveData.postValue(Resource.Success(it));
        }
    }

    internal fun deleteRoom(room: Room) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteRoomManagementLiveData.postValue(Resource.Loading())
            roomsDisposable?.let { composite.remove(it) }
            roomsDisposable =
                useCase.execute(room).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(roomsObserver) { t ->
                        t.message?.let { deleteRoomManagementLiveData.postValue(Resource.Error(t.message!!)) }
                    }
            roomsDisposable?.let { composite.add(it) }
        }.start()
    }
}