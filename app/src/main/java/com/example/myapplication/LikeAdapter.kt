package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.model.RecyclerViewItem
import com.example.myapplication.Utils.Utils.getDateFromTimestampWithFormat
import com.example.myapplication.databinding.SearchItemBinding

class LikeAdapter(var context1: Context) : RecyclerView.Adapter<LikeAdapter.ItemViewHolder>() {
    var items = mutableListOf<RecyclerViewItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = SearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        Glide.with(context1)
            .load(items[position].url)
            .into((holder as ItemViewHolder).iconImage)

        holder.title.text = items[position].title
        holder.like.visibility = View.GONE
        holder.datetime.text =
            getDateFromTimestampWithFormat(items[position].dateTime,"yyyy-MM-dd'T'HH:mm:ss.SSS+09:00","yyyy-MM-dd HH:mm:ss")
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ItemViewHolder(binding: SearchItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var iconImage: ImageView = binding.imgItem
        var like: ImageView = binding.imgLike
        var title: TextView = binding.txtItemTitle
        var datetime: TextView = binding.txtItemDatetime
        var recyclerViewItem: ConstraintLayout = binding.recyclerviewitem


        init {
            like.visibility = View.GONE

            recyclerViewItem.setOnClickListener {
                val position = adapterPosition
                (context1 as MainActivity).removeLikedItem(items[position])

                if (position != RecyclerView.NO_POSITION) {
                    items.removeAt(position)
                    notifyItemRemoved(position)

                }
            }
        }
    }


}