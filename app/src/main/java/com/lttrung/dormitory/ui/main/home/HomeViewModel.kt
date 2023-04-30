package com.lttrung.dormitory.ui.main.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lttrung.dormitory.domain.data.network.models.FetchRoomContractResponse
import com.lttrung.dormitory.domain.data.network.models.RoomType
import com.lttrung.dormitory.domain.data.network.models.StudentProfile
import com.lttrung.dormitory.domain.usecases.*
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
    private val getRoomTypesUseCase: GetRoomTypesUseCase,
    private val getRoomContractUseCase: GetRoomContractUseCase,
    private val cancelContractUseCase: CancelContractUseCase,
    private val extendRoomUseCase: ExtendRoomUseCase,
    private val viewProfileUseCase: ViewProfileUseCase
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
            roomTypesDisposable =
                getRoomTypesUseCase.execute().observeOn(AndroidSchedulers.mainThread())
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
            roomContractDisposable =
                getRoomContractUseCase.execute().observeOn(AndroidSchedulers.mainThread())
                    .subscribe(roomContractObserver) { t ->
                        roomContractLiveData.postValue(
                            Resource.Error(
                                t.message ?: "Unknown error."
                            )
                        )
                    }
            roomContractDisposable?.let { composite.add(it) }
        }.start()
    }


    internal val cancelContractLiveData: MutableLiveData<Resource<Unit>> by lazy {
        MutableLiveData<Resource<Unit>>()
    }
    private var cancelContractDisposable: Disposable? = null
    private val cancelContractObserver: Consumer<Unit> by lazy {
        Consumer {
            cancelContractLiveData.postValue(Resource.Success(it))
        }
    }

    internal fun cancelContract() {
        viewModelScope.launch(Dispatchers.IO) {
            cancelContractLiveData.postValue(Resource.Loading())
            cancelContractDisposable?.let { composite.remove(it) }
            cancelContractDisposable =
                cancelContractUseCase.execute().observeOn(AndroidSchedulers.mainThread())
                    .subscribe(cancelContractObserver) { t ->
                        cancelContractLiveData.postValue(
                            Resource.Error(
                                t.message ?: "Unknown error."
                            )
                        )
                    }
            cancelContractDisposable?.let { composite.add(it) }
        }.start()
    }


    internal val extendRoomLiveData: MutableLiveData<Resource<Unit>> by lazy {
        MutableLiveData<Resource<Unit>>()
    }
    private var extendRoomDisposable: Disposable? = null
    private val extendRoomObserver: Consumer<Unit> by lazy {
        Consumer {
            extendRoomLiveData.postValue(Resource.Success(it))
        }
    }

    internal fun extendRoom() {
        viewModelScope.launch(Dispatchers.IO) {
            extendRoomLiveData.postValue(Resource.Loading())
            extendRoomDisposable?.let { composite.remove(it) }
            extendRoomDisposable =
                extendRoomUseCase.execute().observeOn(AndroidSchedulers.mainThread())
                    .subscribe(extendRoomObserver) { t ->
                        extendRoomLiveData.postValue(Resource.Error(t.message ?: "Unknown error."))
                    }
            extendRoomDisposable?.let { composite.add(it) }
        }.start()
    }



    internal val viewProfileLiveData: MutableLiveData<Resource<StudentProfile>> by lazy {
        MutableLiveData<Resource<StudentProfile>>()
    }
    private var viewProfileDisposable: Disposable? = null
    private val viewProfileObserver: Consumer<StudentProfile> by lazy {
        Consumer {
            viewProfileLiveData.postValue(Resource.Success(it))
        }
    }

    internal fun viewProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            viewProfileLiveData.postValue(Resource.Loading())
            viewProfileDisposable?.let { composite.remove(it) }
            viewProfileDisposable =
                    viewProfileUseCase.execute().observeOn(AndroidSchedulers.mainThread())
                    .subscribe(viewProfileObserver) { t ->
                        viewProfileLiveData.postValue(Resource.Error(t.message ?: "Unknown error."))
                    }
            extendRoomDisposable?.let { composite.add(it) }
        }.start()
    }
}