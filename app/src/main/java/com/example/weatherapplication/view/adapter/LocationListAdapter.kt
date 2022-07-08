package com.example.weatherapplication.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapplication.databinding.WeatherListItemBinding
import com.example.weatherapplication.repository.vo.WeatherInfo
import com.example.weatherapplication.view.fragment.LocationListFragmentDirections


class LocationListAdapter(var weatherInfoList: List<WeatherInfo>) :
    RecyclerView.Adapter<LocationListAdapter.LocationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {

        return LocationViewHolder(
            WeatherListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return weatherInfoList.size
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.bind(weatherInfoList.get(position))
    }

    class LocationViewHolder(val v: WeatherListItemBinding) : RecyclerView.ViewHolder(v.root) {
        fun bind(info: WeatherInfo?) {
            info?.let {
                v.weatherInfo = it
                itemView.setOnClickListener {
                    val action =
                        LocationListFragmentDirections.actionListToWeatherDetail(
                            /*title =*/ v.tvLocationName.text.toString(),//закомментированная часть(её отсутствие) помогла исправиль ошибку
                            /*weatherInfo =*/ it@ info
                        )

                    Navigation.findNavController(it).navigate(action)
                }
            }
        }
    }

    fun updateList(updatedList: List<WeatherInfo>) {
        weatherInfoList = updatedList
        notifyDataSetChanged()
    }

}