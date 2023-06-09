package com.lttrung.dormitory.ui.roommanagement.addRoomManagement

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lttrung.dormitory.domain.data.network.models.Room
import com.lttrung.dormitory.domain.usecases.AddRoomManagementUseCase
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
class AddRoomManagementViewModel @Inject constructor(
    private val useCase: AddRoomManagementUseCase
) : ViewModel() {
    internal val addRoomManagementLiveData: MutableLiveData<Resource<Room>> by lazy {
        MutableLiveData<Resource<Room>>()
    }

    private val composite: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    private var addRoomDisposable: Disposable? = null

    private val addRoomObserver: Consumer<Room> by lazy {
        Consumer {
            addRoomManagementLiveData.postValue(Resource.Success(it));
        }
    }

    internal fun addRoom(room: Room) {
        viewModelScope.launch(Dispatchers.IO) {
            addRoomManagementLiveData.postValue(Resource.Loading())
            addRoomDisposable?.let { composite.remove(it) }
            addRoomDisposable =
                useCase.execute(room).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(addRoomObserver) { t ->
                        t.message?.let { addRoomManagementLiveData.postValue(Resource.Error(t.message!!)) }
                    }
            addRoomDisposable?.let { composite.add(it) }
        }.start()
    }
}