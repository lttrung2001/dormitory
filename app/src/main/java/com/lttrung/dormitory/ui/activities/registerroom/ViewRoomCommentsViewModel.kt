package com.lttrung.dormitory.ui.activities.registerroom

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lttrung.dormitory.domain.data.local.room.entities.CommentLocalModel
import com.lttrung.dormitory.domain.data.network.models.CommentNetworkModel
import com.lttrung.dormitory.domain.usecases.DeleteCommentUseCase
import com.lttrung.dormitory.domain.usecases.GetCommentsUseCase
import com.lttrung.dormitory.domain.usecases.WriteCommentUseCase
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
class ViewRoomCommentsViewModel @Inject constructor(
    private val writeCommentUseCase: WriteCommentUseCase,
    private val deleteCommentUseCase: DeleteCommentUseCase,
    private val getCommentsUseCase: GetCommentsUseCase
) : ViewModel() {

    internal val writeCommentLiveData: MutableLiveData<Resource<CommentLocalModel>> by lazy {
        MutableLiveData<Resource<CommentLocalModel>>()
    }
    internal val deleteCommentLiveData: MutableLiveData<Resource<Int>> by lazy {
        MutableLiveData<Resource<Int>>()
    }
    internal val getCommentsLiveData: MutableLiveData<Resource<List<CommentLocalModel>>> by lazy {
        MutableLiveData<Resource<List<CommentLocalModel>>>()
    }

    private val composite: CompositeDisposable by lazy { CompositeDisposable() }
    private var writeCommentDisposable: Disposable? = null
    private var deleteCommentDisposable: Disposable? = null
    private var getCommentsDisposable: Disposable? = null

    private val writeCommentObserver: Consumer<CommentLocalModel> by lazy {
        Consumer { writeCommentLiveData.postValue(Resource.Success(it)) }
    }
    private val deleteCommentObserver: Consumer<Int> by lazy {
        Consumer { deleteCommentLiveData.postValue(Resource.Success(it)) }
    }
    private val getCommentsObserver: Consumer<List<CommentLocalModel>> by lazy {
        Consumer { getCommentsLiveData.postValue(Resource.Success(it)) }
    }

    internal fun writeComment(commentNetworkModel: CommentNetworkModel) {
        viewModelScope.launch(Dispatchers.IO) {
            writeCommentLiveData.postValue(Resource.Loading())
            writeCommentDisposable?.let { composite.remove(it) }
            writeCommentDisposable = writeCommentUseCase.execute(commentNetworkModel)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(writeCommentObserver) {

                }
            writeCommentDisposable?.let { composite.add(it) }
        }
    }

    internal fun deleteComment(commentId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteCommentLiveData.postValue(Resource.Loading())
            deleteCommentDisposable?.let { composite.remove(it) }
            deleteCommentDisposable = deleteCommentUseCase.execute(commentId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(deleteCommentObserver) {

                }
            deleteCommentDisposable?.let { composite.add(it) }
        }
    }

    internal fun getComments(roomId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            getCommentsLiveData.postValue(Resource.Loading())
            getCommentsDisposable?.let { composite.remove(it) }
            getCommentsDisposable = getCommentsUseCase.execute(roomId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getCommentsObserver) {

                }
            deleteCommentDisposable?.let { composite.add(it) }
        }
    }
}