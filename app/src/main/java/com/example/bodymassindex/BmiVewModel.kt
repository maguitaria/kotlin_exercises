package com.example.bodymassindex

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BmiViewModel : ViewModel() {
    // Member variables for height, weight, and result (bmi)
    private val _height = MutableLiveData<Float>()
    val height: LiveData<Float> get() = _height

    private val _weight = MutableLiveData<Float>()
    val weight: LiveData<Float> get() = _weight

    private val _bmi = MutableLiveData<Float>()
    val bmi: LiveData<Float> get() = _bmi

    // Method for updating height
    fun updateHeight(height: Float) {
        _height.value = height
        calculateBmi()
    }

    // Method for updating weight
    fun updateWeight(weight: Float) {
        _weight.value = weight
        calculateBmi()
    }

    // Private method for BMI calculation
    private fun calculateBmi() {
        val heightValue = _height.value ?: 0f
        val weightValue = _weight.value ?: 0f

        if (heightValue > 0 && weightValue > 0) {
            val bmiValue = weightValue / (heightValue * heightValue)
            _bmi.value = bmiValue
        }
    }
}