package com.example.giphyiigroup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.giphyiigroup.adapter.TrendingGifsAdapter
import com.example.giphyiigroup.databinding.ActivityMainBinding
import com.example.giphyiigroup.network.model.Pagination
import com.example.giphyiigroup.util.PagedScrollListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    private var adapter: TrendingGifsAdapter? = null

    private var currentOffset = 0
    private var isLastPage = false
    private var isLoading = false
        set(value) {
            field = value
            binding.progress.isInvisible = !value
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        initViews()
        initObservers()
        isLoading = true
        viewModel.getTrendingGifs(PagedScrollListener.PAGE_SIZE, currentOffset)
    }

    private fun initViews() {
        val layoutManager = GridLayoutManager(this, 2)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.addOnScrollListener(object : PagedScrollListener(layoutManager) {
            override fun canLoadMore(): Boolean {
                return !isLastPage && !isLoading
            }

            override fun loadNextPage() {
                isLoading = true
                println("currentOffset - $currentOffset")
                viewModel.getTrendingGifs(PagedScrollListener.PAGE_SIZE, currentOffset)
            }
        })
        adapter = TrendingGifsAdapter()
        binding.recyclerView.adapter = adapter
    }

    private fun initObservers() {
        viewModel.trendingLiveData.observe(this) {
            println(it.toString())
            isLoading = false
            adapter?.addData(it.data)
            checkPagination(it.pagination)
        }
        viewModel.errorLiveData.observe(this) {
            isLoading = false
            it.printStackTrace()
        }
    }

    private fun checkPagination(pagination: Pagination) {
        currentOffset = pagination.offset + pagination.count
        isLastPage = currentOffset >= pagination.total
        println("currentOffset - $currentOffset isLastPage - $isLastPage")
    }
}