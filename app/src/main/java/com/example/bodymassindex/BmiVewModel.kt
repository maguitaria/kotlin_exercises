package com.example.bodymassindex

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
class BmiViewModel : ViewModel() {
    private val _heightCm = MutableLiveData<Float>()
    val heightCm: LiveData<Float> get() = _heightCm

    private val _weight = MutableLiveData<Float>()
    val weight: LiveData<Float> get() = _weight

    private val _bmi = MutableLiveData<Float>()
    val bmi: LiveData<Float> get() = _bmi

    fun updateHeightCm(heightCm: Float) {
        _heightCm.value = heightCm
        calculateBmi()
    }

    fun updateWeight(weight: Float) {
        _weight.value = weight
        calculateBmi()
    }

    fun calculateBmiStatus(): String {
        val bmiValue = bmi.value ?: 0f

        return when {
            bmiValue < 18.5f -> "Underweight"
            bmiValue < 24.9f -> "Normal weight"
            bmiValue < 29.9f -> "Overweight"
            else -> "Obese"
        }
    }

    private fun calculateBmi() {
        val heightValue = _heightCm.value ?: 0f
        val weightValue = _weight.value ?: 0f

        if (heightValue > 0 && weightValue > 0) {
            val heightInMeters = heightValue / 100 // Convert height from cm to meters
            val bmiValue = weightValue / (heightInMeters * heightInMeters)
            _bmi.value = bmiValue
        }
    }
}
