package com.example.weatherapplication.repository.network.api

/* изменено с целью избавить от предупреждений
enum class Status {
    RUNNING,
    SUCCESS,
    FAILED
}*/

class NetworkState(val msg: String) {
    companion object {
        @JvmStatic
        val LOADED: NetworkState = NetworkState("Success")

        @JvmStatic
        val LOADING: NetworkState = NetworkState("Running")

        @JvmStatic
        val ERROR: NetworkState = NetworkState("Something went wrong")

    }
}