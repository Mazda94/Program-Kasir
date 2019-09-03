package com.hirano_ali.programkasir;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CashierAdapter extends ArrayAdapter<Cashier> {

    public CashierAdapter(@NonNull Context context, List<Cashier> cashiers) {
        super( context, R.layout.item_petugas_cashier, cashiers );
    }

    private static class ViewHolder {
        TextView textViewNIP;
        TextView textViewNamaPetugas;
        TextView textViewStatus;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Cashier cashier = getItem( position );
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from( getContext() ).inflate( R.layout.item_petugas_cashier, parent, false );
            viewHolder.textViewNIP = convertView.findViewById( R.id.nip );
            viewHolder.textViewNamaPetugas = convertView.findViewById( R.id.nama );
            viewHolder.textViewStatus = convertView.findViewById( R.id.status );

            convertView.setTag( viewHolder );
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textViewNIP.setText( cashier.getNip() );
        viewHolder.textViewNamaPetugas.setText( cashier.getNamaPetugas() );
        viewHolder.textViewStatus.setText( cashier.getStatus() );
        return convertView;
    }
}
