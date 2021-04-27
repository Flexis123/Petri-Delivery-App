package com.example.petridelivery.lists.adapters.abs;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;

import org.jetbrains.annotations.NotNull;

public abstract class PagingColumnHeaderDataAdapter<T, U extends BaseViewHolder> extends PagingDataAdapter<T, U> {
    final static int HEADER = 0;
    final static int ITEM = 1;

    protected LayoutInflater inflater;
    protected ViewGroup parent;
    protected View view;

    public PagingColumnHeaderDataAdapter(@NotNull DiffUtil.ItemCallback<T> diffCallback, View view) {
        super(diffCallback);
        this.view = view;
    }

    @NonNull
    @Override
    public U onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.parent = parent;
        inflater = LayoutInflater.from(parent.getContext());

        U viewHolder = createViewHolder(parent);
        if(viewType == HEADER){
            viewHolder.getActionButtonFrame().removeAllViews();
        }
        return viewHolder;
    }

    public abstract U createViewHolder(ViewGroup parent);

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return HEADER;
        }
        return ITEM;
    }

    @Override
    public int getItemCount() {
        return super.getItemCount() + 1;
    }
}
