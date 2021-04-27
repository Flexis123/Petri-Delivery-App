package com.example.petridelivery.lists.adapters;

import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.petridelivery.R;
import com.example.petridelivery.lists.adapters.abs.BaseViewHolder;
import com.example.petridelivery.lists.adapters.abs.DiffItemCallback;
import com.example.petridelivery.lists.adapters.abs.PagingColumnHeaderDataAdapter;
import com.example.petridelivery.wrappers.ClientWrapper;
import com.example.petridelivery.wrappers.base.OnResponseCallback;
import com.petri.delivery.web.objects.ClientDto;

import lombok.Getter;
import retrofit2.Response;

public class ClientListAdapter extends PagingColumnHeaderDataAdapter<ClientDto, ClientListAdapter.ClientViewHolder> {

    ClientWrapper clientWrapper;

    public ClientListAdapter(ClientWrapper clientWrapper, View view) {
        super(DIFF_CALLBACK, view);
        this.clientWrapper = clientWrapper;
    }

    @Override
    public void onBindViewHolder(@NonNull ClientViewHolder holder, int position) {
        Resources res = holder.itemView.getResources();
        if(position == 0){
            holder.clientIdTextView.setText(res.getString(R.string.client_id_column_header));
            holder.numeTextView.setText(res.getString(R.string.client_nume_column_header));
            holder.prenumeTextView.setText(res.getString(R.string.client_prenume_column_header));
            return;
        }
        ClientDto client = getItem(position);
        if(client != null){
            holder.prenumeTextView.setText(client.getPrenume());
            holder.clientIdTextView.setText(client.getId());
            holder.numeTextView.setText(client.getNume());
            holder.numarDeTelefonTextView.setText(client.getNumarDeTelefon());

            holder.getStergeButton().setOnClickListener(v -> {
                Integer id = Integer.parseInt(holder.clientIdTextView.getText().toString());
                clientWrapper.deleteClient(id).enqueue(new OnResponseCallback<Void>(view.getContext()) {
                    @Override
                    public void onSuccessful(Response<Void> response) {
                        parent.removeViewAt(position);
                    }
                });
            });
        }
    }

    @Override
    public ClientViewHolder createViewHolder(ViewGroup parent) {
        return new ClientViewHolder(inflater.inflate(R.layout.client_view_holder, parent, false));
    }

    private static DiffUtil.ItemCallback<ClientDto> DIFF_CALLBACK =
            new DiffItemCallback<ClientDto>() {
                @Override
                public boolean areItemsTheSame(@NonNull ClientDto oldItem, @NonNull ClientDto newItem) {
                    return oldItem.getId().equals(newItem.getId());
                }
            };

    @Getter
    public class ClientViewHolder extends BaseViewHolder {
        private final TextView clientIdTextView;
        private final TextView numeTextView;
        private final TextView prenumeTextView;
        private final TextView numarDeTelefonTextView;

        public ClientViewHolder(@NonNull View itemView) {
            super(itemView);

            clientIdTextView = itemView.findViewById(R.id.clientIdTextView);
            numeTextView = itemView.findViewById(R.id.numeTextView);
            prenumeTextView = itemView.findViewById(R.id.prenumeTextView);
            numarDeTelefonTextView = itemView.findViewById(R.id.numarDeTelefonTextView);
        }
    }
}
