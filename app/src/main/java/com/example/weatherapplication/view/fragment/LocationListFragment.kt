package com.example.weatherapplication.view.fragment

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
//import androidx.lifecycle.Observer больше не нужно в связи с удалением предупреждений
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapplication.R
import com.example.weatherapplication.databinding.FragmentLocationListBinding
import com.example.weatherapplication.repository.network.api.NetworkState
import com.example.weatherapplication.view.adapter.LocationListAdapter
import com.example.weatherapplication.viewModel.MainViewModel
import com.example.weatherapplication.viewModel.LocationListFragmentViewModel
import android.Manifest
//проблема: приложение находиться в бесконечной загрузке несмотря на отсутсвие ошибок, успешную сборку и запуск проекта.
// Предположение: проблема возникла из-за перечёркнутых методов
class LocationListFragment : Fragment() {
    private lateinit var viewModel: LocationListFragmentViewModel
    private lateinit var activityViewModel: MainViewModel
    private lateinit var binding: FragmentLocationListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {//тут было View?, но ? был удалён с целяю избавиться от предупреждения
        setHasOptionsMenu(true)
        binding = FragmentLocationListBinding.inflate(inflater, container, false)
            .apply {
                lifecycleOwner = this@LocationListFragment

            }
        return binding.root
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.refresh_menu_item, menu)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.refresh_weather_info -> activityViewModel.setUpdateFlag(true)
        }
        return super.onOptionsItemSelected(item)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activityViewModel =
            ViewModelProvider(requireActivity())[MainViewModel::class.java]
        viewModel = ViewModelProvider(this)[LocationListFragmentViewModel::class.java].also {
            binding.locationListViewModel = it
        }
        binding.rvLocationList.apply {
            adapter = LocationListAdapter(listOf())
        }

        viewModel.networkState.observe(viewLifecycleOwner) {
            if (it == NetworkState.ERROR && !viewModel.listIsEmpty()) {
                Toast.makeText(activity, it.msg, Toast.LENGTH_SHORT).show()
            }
        }
        setObserverForActivityRestart()
        fetchWeatherInfoList()
    }

    private fun fetchWeatherInfoList() {
        viewModel.weatherInfoList.observe(viewLifecycleOwner) {
            (binding.rvLocationList.adapter as LocationListAdapter).updateList(it)
        }
        if (viewModel.listIsEmpty()
            && activityViewModel.getUpdateFlag().value == false
            && viewModel.isLoading.value == false
        ) {
            viewModel.fetchWeatherInfo()
        }
    }

    private fun setObserverForActivityRestart() {
        activityViewModel.getUpdateFlag().observe(requireActivity()) {
            if (it) {
                if (viewModel.isLoading.value == false) {
                    viewModel.fetchWeatherInfo()
                }
                activityViewModel.setUpdateFlag(false)
            }
        }
    }

    //разрешение на использование тех или иных данных, в данном случае данных о местположении
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPermission() //- если раскомментировать эту строчку при входе в приложение
        // будет спрашиваться разрешение на использование данных
        //init() тут когда то был, но функция удалена за ненадобностью
    }
    private lateinit var pLauncher: ActivityResultLauncher<String>

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

}