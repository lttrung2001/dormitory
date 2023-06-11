package com.lttrung.dormitory.ui.activities.adminviewgenderstats

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lttrung.dormitory.domain.data.network.models.GenderStats
import com.lttrung.dormitory.domain.usecases.GetGenderStatsUseCase
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
class AdminViewGenderStatsViewModel @Inject constructor(private val getGenderStatsUseCase: GetGenderStatsUseCase) :
    ViewModel() {
    private val composite = CompositeDisposable()
    internal val getGenderStatsLiveData: MutableLiveData<Resource<GenderStats>> =
        MutableLiveData<Resource<GenderStats>>()
    private val getGenderStatsObserver: Consumer<GenderStats> by lazy {
        Consumer {
            getGenderStatsLiveData.postValue(Resource.Success(it))
        }
    }
    private var getGenderStatsDisposable: Disposable? = null
    internal fun getGenderStats() {
        // Async
        viewModelScope.launch(Dispatchers.IO) {
            getGenderStatsLiveData.postValue(Resource.Loading())
            getGenderStatsDisposable?.let { composite.remove(it) }
            getGenderStatsDisposable =
                getGenderStatsUseCase.execute()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(getGenderStatsObserver) { t: Throwable ->
                        t.message?.let { getGenderStatsLiveData.postValue(Resource.Error(t.message!!)) }
                    }
            getGenderStatsDisposable?.let { composite.add(it) }
        }.start()
    }
}