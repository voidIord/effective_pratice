package com.example.weatherapplication.adapters

//поять что такое data class
data class WeatherModel(
    val city: String,
    val time: String,
    val condition: String,
    val CurrentTemp : String,
    val hours: String,
)