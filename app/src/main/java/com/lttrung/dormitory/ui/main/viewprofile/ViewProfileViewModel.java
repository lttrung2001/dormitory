package com.lttrung.dormitory.ui.main.viewprofile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewProfileViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ViewProfileViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}