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
import java.util.HashMap;
import java.util.List;

public class ListCashierActivity extends AppCompatActivity {

    private List<Cashier> cashiers;
    private Network network = new Network();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_list_cashier );

        FloatingActionButton addItem = findViewById( R.id.tambah_petugas_kasir );
        addItem.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( ListCashierActivity.this, TambahPetugasKasir.class ) );
            }
        } );
    }

    @Override
    protected void onResume() {
        super.onResume();
        cashiers = new ArrayList<>(  );
        new Handler().postDelayed( new Runnable() {
            @Override
            public void run() {
                HashMap<String, String> param = new HashMap<>(  );
                param.put( "role_id", "2" );
                network.HttpRequest( ListCashierActivity.this, Request.Method.POST, Config.URL_GET_LIST_CASHIER, param, new NetworkHelper() {
                    @Override
                    public void getResponseFromServer(JSONObject response) throws JSONException {
                        TextView noData;
                        noData = findViewById( R.id.no_data );
                        ProgressBar loading;
                        loading = findViewById( R.id.loading );
                        loading.setVisibility( View.GONE );
                        if (!response.getBoolean( "error" )){
                            JSONArray cashier = response.getJSONArray( "data" );
                            for (int i = 0; i < cashier.length(); i++) {
                                String nip = cashier.getJSONObject( i ).getString( "nomor_id" );
                                String nama = cashier.getJSONObject( i ).getString( "nama_user" );
                                String status = cashier.getJSONObject( i ).getString( "role_name" );
                                cashiers.add( new Cashier( nip, nama, status) );
                            }
                            MaterialCardView layoutListBarang = findViewById( R.id.layout_list_cashier );
                            layoutListBarang.setVisibility( View.VISIBLE );
                            CashierAdapter adapter = new CashierAdapter( ListCashierActivity.this, cashiers );
                            ListView listBarang = findViewById( R.id.list_petugas_kasir );
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
