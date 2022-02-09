package com.example.binauralcalling.ui.call;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;

import com.google.android.material.slider.Slider;

import circularslider.CircularSlider;

public class CallBindingAdapters {

    @BindingAdapter(value = {"distance", "distanceAttrChanged"}, requireAll = false)
    public static void setDistance(Slider slider, Float value, final InverseBindingListener attrChanged) {
        slider.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {
            @Override
            public void onStartTrackingTouch(@NonNull Slider slider) {
            }

            @Override
            public void onStopTrackingTouch(@NonNull Slider slider) {
                attrChanged.onChange();
            }
        });

        slider.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                attrChanged.onChange();
            }
        });
    }

    @InverseBindingAdapter(attribute = "distance", event = "distanceAttrChanged")
    public static Float getDistance(Slider slider) {
        return slider.getValue();
    }

    @BindingAdapter(value = {"angle", "angleAttrChanged"}, requireAll = false)
    public static void setAngleValue(CircularSlider slider, Double value, final InverseBindingListener attrChanged) {
        slider.setOnSliderMovedListener(new CircularSlider.OnSliderMovedListener() {
            @Override
            public void onSliderMoved(double pos) {
                attrChanged.onChange();
            }
        });
    }

    @InverseBindingAdapter(attribute = "angle", event = "angleAttrChanged")
    public static Double getAngleValue(CircularSlider slider) {
        return slider.getAngle();
    }

    @BindingAdapter(value = {"voiceVolume", "voiceVolumeAttrChanged"}, requireAll = false)
    public static void setVoiceVolume(Slider slider, Float value, final InverseBindingListener attrChanged) {
        slider.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {
            @Override
            public void onStartTrackingTouch(@NonNull Slider slider) {
            }

            @Override
            public void onStopTrackingTouch(@NonNull Slider slider) {
                attrChanged.onChange();
            }
        });

        slider.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                attrChanged.onChange();
            }
        });
    }

    @InverseBindingAdapter(attribute = "voiceVolume", event = "voiceVolumeAttrChanged")
    public static Float getVoiceVolume(Slider slider) {
        return slider.getValue();
    }

    @BindingAdapter(value = {"ambianceVolume", "ambianceVolumeAttrChanged"}, requireAll = false)
    public static void setAmbianceVolume(Slider slider, Float value, final InverseBindingListener attrChanged) {
        slider.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {
            @Override
            public void onStartTrackingTouch(@NonNull Slider slider) {
            }

            @Override
            public void onStopTrackingTouch(@NonNull Slider slider) {
                attrChanged.onChange();
            }
        });

        slider.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                attrChanged.onChange();
            }
        });
    }

    @InverseBindingAdapter(attribute = "ambianceVolume", event = "ambianceVolumeAttrChanged")
    public static Float getAmbianceVolume(Slider slider) {
        return slider.getValue();
    }
}
