package com.example.weatherapplication.adapters

//автоимпортируются, если использовать подсказки
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

//адаптер
class VpAdapter(fa: FragmentActivity, private val list: List<Fragment>) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int {
        return list.size
    }
//просматривает позицию фрагмента, чтобы переключаться между ними
    override fun createFragment(position: Int): Fragment {
        return list[position]
    }
}