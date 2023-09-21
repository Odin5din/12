package com.example.myapplication.model

data class RecyclerViewItem (
    var title: String,
    var dateTime: String,
    var url: String,
    var isLike: Boolean = false
)