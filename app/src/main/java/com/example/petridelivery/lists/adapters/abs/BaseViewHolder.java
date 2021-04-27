package com.example.petridelivery.lists.adapters.abs;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petridelivery.R;

import lombok.Getter;

@Getter
public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    private Button actualizeazaButton;
    private Button stergeButton;
    private LinearLayout actionButtonFrame;

    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);

        actualizeazaButton = itemView.findViewById(R.id.actualizeazaButton);
        stergeButton = itemView.findViewById(R.id.stergeButton);
        actionButtonFrame = itemView.findViewById(R.id.actionButtonsFrame);
    }
}
