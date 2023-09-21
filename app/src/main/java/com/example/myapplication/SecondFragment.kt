package com.example.myapplication.View

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.myapplication.LikeAdapter
import com.example.myapplication.MainActivity
import com.example.myapplication.Model.RecyclerViewItem
import com.example.myapplication.databinding.FragmentSecondBinding


class SecondFragment : Fragment() {
    private lateinit var context2: Context
    private var binding: FragmentSecondBinding? = null
    private lateinit var adapter: LikeAdapter
    private var likedItems: List<RecyclerViewItem> = listOf()


    override fun onAttach(context: Context) {
        super.onAttach(context)
        context2 = context
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val mainActivity = activity as MainActivity
        likedItems = mainActivity.likedItems

        adapter = LikeAdapter(context2).apply {
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