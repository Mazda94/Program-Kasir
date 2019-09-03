package com.hirano_ali.programkasir;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListBarangActivity extends AppCompatActivity {
    private Network network = new Network();
    private List<Barang> barangs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_list_barang );

        FloatingActionButton addItem = findViewById( R.id.tambah_barang );
        addItem.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( ListBarangActivity.this, TambahBarangActivity.class ) );
            }
        } );
    }

    @Override
    protected void onResume() {
        super.onResume();
        barangs = new ArrayList<>(  );
        new Handler().postDelayed( new Runnable() {
            @Override
            public void run() {
                network.HttpRequest( ListBarangActivity.this, Request.Method.GET, Config.URL_GET_LIST_BARANG, null, new NetworkHelper() {
                    @Override
                    public void getResponseFromServer(JSONObject response) throws JSONException {
                        TextView noData;
                        noData = findViewById( R.id.no_data );
                        ProgressBar loading;
                        loading = findViewById( R.id.loading );
                        loading.setVisibility( View.GONE );
                        if (!response.getBoolean( "error" )){
                            JSONArray barang = response.getJSONArray( "data" );
                            for (int i = 0; i < barang.length(); i++) {
                                String kodeBarang = barang.getJSONObject( i ).getString( "kode_barang" );
                                String namaBarang = barang.getJSONObject( i ).getString( "nama_barang" );
                                String hargaBarang = barang.getJSONObject( i ).getString( "harga_barang" );
                                String stokBarang = barang.getJSONObject( i ).getString( "stok_barang" );
                                barangs.add( new Barang( kodeBarang, namaBarang, hargaBarang, stokBarang ) );
                            }
                            MaterialCardView layoutListBarang = findViewById( R.id.layout_list_barang );
                            layoutListBarang.setVisibility( View.VISIBLE );
                            BarangAdapter adapter = new BarangAdapter( ListBarangActivity.this, barangs );
                            ListView listBarang = findViewById( R.id.list_barang );
                            listBarang.setAdapter( adapter );
                        } else {
                            noData.setVisibility( View.VISIBLE );
                        }
                    }
                } );
            }
        }, 1500 );
    }
}
