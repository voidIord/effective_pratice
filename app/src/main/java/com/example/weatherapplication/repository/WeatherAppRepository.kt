package com.example.weatherapplication.repository

import android.location.Location
import androidx.lifecycle.LiveData
import com.example.weatherapplication.repository.network.api.NetworkState
import com.example.weatherapplication.repository.network.api.WeatherApiClient
import com.example.weatherapplication.repository.network.WeatherApiDatasource
import com.example.weatherapplication.repository.network.api.WeatherApiInterface
import com.example.weatherapplication.repository.vo.WeatherInfo
import io.reactivex.disposables.CompositeDisposable

class WeatherAppRepository(val compositeDisposable: CompositeDisposable) {

    private val weatherApiDatasource by lazy {
        WeatherApiDatasource(apiService, compositeDisposable)
    }

    private val apiService: WeatherApiInterface by lazy {
        WeatherApiClient.getClient()
    }

    fun fetchWeatherDetailsNetworkState(): LiveData<NetworkState> {
        return weatherApiDatasource.networkState
    }

    fun isLoading(): LiveData<Boolean> {
        return weatherApiDatasource.isLoading
    }

    fun fetchWeatherInfoList(): LiveData<ArrayList<WeatherInfo>> {
        return weatherApiDatasource.weatherInfoListResponse
    }

    fun fetchWeatherInfo(
        location: List<Location>
    ) {
        weatherApiDatasource.fetchWeatherInfo(location)
    }

}