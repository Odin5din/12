package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.myapplication.model.RecyclerViewItem
import com.example.myapplication.databinding.FragmentSecondBinding


class SecondFragment : Fragment() {
    private lateinit var context1: Context
    private var binding: FragmentSecondBinding? = null
    private lateinit var adapter: LikeAdapter
    private var likedItems: List<RecyclerViewItem> = listOf()


    override fun onAttach(context: Context) {
        super.onAttach(context)
        context1 = context
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val mainActivity = activity as MainActivity
        likedItems = mainActivity.likedItems

        adapter = LikeAdapter(context1).apply {
            items = likedItems.toMutableList()
        }

        binding = FragmentSecondBinding.inflate(inflater, container, false).apply {
            likeItem.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
            likeItem.adapter = adapter
        }

        return  binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}