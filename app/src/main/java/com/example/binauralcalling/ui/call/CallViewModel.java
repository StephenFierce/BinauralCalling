package com.example.binauralcalling.ui.call;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CallViewModel extends ViewModel {

    private MutableLiveData<Float> mDistance;
    private MutableLiveData<Double> mAngle;
    private MutableLiveData<Float> mAmbianceVolume;
    private MutableLiveData<Float> mVoiceVolume;

    public CallViewModel() {
        mDistance = new MutableLiveData<>();
        mAngle = new MutableLiveData<>();
        mAmbianceVolume = new MutableLiveData<>();
        mVoiceVolume = new MutableLiveData<>();
    }

    public MutableLiveData<Float> getDistance() {
        return mDistance;
    }

    public void setDistance(MutableLiveData<Float> mDistance) {
        this.mDistance = mDistance;
    }


    public MutableLiveData<Double> getAngle() {
        return mAngle;
    }

    public void setAngle(MutableLiveData<Double> mAngle) {
        this.mAngle = mAngle;
    }

    public MutableLiveData<Float> getAmbianceVolume() {
        return mAmbianceVolume;
    }

    public void setAmbianceVolume(MutableLiveData<Float> mAmbianceVolume) {
        this.mAmbianceVolume = mAmbianceVolume;
    }

    public MutableLiveData<Float> getVoiceVolume() {
        return mVoiceVolume;
    }

    public void setVoiceVolume(MutableLiveData<Float> mVoiceVolume) {
        this.mVoiceVolume = mVoiceVolume;
    }
}