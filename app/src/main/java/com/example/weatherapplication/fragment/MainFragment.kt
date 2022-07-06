//фрагмент - как я понял, прослойка между activity и пользовательским интерфейсом
//создание черезмерного количества activity переполняет оператвиную память устройство из особенностей жизненного цикла
//фрагменты решают эту проблему, а точнее их жизненный цикл
//есть альтернативное решение - compose
package com.example.weatherapplication.fragment

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.FragmentActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.weatherapplication.adapters.VpAdapter
import com.example.weatherapplication.databinding.FragmentMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import org.json.JSONObject

//омй ключ
const val API_KEY = "c952a90dc7f4440eb3795834220507"


class MainFragment : Fragment() {
    private val fList = listOf(
        HoursFragment.newInstance(),
        DaysFragment.newInstance(),
    )
    private val tList = listOf(
        "Часы",
        "Дни",
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
        permissionListener()
        init()
    }

    private fun init() = with(binding){
        val adapter = VpAdapter(activity as FragmentActivity, fList)
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

    //получение прогноза погоды из всемироной паутины
    private fun getResult(name:String)
    {
        val url = "https://api.weatherapi.com/v1/current.json" +
                "?key=$API_KEY&q=$name&aqi=no"
        val queue = Volley.newRequestQueue(this)
        val StringRequest = StringRequest
        (
            Request.Method.GET, url,
            {
                response->val obj = JSONObject(response)
                val temp = obj.getJSONObject("current")
                Log.d("MyLog","Response: ${temp.getString("temp_c")}")
            },

            {
                Log.d("MyLog","Volley error: $it")
            }
        ),
        queue.add(StringRequest)
    }

    TextView tvCurrentTemp = (TextView)findViewById(R.id.myTextView);
    myTextView.getResult("Omsk")

    companion object {
        @JvmStatic
        fun newInstance() =
            MainFragment()
    }
}