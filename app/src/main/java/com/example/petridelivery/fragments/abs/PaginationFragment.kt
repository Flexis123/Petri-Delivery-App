package com.example.petridelivery.fragments.abs

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.petridelivery.lists.adapters.abs.EditableViewHolder
import com.example.petridelivery.lists.viewmodels.abs.IntegerKeyViewModel
import kotlinx.coroutines.flow.collectLatest

abstract class PaginationFragment<T: Any, U : EditableViewHolder>(contentLayoutId: Int, private val recyclerViewResId: Int) : BaseFragment(contentLayoutId) {

    protected lateinit var viewModel: IntegerKeyViewModel<T>
    protected lateinit var adapter: PagingDataAdapter<T, U>


    fun onViewCreated(view: View, savedInstanceState: Bundle?, adapter: PagingDataAdapter<T, U>,
                      viewModel: IntegerKeyViewModel<T>) {

        this.adapter = adapter
        this.viewModel = viewModel

        val recyclerView: RecyclerView = view.findViewById(recyclerViewResId)
        recyclerView.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.flow.collectLatest {
                adapter.submitData(it)
            }
        }
    }

}