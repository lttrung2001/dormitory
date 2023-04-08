package com.lttrung.dormitory.ui.main.home.viewwaterbills

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lttrung.dormitory.database.data.network.models.RoomType
import com.lttrung.dormitory.database.data.network.models.WaterBill
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
class ViewWaterBillsViewModel @Inject constructor(
    private val useCase: ViewWaterBillsUseCase
) : ViewModel() {
    internal val waterBillsLiveData: MutableLiveData<Resource<List<WaterBill>>> by lazy {
        MutableLiveData<Resource<List<WaterBill>>>()
    }
    private val composite: CompositeDisposable by lazy {
        CompositeDisposable()
    }
    private var waterBillsDisposable: Disposable? = null
    private val waterBillsObserver: Consumer<List<WaterBill>> by lazy {
        Consumer {
            waterBillsLiveData.postValue(Resource.Success(it))
        }
    }

    internal fun getRoomTypes() {
        viewModelScope.launch(Dispatchers.IO) {
            waterBillsLiveData.postValue(Resource.Loading())
            waterBillsDisposable?.let { composite.remove(it) }
            waterBillsDisposable = useCase.getWaterBills().observeOn(AndroidSchedulers.mainThread())
                .subscribe(waterBillsObserver) { t ->
                    waterBillsLiveData.postValue(Resource.Error(t.message ?: "Unknown error."))
                }
            waterBillsDisposable?.let { composite.add(it) }
        }.start()
    }
}