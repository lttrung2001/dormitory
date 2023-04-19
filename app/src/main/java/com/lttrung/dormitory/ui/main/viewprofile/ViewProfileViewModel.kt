package com.lttrung.dormitory.ui.main.viewprofile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lttrung.dormitory.domain.data.local.UserLocal
import com.lttrung.dormitory.domain.data.network.models.StudentProfile
import com.lttrung.dormitory.domain.usecases.LogoutUseCase
import com.lttrung.dormitory.domain.usecases.ViewProfileUseCase
import com.lttrung.dormitory.exceptions.FailedException
import com.lttrung.dormitory.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.Consumer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ViewProfileViewModel @Inject constructor(
    private val viewProfileUseCase: ViewProfileUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val userLocal: UserLocal
) : ViewModel() {
    internal val profileLiveData: MutableLiveData<Resource<StudentProfile>> by lazy {
        MutableLiveData<Resource<StudentProfile>>()
    }
    private val composite: CompositeDisposable by lazy {
        CompositeDisposable()
    }
    private var profileDisposable: Disposable? = null
    private val profileObserver: Consumer<StudentProfile> by lazy {
        Consumer {
            profileLiveData.postValue(Resource.Success(it))
        }
    }

    internal fun getProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            val currentUser = userLocal.getCurrentUser()
            var data: StudentProfile? = null
            currentUser?.let {
                val profile = currentUser.profile
                profile?.let {
                    data =
                        StudentProfile(
                            currentUser.studentId,
                            profile.fullName,
                            profile.isMale,
                            Date(profile.dob),
                            profile.email,
                            profile.identityCardId,
                            profile.phoneNumber
                        )
                }
            }
            profileLiveData.postValue(Resource.Loading(data))
            profileDisposable?.let { composite.remove(it) }
            profileDisposable =
                viewProfileUseCase.execute().observeOn(AndroidSchedulers.mainThread())
                    .subscribe(profileObserver) { t ->
                        when (t) {
                            is FailedException -> profileLiveData.postValue(Resource.Error(t.message))
                            else -> profileLiveData.postValue(Resource.Error("Unknown error"))
                        }
                    }
            profileDisposable?.let { composite.add(it) }
        }.start()
    }

    fun logout() {
        viewModelScope.launch(Dispatchers.IO) {
            logoutUseCase.execute()
        }.start()
    }
}