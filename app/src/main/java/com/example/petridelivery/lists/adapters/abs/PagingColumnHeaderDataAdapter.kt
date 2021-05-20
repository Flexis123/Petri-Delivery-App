package com.example.petridelivery.lists.adapters.abs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil

abstract class PagingColumnHeaderDataAdapter<T: Any, U : EditableViewHolder>(diffCallback: DiffUtil.ItemCallback<T>, protected var view: View) : PagingDataAdapter<T, U>(diffCallback) {
    protected lateinit var inflater: LayoutInflater
    protected lateinit var parent: ViewGroup

    companion object {
        const val HEADER = 0
        const val ITEM = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): U {
        this.parent = parent
        inflater = LayoutInflater.from(parent.context)

        val viewHolder = createViewHolder(parent)
        if (viewType == HEADER) {
            viewHolder.actionButtonFrame.removeAllViews()
        }
        return viewHolder
    }

    abstract fun createViewHolder(parent: ViewGroup): U

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            HEADER
        } else ITEM
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + 1
    }

}