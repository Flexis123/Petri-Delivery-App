package com.example.petridelivery.lists.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import com.example.petridelivery.R
import com.example.petridelivery.lists.adapters.ClientListAdapter.ClientViewHolder
import com.example.petridelivery.lists.adapters.abs.EditableViewHolder
import com.example.petridelivery.lists.adapters.abs.DiffItemCallback
import com.example.petridelivery.lists.adapters.abs.PagingColumnHeaderDataAdapter
import com.example.petridelivery.wrappers.ClientWrapper
import com.example.petridelivery.wrappers.base.OnResponseCallback
import com.petri.delivery.web.objects.ClientDto
import retrofit2.Response

class ClientListAdapter(var clientWrapper: ClientWrapper, view: View) : PagingColumnHeaderDataAdapter<ClientDto?, ClientViewHolder?>(DIFF_CALLBACK, view) {

    override fun onBindViewHolder(holder: ClientViewHolder, position: Int) {
        val res = holder.itemView.resources
        if (position == 0) {
            holder.clientIdTextView.setText(res.getString(R.string.client_id_column_header))
            holder.numeTextView.setText(res.getString(R.string.client_nume_column_header))
            holder.prenumeTextView.setText(res.getString(R.string.client_prenume_column_header))
            return
        }

        val client = getItem(position)
        if (client != null) {
            holder.prenumeTextView.setText(client.prenume)
            holder.clientIdTextView.setText(client.id)
            holder.numeTextView.setText(client.nume)
            holder.numarDeTelefonTextView.setText(client.numarDeTelefon)

            holder.stergeButton.setOnClickListener { v: View? ->
                val id = holder.clientIdTextView.getText().toString().toInt()
                clientWrapper.deleteClient(id).enqueue(object: OnResponseCallback<Void>(view.context){
                    override fun onSuccessful(response: Response<Void>) {

                    }
                })
            }
        }
    }

    override fun createViewHolder(parent: ViewGroup): ClientViewHolder? {
        return ClientViewHolder(inflater.inflate(R.layout.client_view_holder, parent, false))
    }

    inner class ClientViewHolder(itemView: View) : EditableViewHolder(itemView) {
        val clientIdTextView: TextView
        val numeTextView: TextView
        val prenumeTextView: TextView
        val numarDeTelefonTextView: TextView

        init {
            clientIdTextView = itemView.findViewById(R.id.clientIdTextView)
            numeTextView = itemView.findViewById(R.id.numeTextView)
            prenumeTextView = itemView.findViewById(R.id.prenumeTextView)
            numarDeTelefonTextView = itemView.findViewById(R.id.numarDeTelefonTextView)
        }


    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<ClientDto?> = object : DiffItemCallback<ClientDto?>() {
            override fun areItemsTheSame(oldItem: ClientDto, newItem: ClientDto): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

}