package com.example.weatherapplication.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weatherapplication.R

//этот фрагмент будет использоваться для отображения прогноза погоды на день по часам
class HoursFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_hours, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = HoursFragment()
    }
}