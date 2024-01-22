package com.example.bmicalculator

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class BMIViewModel : ViewModel() {
    var height by mutableStateOf(0f)
    var weight by mutableStateOf(0f)
    var bmi by mutableStateOf(0f)

    fun updateHeight(newHeight: Float) {
        height = newHeight
        calculateBMI()
    }

    fun updateWeight(newWeight: Float) {
        weight = newWeight
        calculateBMI()
    }

    private fun calculateBMI() {
        if (height > 0 && weight > 0) {
            val heightInMeters = height / 100
            bmi = weight / (heightInMeters * heightInMeters)
        } else {
            bmi = 0f
        }
    }
}
