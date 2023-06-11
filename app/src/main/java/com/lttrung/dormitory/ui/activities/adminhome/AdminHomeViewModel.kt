package com.lttrung.dormitory.ui.activities.adminhome

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lttrung.dormitory.domain.usecases.LogoutUseCase
import com.lttrung.dormitory.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminHomeViewModel @Inject constructor(
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {
    internal val logoutLiveData: MutableLiveData<Resource<Unit>> =
        MutableLiveData<Resource<Unit>>()

    internal fun logout() {
        // Async
        viewModelScope.launch(Dispatchers.IO) {
            logoutLiveData.postValue(Resource.Loading())
            logoutUseCase.execute()
            logoutLiveData.postValue(Resource.Success(Unit))
        }.start()
    }
}