package com.example.petridelivery.lists.adapters.abs

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

abstract class DiffItemCallback<T> : DiffUtil.ItemCallback<T?>() {
    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }
}