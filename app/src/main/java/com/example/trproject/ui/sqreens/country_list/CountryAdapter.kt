package com.example.trproject.ui.sqreens.country_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trproject.data.models.CountryItem

import com.example.trproject.databinding.ItemCountryBinding

class CountryAdapter() : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {
    private val items: MutableList<CountryItem> = mutableListOf()
    var clickListener:((CountryItem)->Unit)?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CountryViewHolder(
            ItemCountryBinding.inflate(inflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(items[position],clickListener)

    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItems(countryList: List<CountryItem>) {
        items.clear()
        items.addAll(countryList)
        notifyDataSetChanged()
    }

    class CountryViewHolder(private val binding: ItemCountryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CountryItem, clickListener: ((CountryItem) -> Unit)?) {
            item.bind(binding)
            binding.container.setOnClickListener {
                clickListener?.invoke(item)
            }
        }
    }
}
