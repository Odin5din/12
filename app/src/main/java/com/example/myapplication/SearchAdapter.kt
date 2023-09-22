package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.RecyclerViewItem
import com.bumptech.glide.Glide
import com.example.api.databinding.SearchItemBinding
import com.example.myapplication.Utils.Utils.getDateFromTimestampWithFormat

class SearchAdapter(private val context1: Context) :
    RecyclerView.Adapter<SearchAdapter.ItemViewHolder>() {
    var items = ArrayList<RecyclerViewItem>()

    fun clearItem() {
        items.clear()
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchAdapter.ItemViewHolder {
        val binding = SearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchAdapter.ItemViewHolder, position: Int) {
        val currentItem = items[position]

        Glide.with(context1)
            .load(currentItem.url)
            .into(holder.iconImage)

        holder.like.visibility = if (currentItem.isLike) View.VISIBLE else View.INVISIBLE
        holder.title.text = currentItem.title
        holder.datetime.text = getDateFromTimestampWithFormat(
            currentItem.dateTime, "", ""
        )
    }

    override fun getItemCount(): Int =
        items.size


    inner class ItemViewHolder(binding: SearchItemBinding) : RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {

        var iconImage: ImageView = binding.imgItem
        var like: ImageView = binding.imgLike
        var title: TextView = binding.txtItemTitle
        var datetime: TextView = binding.txtItemDatetime
        var recyclerViewItem: ConstraintLayout = binding.recyclerviewitem

        init {
            like.visibility = View.GONE
            iconImage.setOnClickListener(this)
            recyclerViewItem.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            val position = adapterPosition.takeIf { it != RecyclerView.NO_POSITION } ?: return
            val item = items[position]

            item.isLike = !item.isLike

            if (item.isLike) {
                (context1 as MainActivity).addLikedItem(item)
            } else {
                (context1 as MainActivity).removeLikedItem(item)
            }

            notifyItemChanged(position)


        }
    }
}