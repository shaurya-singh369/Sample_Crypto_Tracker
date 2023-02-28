package com.example.sample_crypto_tracker.recylerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sample_crypto_tracker.R
import com.example.sample_crypto_tracker.schema.CryptoEntity

class Adapter(val cryptoList: List<CryptoEntity>) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.currency_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.currencyName.text = cryptoList[position].name
        holder.currencyRate.text = cryptoList[position].priceUsd
        holder.currencySymbol.text = cryptoList[position].symbol
        holder.root.setOnClickListener {
            //TODO: Navigate to detail page

        }

    }


    override fun getItemCount(): Int {
        return cryptoList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var currencyName = itemView.findViewById<TextView>(R.id.currency_name)
        var currencyRate = itemView.findViewById<TextView>(R.id.currency_rate)
        var currencySymbol = itemView.findViewById<TextView>(R.id.currency_symbol)
        var root = itemView.findViewById<View>(R.id.currency_name)
    }


}
