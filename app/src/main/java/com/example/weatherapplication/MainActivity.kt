package com.example.weatherapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weatherapplication.databinding.ActivityMainBinding
import com.example.weatherapplication.fragment.MainFragment

//activity - это точка входа для взаимодействия приложения с пользователем
//главная процедура приложения, у приложения может быть несколько "активностей", но
//на данный момент считается, что эффективнее использовать только одну
class MainActivity : AppCompatActivity() {
    //private lateinit var binding: ActivityMainBinding

    //нижеописанный метод создаёт весь интерфейс приложения
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(R.layout.activity_main)//(binding.root) сюда передаётся визуальный элемент приложения
        //supportFragmentManager - запуск фрагмента в activity

        supportFragmentManager.beginTransaction().replace(R.id.placeHolder, MainFragment.newInstance()).commit()
    }
}