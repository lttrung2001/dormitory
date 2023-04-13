package com.lttrung.dormitory.ui.main.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lttrung.dormitory.database.data.network.models.FetchRoomContractResponse
import com.lttrung.dormitory.database.data.network.models.RoomType
import com.lttrung.dormitory.database.data.network.models.UserProfile
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
class HomeViewModel @Inject constructor(
    private val useCase: HomeUseCase
) : ViewModel() {
    internal val roomTypesLiveData: MutableLiveData<Resource<List<RoomType>>> by lazy {
        MutableLiveData<Resource<List<RoomType>>>()
    }
    private val composite: CompositeDisposable by lazy {
        CompositeDisposable()
    }
    private var roomTypesDisposable: Disposable? = null
    private val roomTypesObserver: Consumer<List<RoomType>> by lazy {
        Consumer {
            roomTypesLiveData.postValue(Resource.Success(it))
        }
    }

    internal fun getRoomTypes() {
        viewModelScope.launch(Dispatchers.IO) {
            roomTypesLiveData.postValue(Resource.Loading())
            roomTypesDisposable?.let { composite.remove(it) }
            roomTypesDisposable = useCase.getRoomTypes().observeOn(AndroidSchedulers.mainThread())
                .subscribe(roomTypesObserver) { t ->
                    roomTypesLiveData.postValue(Resource.Error(t.message ?: "Unknown error."))
                }
            roomTypesDisposable?.let { composite.add(it) }
        }.start()
    }





    internal val roomContractLiveData: MutableLiveData<Resource<FetchRoomContractResponse>> by lazy {
        MutableLiveData<Resource<FetchRoomContractResponse>>()
    }
    private var roomContractDisposable: Disposable? = null
    private val roomContractObserver: Consumer<FetchRoomContractResponse> by lazy {
        Consumer {
            roomContractLiveData.postValue(Resource.Success(it))
        }
    }

    internal fun getRoomContract() {
        viewModelScope.launch(Dispatchers.IO) {
            roomContractLiveData.postValue(Resource.Loading())
            roomContractDisposable?.let { composite.remove(it) }
            roomContractDisposable = useCase.getRoomContract().observeOn(AndroidSchedulers.mainThread())
                .subscribe(roomContractObserver) { t ->
                    roomContractLiveData.postValue(Resource.Error(t.message ?: "Unknown error."))
                }
            roomContractDisposable?.let { composite.add(it) }
        }.start()
    }
}