package com.borjalapa.appexamen1.ui.reproductor;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ReproductorViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ReproductorViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is reproductor fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}