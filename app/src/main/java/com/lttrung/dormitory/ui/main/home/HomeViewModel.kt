package com.lttrung.dormitory.ui.main.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lttrung.dormitory.database.data.network.models.RoomType
import com.lttrung.dormitory.database.data.network.models.UserProfileResponse
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
    val roomTypesLiveData: MutableLiveData<Resource<List<RoomType>>> by lazy {
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

    fun getRoomTypes() {
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





    val userProfileLiveData: MutableLiveData<Resource<UserProfileResponse>> by lazy {
        MutableLiveData<Resource<UserProfileResponse>>()
    }
    private var userProfileDisposable: Disposable? = null
    private val userProfileObserver: Consumer<UserProfileResponse> by lazy {
        Consumer {
            userProfileLiveData.postValue(Resource.Success(it))
        }
    }

    fun getUserProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            userProfileLiveData.postValue(Resource.Loading())
            userProfileDisposable?.let { composite.remove(it) }
            userProfileDisposable = useCase.getUserProfile().observeOn(AndroidSchedulers.mainThread())
                .subscribe(userProfileObserver) { t ->
                    userProfileLiveData.postValue(Resource.Error(t.message ?: "Unknown error."))
                }
            userProfileDisposable?.let { composite.add(it) }
        }.start()
    }
}