package com.example.petridelivery.lists.adapters.abs;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public abstract class DiffItemCallback<T> extends DiffUtil.ItemCallback<T> {
    @SuppressLint("DiffUtilEquals")
    @Override
    public boolean areContentsTheSame(@NonNull T oldItem, @NonNull T newItem) {
        return oldItem.equals(newItem);
    }
}
