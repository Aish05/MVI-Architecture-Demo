package aish.android.mvisample.ui.main

import aish.android.mvisample.R
import aish.android.mvisample.model.CountriesItem
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_summary_adapter.view.*


class MainDataAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CountriesItem>() {

        override fun areItemsTheSame(oldItem: CountriesItem, newItem: CountriesItem): Boolean {
            return oldItem.countryCode == newItem.countryCode
        }

        override fun areContentsTheSame(oldItem: CountriesItem, newItem: CountriesItem): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    fun submitList(list: List<CountriesItem>) {
        differ.submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SummaryViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_summary_adapter, parent, false))

    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SummaryViewHolder -> {
                holder.bind(differ.currentList.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    class SummaryViewHolder constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(item: CountriesItem) = with(itemView) {
            country_name.text = item.country
            total_confirmed.text = item.totalConfirmed.toString()
            total_death.text = item.totalDeaths.toString()
            total_recovered.text = item.totalRecovered.toString()
        }
    }

}