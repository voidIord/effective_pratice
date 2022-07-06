//фрагмент - как я понял, прослойка между activity и пользовательским интерфейсом
//создание черезмерного количества activity переполняет оператвиную память устройства
// из особенностей жизненного цикла
//фрагменты решают эту проблему, а точнее их жизненный цикл
//есть альтернативное решение - compose
package com.example.weatherapplication.fragment

import android.Manifest
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.FragmentActivity
import com.example.weatherapplication.R
/* более не испольуемые библиотеки
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley*/
import com.example.weatherapplication.adapters.ViewPagerAdapter
import com.example.weatherapplication.databinding.FragmentMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

//ключ доступа к сайту с API
const val API_KEY = "5c398a6426e5744a00caad89a51fd830"


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
        "Другое"
    )

    private lateinit var pLauncher: ActivityResultLauncher<String>
    private lateinit var binding: FragmentMainBinding

    //переменные для вывода прогноза погоды
    //private lateinit var
    //private lateinit var fusedLocationClient: FusedLocationProviderClient


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

    /*private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
    // ...

           fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }*/

//проверка разрешений
    private fun checkPermission(){
        if(!isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION)){
            permissionListener()
            pLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }
    //
    /*val resaultText: EditText? = binding.root.findViewById<EditText>(R.id.tvCurrentTemp)
        .also { @GET("https://api.openweathermap.org/data/2.5/weather?lat=54&lon=73&appid=$API_KEY")
    resaultText = it }*/


    val resultText: EditText? = binding.root.findViewById<EditText>(R.id.tvCurrentTemp)
        .also { @GET("https://api.openweathermap.org/data/2.5/weather?lat=54&lon=73&appid=$API_KEY")
        resultText = it}

    //val nicknameTextView = findViewById<TextView>(R.id.nickname_text)

    //binding.root.findViewById<EditText>(R.id.tvCurrentTemp) = @GET("https://api.openweathermap.org/data/2.5/weather?lat=54&lon=73&appid=$API_KEY"

    // just.binding.it.to.this.

    /*val a: EditText? by lazy {
        binding.root.findViewById<EditText>(R.id.tvCurrentTemp)
        .also { @GET("https://api.openweathermap.org/data/2.5/weather?lat=54&lon=73&appid=$API_KEY"), a = }
    }*/
    //val transmit: tvCurrentTemp =

    /*interface ApiService {
@GET("geo/1.0/direct?limit=5&appid=f70de23578e62f51050edc52c3345f6c")
suspend fun getLocation("q" city:String ):ArrayList<GeocodingItem>

@GET("data/2.5/onecall?&units=metric&lang=ru&exclude=minutely,hourly,alerts&appid=f70de23578e62f51050edc52c3345f6c")
suspend fun getWeather("lat" lat:String ,
"lon" lon:String
):WeatherOfTheCity

} object RetrofitInstance {

private val retrofit by lazy{
Retrofit.Builder()
.baseUrl("https://api.openweathermap.org/")
.addConverterFactory(GsonConverterFactory.create())
.build()
}
val api:ApiService by lazy{
retrofit.create(ApiService::class.java)
}

}*/
    /*class MainActivity : AppCompatActivity() {
// weather url to get JSON
var weather_url1 = ""
// api id for url
var api_id1 = "030314b750cc43e7b39e503dfe37150c"
private lateinit var textView: TextView
private lateinit var fusedLocationClient: FusedLocationProviderClient
override fun onCreate(savedInstanceState: Bundle?) {
super .onCreate(savedInstanceState)
setContentView(R.layout.activity_main)
// link the textView in which the
// temperature will be displayed
textView = findViewById(R.id.textView)
// create an instance of the Fused
// Location Provider Client
fusedLocationClient = LocationServices.getFusedLocationProviderClient( this )
Log.e( "lat" , weather_url1)
// on clicking this button function to
// get the coordinates will be called
btVar1.setOnClickListener {
Log.e( "lat" , "onClick" )
// function to find the coordinates
// of the last location
obtainLocation()
}
}
@SuppressLint ( "MissingPermission" )
private fun obtainLocation() {
Log.e( "lat" , "function" )
// get the last location
fusedLocationClient.lastLocation
.addOnSuccessListener { location: Location? ->
// get the latitude and longitude
// and create the http URL
weather_url1 = " https://api.weatherbit.io/v2.0/current? " + "lat=" + location?.latitude + "&lon=" + location?.longitude + "&key=" + api_id1
Log.e( "lat" , weather_url1.toString())
// this function will
// fetch data from URL
getTemp()
}
}
fun getTemp() {
// Instantiate the RequestQueue.
val queue = Volley.newRequestQueue( this )
val url: String = weather_url1
Log.e( "lat" , url)
// Request a string response
// from the provided URL.
val stringReq = StringRequest(Request.Method.GET, url,
Response.Listener<String> { response ->
Log.e( "lat" , response.toString())
// get the JSON object
val obj = JSONObject(response)
// get the Array from obj of name - "data"
val arr = obj.getJSONArray( "data" )
Log.e( "lat obj1" , arr.toString())
// get the JSON object from the
// array at index position 0
val obj2 = arr.getJSONObject( 0 )
Log.e( "lat obj2" , obj2.toString())
// set the temperature and the city
// name using getString() function
textView.text = obj2.getString( "temp" ) + " deg Celcius in " + obj2.getString( "city_name" )
},
// In case of any error
Response.ErrorListener { textView!!.text = "That didn't work!" })
queue.add(stringReq)
}
}*/
    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}