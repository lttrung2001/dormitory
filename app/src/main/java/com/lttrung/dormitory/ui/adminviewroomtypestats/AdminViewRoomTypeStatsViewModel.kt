package com.lttrung.dormitory.ui.adminviewroomtypestats

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lttrung.dormitory.domain.data.network.models.RoomTypeStat
import com.lttrung.dormitory.domain.usecases.GetRoomTypeStatsUseCase
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
class AdminViewRoomTypeStatsViewModel @Inject constructor(private val getRoomTypeStatsUseCase: GetRoomTypeStatsUseCase) :
    ViewModel() {
    private val composite = CompositeDisposable()
    internal val getRoomTypeStatsLiveData: MutableLiveData<Resource<List<RoomTypeStat>>> =
        MutableLiveData<Resource<List<RoomTypeStat>>>()
    private val getRoomTypeStatsObserver: Consumer<List<RoomTypeStat>> by lazy {
        Consumer {
            getRoomTypeStatsLiveData.postValue(Resource.Success(it))
        }
    }
    private var getRoomTypeStatsDisposable: Disposable? = null
    internal fun getRoomTypeStats() {
        // Async
        viewModelScope.launch(Dispatchers.IO) {
            getRoomTypeStatsLiveData.postValue(Resource.Loading())
            getRoomTypeStatsDisposable?.let { composite.remove(it) }
            getRoomTypeStatsDisposable =
                getRoomTypeStatsUseCase.execute()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(getRoomTypeStatsObserver) { t: Throwable ->
                        t.message?.let { getRoomTypeStatsLiveData.postValue(Resource.Error(t.message!!)) }
                    }
            getRoomTypeStatsDisposable?.let { composite.add(it) }
        }.start()
    }
}