package com.lttrung.dormitory.ui.adminviewstudentstats

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lttrung.dormitory.domain.data.network.models.StudentStat
import com.lttrung.dormitory.domain.usecases.GetStudentStatsUseCase
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
class AdminViewStudentStatsViewModel @Inject constructor(private val getStudentStatsUseCase: GetStudentStatsUseCase) :
    ViewModel() {
    private val composite = CompositeDisposable()
    internal val getStudentStatsLiveData: MutableLiveData<Resource<List<StudentStat>>> =
        MutableLiveData<Resource<List<StudentStat>>>()
    private val getStudentStatsObserver: Consumer<List<StudentStat>> by lazy {
        Consumer {
            getStudentStatsLiveData.postValue(Resource.Success(it))
        }
    }
    private var getStudentStatsDisposable: Disposable? = null
    internal fun getStudentStats() {
        // Async
        viewModelScope.launch(Dispatchers.IO) {
            getStudentStatsLiveData.postValue(Resource.Loading())
            getStudentStatsDisposable?.let { composite.remove(it) }
            getStudentStatsDisposable =
                getStudentStatsUseCase.execute()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(getStudentStatsObserver) { t: Throwable ->
                        t.message?.let { getStudentStatsLiveData.postValue(Resource.Error(t.message!!)) }
                    }
            getStudentStatsDisposable?.let { composite.add(it) }
        }.start()
    }
}