package com.example.todoapp.ui.viewmodel

data class Todo(
    // @SerializedName("userId") var uID: Int,
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)
