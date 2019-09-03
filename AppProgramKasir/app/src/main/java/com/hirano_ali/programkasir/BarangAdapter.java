package com.hirano_ali.programkasir;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class BarangAdapter extends ArrayAdapter<Barang> {

    public BarangAdapter(@NonNull Context context, List<Barang> vehicles) {
        super( context, R.layout.item_barang, vehicles );
    }

    private static class ViewHolder {
        TextView textViewKodeBarang;
        TextView textViewNamaBarang;
        TextView textViewHargaBarang;
        TextView textViewStokBarang;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Barang barang = getItem( position );
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from( getContext() ).inflate( R.layout.item_barang, parent, false );
            viewHolder.textViewKodeBarang = convertView.findViewById( R.id.kode_barang );
            viewHolder.textViewNamaBarang = convertView.findViewById( R.id.nama_barang );
            viewHolder.textViewHargaBarang = convertView.findViewById( R.id.harga_barang );
            viewHolder.textViewStokBarang = convertView.findViewById( R.id.stok_barang );

            convertView.setTag( viewHolder );
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textViewKodeBarang.setText( barang.getKodeBarang() );
        viewHolder.textViewNamaBarang.setText( barang.getNamaBarang() );
        int harga = Integer.parseInt( barang.getHargaBarang() );

        NumberFormat formatRp = NumberFormat.getCurrencyInstance(new Locale( "in", "ID" ) );
        viewHolder.textViewHargaBarang.setText( formatRp.format( harga ) );
        viewHolder.textViewStokBarang.setText( barang.getStokBarang() );
        return convertView;
    }
}
