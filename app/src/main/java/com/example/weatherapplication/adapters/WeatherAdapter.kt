package com.example.weatherapplication.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapplication.databinding.ListItemBinding

class WeatherAdapter(config: AsyncDifferConfig<WeatherModel>) :
    ListAdapter<WeatherModel, WeatherAdapter.Holder>(config)
{
    class Holder(view:View):RecyclerView.ViewHolder(view){
        val binding = ListItemBinding.bind(view)


        fun bind(item: WeatherModel) = with(binding)
        {
            tvDate.text = item.time
            tvConditions.text = item.condition
            tvTemp.text = item.CurrentTemp
        }

    }

    //в доработке
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

        //val itemBinding = RowPaymentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        //return PaymentHolder(itemBinding)

        /*другой вариант, но я так понял в этом случае не следует использовать viewBinding

            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.text_row_item, viewGroup, false)

            return ViewHolder(view)
         */
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        //e;t
    }
}