package com.example.weatherapplication.adapters

import android.view.View
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapplication.databinding.ListItemBinding

//recycler - не работает

abstract class WeatherAdapter(config: AsyncDifferConfig<WeatherModel>) :
    ListAdapter<WeatherModel, WeatherAdapter.holder>(config) {
    class holder(view:View):RecyclerView.ViewHolder(view){
        val binding = ListItemBinding.bind(view)

        fun bind(item: WeatherModel) = with(binding) {
            tvDate.text = item.time
            tvConditions.text = item.condition
            tvTemp.text = item.CurrentTemp
        }
    }
}