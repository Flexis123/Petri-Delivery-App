package com.example.petridelivery.lists.adapters.abs

import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.petridelivery.R


abstract class EditableViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val actualizeazaButton: Button
    val stergeButton: Button
    val actionButtonFrame: LinearLayout

    init {
        actualizeazaButton = itemView.findViewById(R.id.actualizeazaButton)
        stergeButton = itemView.findViewById(R.id.stergeButton)
        actionButtonFrame = itemView.findViewById(R.id.actionButtonsFrame)
    }
}