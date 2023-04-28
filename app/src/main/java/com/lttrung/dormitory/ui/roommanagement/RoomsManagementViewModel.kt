package com.lttrung.dormitory.ui.roommanagement

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lttrung.dormitory.domain.data.network.models.Room
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
class RoomsManagementViewModel @Inject constructor(
    private val useCase: RoomsManagementUseCase
) : ViewModel() {
    internal val roomsManagementLiveData: MutableLiveData<Resource<List<Room>>> by lazy {
        MutableLiveData<Resource<List<Room>>>()
    }

    private val composite: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    private var roomsDisposable: Disposable? = null

    private val roomsObserver: Consumer<List<Room>> by lazy {
        Consumer {
            roomsManagementLiveData.postValue(Resource.Success(it))
        }
    }

    internal fun getRooms(roomTypeId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            roomsManagementLiveData.postValue(Resource.Loading())
            roomsDisposable?.let { composite.remove(it) }
            roomsDisposable =
                useCase.getRooms(roomTypeId).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(roomsObserver) { t ->
                        t.message?.let { roomsManagementLiveData.postValue(Resource.Error(t.message!!)) }
                    }
            roomsDisposable?.let { composite.add(it) }
        }.start()
    }
}