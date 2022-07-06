package com.example.weatherapplication

import java.util.stream.Stream

//для хранения информации из API
data class DayItem(
    val city: String,
    val time: String,
    val condition: String,
    val imageUrl: String,
    val currentTemp: String,
    val hours: String,

)
