package com.example.wishlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import java.text.NumberFormat


class wishAdapter(
    private val items: MutableList<WishItem>
) : RecyclerView.Adapter<wishAdapter.VH>() {

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName = itemView.findViewById<TextView>(R.id.tvName)
        val tvPrice = itemView.findViewById<TextView>(R.id.tvPrice)
        val tvUrl = itemView.findViewById<TextView>(R.id.tvUrl)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_wish, parent, false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = items[position]
        holder.tvName.text = item.name
        holder.tvPrice.text = NumberFormat.getCurrencyInstance().format(item.price)
        holder.tvUrl.text = item.url
    }

    override fun getItemCount(): Int = items.size

    fun add(item: WishItem) {
        items.add(item)
        notifyItemInserted(items.lastIndex)
    }
}