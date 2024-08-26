package com.overengineered.sharenet

data class Request(
    val item: String,
    val title: String,          // Ensure this property is present
    val description: String
)
