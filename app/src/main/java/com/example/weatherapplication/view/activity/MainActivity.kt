package com.example.weatherapplication.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.databinding.DataBindingUtil
import com.example.weatherapplication.R
import com.example.weatherapplication.viewModel.MainViewModel
import com.example.weatherapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //другой файл
    private val navController: NavController by lazy { findNavController(R.id.fragment_container) }
    //отдельный файл
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        )
        NavigationUI.setupActionBarWithNavController(this, navController)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            navController
            , null
        )
    }
    //часть жизненного цикла onRestart
    override fun onRestart() {
        super.onRestart()
        viewModel.setUpdateFlag(true)
    }
}