package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.myapplication.model.RecyclerViewItem
import com.example.myapplication.model.Retrofit_client.apiService
import com.example.myapplication.Utils.Utils
import com.example.myapplication.model.KakaoModel
import com.example.myapplication.databinding.FragmentFirstBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    private lateinit var editTextSearch: EditText
    private lateinit var buttonSearch: Button
    private lateinit var adapter: SearchAdapter
    private lateinit var adapterimage: StaggeredGridLayoutManager
    private lateinit var context1: Context
    private var item: ArrayList<RecyclerViewItem> = ArrayList()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context1 = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater, container, false)

        setupView()
        performSearch()


        editTextSearch = binding.edtSearch
        buttonSearch = binding.btnSearch

        buttonSearch.setOnClickListener {
            performSearch()
        }

        return binding.root
    }

    private fun setupView() {
        adapterimage = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.recyclerviewImg.layoutManager = adapterimage

        adapter = SearchAdapter(context1)
        binding.recyclerviewImg.adapter = adapter
        binding.recyclerviewImg.itemAnimator = null

        val lastSearch = Utils.getLastSearch(requireContext())
        binding.edtSearch.setText(lastSearch)
    }


    private fun performSearch() {
        val query = editTextSearch.text.toString()
        if (query.isNotEmpty()) {
            Utils.saveLastSearch(requireContext(), query)
            adapter.clearItem()
            fetchImageResults(query)
        } else {
            Toast.makeText(context1, "검색어를 입력해주세요", Toast.LENGTH_SHORT).show()
        }
    }

    private fun fetchImageResults(query: String) {
        apiService.kakao_image_search(
            "KakaoAK 031dcfb15b0e58f859ab7a807508be70", query, "recency", 1, 80
        )?.enqueue(object : Callback<KakaoModel?> {
            override fun onResponse(call: Call<KakaoModel?>, response: Response<KakaoModel?>) {
                response.body()?.meta?.let { meta ->
                    if (meta.totalCount > 0) {
                        response.body()!!.documents.forEach { documents ->
                            val title = documents.displaySitename
                            val datetime = documents.datetime
                            val url = documents.thumbnailUrl
                            item.add(RecyclerViewItem(title, datetime, url))

                        }
                    }
                }
                adapter.items = item
                adapter.notifyDataSetChanged()

            }

            override fun onFailure(call: Call<KakaoModel?>, t: Throwable) {
                Log.e("asd", "test : ${t.message}")
            }
        })

        }
    }

