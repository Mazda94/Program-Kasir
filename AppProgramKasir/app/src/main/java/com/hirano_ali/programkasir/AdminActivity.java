package com.hirano_ali.programkasir;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;

public class AdminActivity extends AppCompatActivity {
    private Network network = new Network();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_admin );

        MaterialCardView cardViewPetugasKasir = findViewById( R.id.cardview_petugas_kasir );
        cardViewPetugasKasir.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( AdminActivity.this, ListCashierActivity.class ) );
            }
        } );

        MaterialCardView cardViewDaftarBarang = findViewById( R.id.cardview_daftar_barang );
        cardViewDaftarBarang.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( AdminActivity.this, ListBarangActivity.class ) );
            }
        } );
    }
}
