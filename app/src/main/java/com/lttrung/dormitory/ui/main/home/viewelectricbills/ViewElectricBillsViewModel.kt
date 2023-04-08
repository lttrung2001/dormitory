package com.lttrung.dormitory.ui.main.home.viewelectricbills

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lttrung.dormitory.database.data.network.models.ElectricBill
import com.lttrung.dormitory.database.data.network.models.RoomType
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
class ViewElectricBillsViewModel @Inject constructor(
    private val useCase: ViewElectricBillsUseCase
) : ViewModel() {
    internal val electricBillsLiveData: MutableLiveData<Resource<List<ElectricBill>>> by lazy {
        MutableLiveData<Resource<List<ElectricBill>>>()
    }
    private val composite: CompositeDisposable by lazy {
        CompositeDisposable()
    }
    private var electricBillsDisposable: Disposable? = null
    private val electricBillsObserver: Consumer<List<ElectricBill>> by lazy {
        Consumer {
            electricBillsLiveData.postValue(Resource.Success(it))
        }
    }

    internal fun getElectricBills() {
        viewModelScope.launch(Dispatchers.IO) {
            electricBillsLiveData.postValue(Resource.Loading())
            electricBillsDisposable?.let { composite.remove(it) }
            electricBillsDisposable = useCase.getElectricBills().observeOn(AndroidSchedulers.mainThread())
                .subscribe(electricBillsObserver) { t ->
                    electricBillsLiveData.postValue(Resource.Error(t.message ?: "Unknown error."))
                }
            electricBillsDisposable?.let { composite.add(it) }
        }.start()
    }
}