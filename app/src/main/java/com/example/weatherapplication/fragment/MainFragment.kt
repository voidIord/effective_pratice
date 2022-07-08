package com.example.weatherapplication.fragment

import android.Manifest
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.FragmentActivity
import com.example.weatherapplication.adapters.ViewPagerAdapter
import com.example.weatherapplication.databinding.FragmentMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainFragment : Fragment() {

    //сюда передаются фрагменты, которые впоследствии будут переключаться
    private val fList = listOf(
        HoursFragment.newInstance(),
        DaysFragment.newInstance(),
        OtherFragment.newInstance(),
    )

    //здесь задаются названия
    private val tList = listOf(
        "Часы",
        "Дни",
        "Другое",
    )

    private lateinit var pLauncher: ActivityResultLauncher<String>
    private lateinit var binding: FragmentMainBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    //разрешение на использование тех или иных данных, в данном случае данных о местположении
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPermission() //- если раскомментировать эту строчку при входе в приложение
        // будет спрашиваться разрешение на использование данных
        init()
    }

    private fun init() = with(binding){
        val adapter = ViewPagerAdapter(activity as FragmentActivity, fList)
        vp.adapter = adapter//vp - один из xml элементов
        TabLayoutMediator(tabLayout, vp){
                tab, pos -> tab.text = tList[pos]

        }.attach()//Присоединяет ранее отсоединенный подграф объекта

    }

    private fun permissionListener(){
        pLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()){
            Toast.makeText(activity, "Permission is $it", Toast.LENGTH_LONG).show()
        }
    }

    //проверка разрешений
    private fun checkPermission(){
        if(!isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION)){
            permissionListener()
            pLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    /*val resaultText: EditText? = binding.root.findViewById<EditText>(R.id.tvCurrentTemp)
        .also { @GET("https://api.openweathermap.org/data/2.5/weather?lat=54&lon=73&appid=$API_KEY")
    resaultText = it }*/

    companion object {

        @JvmStatic
        fun newInstance() = MainFragment()
    }
}