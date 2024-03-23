package com.example.todoapp.ui.viewmodel

import androidx.lifecycle.ViewModel

class TodoViewModel : ViewModel() {
val todos = mutableListOf<String>()
    init {
        todos.add("Test 1")
        todos.add("Test 2")
        todos.add("Test 3")
    }
}