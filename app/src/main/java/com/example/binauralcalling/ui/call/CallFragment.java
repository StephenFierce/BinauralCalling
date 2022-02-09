package com.example.binauralcalling.ui.call;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.binauralcalling.databinding.FragmentCallBinding;
import com.google.vr.sdk.audio.GvrAudioEngine;

import java.util.Objects;

public class CallFragment extends Fragment {

    private CallViewModel callViewModel;
    private FragmentCallBinding binding;
    private GvrAudioEngine gvrAudioEngine;

    protected float[] modelPosition;
    private static final String WOODLAND_FILE = "binaural_woodland.wav";
    private static final String CUBE_FILE = "cube_sound.wav";
    private static final String SUCCESS_FILE = "success.wav";
    private static final String GREAT_GATSBY_FILE = "great_gatsby.mp3";

    private static final float MIN_MODEL_DISTANCE = 3.0f;
    private static final float MAX_MODEL_DISTANCE = 7.0f;

    private volatile int sourceId = GvrAudioEngine.INVALID_ID;
    private volatile int successSourceId = GvrAudioEngine.INVALID_ID;

    private float mDistance = 4f;
    private double mAngle = 3.14;
    private double mXAxis = 0.0;
    private double mYAxis = 0.0;
    private float mVoiceVolume = 0.4f;
    private float mAmbianceVolume = 0.4f;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        callViewModel = new ViewModelProvider(this).get(CallViewModel.class);

        modelPosition = new float[]{0.0f, 0.0f, -MAX_MODEL_DISTANCE / 2.0f};
        binding = FragmentCallBinding.inflate(inflater, container, false);

        View root = binding.getRoot();

        binding.setViewModel(callViewModel);

        LifecycleOwner lifecycleOwner = getViewLifecycleOwner();
        binding.setLifecycleOwner(lifecycleOwner);

        gvrAudioEngine = new GvrAudioEngine(Objects.requireNonNull(getContext()), GvrAudioEngine.RenderingMode.BINAURAL_HIGH_QUALITY);

        callViewModel.getDistance().observe(this, new Observer<Float>() {
            @Override
            public void onChanged(Float aFloat) {
                if (aFloat != null && aFloat != mDistance) {
                    mDistance = aFloat;
                    mXAxis = mDistance * Math.sin(mAngle);
                    mYAxis = mDistance * Math.cos(mAngle);
                    gvrAudioEngine.setSoundObjectPosition(sourceId, (float)mXAxis, (float)mYAxis, -3.5f);
                }
            }
        });

        callViewModel.getAngle().observe(this, new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                if (aDouble != null && aDouble != mAngle) {
                    mAngle = aDouble;
                    mXAxis = mDistance * Math.sin(mAngle);
                    mYAxis = mDistance * Math.cos(mAngle);
                    gvrAudioEngine.setSoundObjectPosition(sourceId, (float)mXAxis, (float)mYAxis, -3.5f);
                }
            }
        });

        callViewModel.getVoiceVolume().observe(this, new Observer<Float>() {
            @Override
            public void onChanged(Float aFloat) {
                if (aFloat != null && mVoiceVolume != aFloat){
                    mVoiceVolume = aFloat;
                    gvrAudioEngine.setSoundVolume(1,mVoiceVolume);
                }
            }
        });

        callViewModel.getAmbianceVolume().observe(this, new Observer<Float>() {
            @Override
            public void onChanged(Float aFloat) {
                if (aFloat != null && mAmbianceVolume != aFloat){
                    mAmbianceVolume = aFloat;
                    gvrAudioEngine.setSoundVolume(0,mAmbianceVolume);
                }
            }
        });

        playSound();

        return root;
    }


    private void playSound() {
        new Thread(() -> {
            // Preload the sound file
            boolean result = gvrAudioEngine.preloadSoundFile(WOODLAND_FILE);
            if (result) {
                sourceId = gvrAudioEngine.createStereoSound(WOODLAND_FILE);
                gvrAudioEngine.setSoundObjectPosition(sourceId, modelPosition[0], modelPosition[1], modelPosition[2]);
                gvrAudioEngine.setSoundVolume(sourceId,mAmbianceVolume);
                if (gvrAudioEngine.isSourceIdValid(sourceId))
                    gvrAudioEngine.playSound(sourceId, true);
                else
                    Log.e("playSound", "Invalid Source ID");
            } else
                Log.e("playSound", "Couldn't load file");

            result = gvrAudioEngine.preloadSoundFile(GREAT_GATSBY_FILE);
            if (result) {
                sourceId = gvrAudioEngine.createSoundObject(GREAT_GATSBY_FILE);
                gvrAudioEngine.setSoundObjectPosition(sourceId, (float)mXAxis, (float)mYAxis, -3.5f);
                gvrAudioEngine.setSoundVolume(sourceId,mVoiceVolume);
                if (gvrAudioEngine.isSourceIdValid(sourceId))
                    gvrAudioEngine.playSound(sourceId, true);
                else
                    Log.e("playSound", "Invalid Source ID");
            } else
                Log.e("playSound", "Couldn't load file");

        }).start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}