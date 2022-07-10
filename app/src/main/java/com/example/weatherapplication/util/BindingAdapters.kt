package com.example.weatherapplication.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.weatherapplication.R
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("setLocationName")
fun setLocationName(view: TextView, timeZone: String?) {
    timeZone?.let {
        var location = timeZone
        if (location.contains("/")) {
            val strArray = location.split("/")
            location = strArray[strArray.size - 1]
        }
        if (location.contains("_")) {
            location = location.replace("_", " ")
        }
        view.text = location
    }
}

@BindingAdapter("loadWeatherImage")
fun loadWeatherImage(view: ImageView, icon: String?) {
    icon?.let {
        when (it) {
            "clear-day" -> view.setImageResource(R.drawable.clear_day)
            "clear-night" -> view.setImageResource(R.drawable.clear_night)
            "rain" -> view.setImageResource(R.drawable.rain)
            "snow" -> view.setImageResource(R.drawable.snow)
            "sleet" -> view.setImageResource(R.drawable.sleet)
            "wind" -> view.setImageResource(R.drawable.windy)
            "fog" -> view.setImageResource(R.drawable.fogy)
            "cloudy" -> view.setImageResource(R.drawable.partlt_cloudy)
            "partly-cloudy-day" -> view.setImageResource(R.drawable.partlt_cloudy)
            "partly-cloudy-night" -> view.setImageResource(R.drawable.partly_cloudy_night)
            else -> view.setImageResource(R.drawable.clear_day)
        }
    }
}

@BindingAdapter("convertLongToTime")
fun convertLongToTime(view: TextView, time: Long?) {
    time?.let {
        val date = Date(time)
        val format = SimpleDateFormat("hh:mm a")
        view.text = format.format(date)
    }
}