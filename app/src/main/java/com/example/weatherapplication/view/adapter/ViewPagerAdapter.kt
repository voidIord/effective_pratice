package com.example.weatherapplication.view.adapter

//автоимпортируются, если использовать подсказки
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

//адаптер
class ViewPagerAdapter(fa: FragmentActivity, private val list: List<Fragment>) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int {
        return list.size//вовращает размер списка
    }
//просматривает позицию фрагмента, чтобы переключаться между ними
    override fun createFragment(position: Int): Fragment {
        return list[position]//возвращает позицию
    }
}
//всё хорошо