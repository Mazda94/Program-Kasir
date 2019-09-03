package com.hirano_ali.programkasir;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.Request;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.zxing.Result;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class TambahBarangActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private static final int REQUEST_PERMISSION_CODE = 100;
    private ZXingScannerView mScannerView;
    private TextInputEditText editTextKodeBarang;
    private TextInputEditText editTextNamaBarang;
    private TextInputEditText editTextHargaBarang;
    private TextInputEditText editTextStokBarang;
    private Network network = new Network();
    private MaterialButton scan;

    private String kodeBarang;
    private String namaBarang;
    private String hargaBarang;
    private String stokBarang;
    private NumberFormat formatRupiah = NumberFormat.getCurrencyInstance( new Locale( "in", "ID" ) );

    @Override
    public void onCreate(Bundle state) {
        super.onCreate( state );
        setContentView( R.layout.activity_tambah_barang );

        if (ContextCompat.checkSelfPermission( TambahBarangActivity.this, Manifest.permission.CAMERA ) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions( TambahBarangActivity.this, new String[]{Manifest.permission.CAMERA}, REQUEST_PERMISSION_CODE );
        }

        final FrameLayout contentFrame = findViewById( R.id.content_frame );
        mScannerView = new ZXingScannerView( this );
        contentFrame.addView( mScannerView );
        editTextKodeBarang = findViewById( R.id.kode_barang );
        editTextNamaBarang = findViewById( R.id.nama_barang );
        editTextHargaBarang = findViewById( R.id.harga_barang );
        editTextStokBarang = findViewById( R.id.stok_barang );

        scan = findViewById( R.id.start_scan );
        scan.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (String.valueOf( scan.getText() )) {
                    case "Start Scan":
                        mScannerView.setResultHandler( TambahBarangActivity.this );
                        mScannerView.startCamera();
                        scan.setText( "Pause Scan" );
                        break;
                    case "Pause Scan":
                        mScannerView.stopCamera();
                        scan.setText( "Start Scan" );
                        break;
                }
            }
        } );

        MaterialButton insert = findViewById( R.id.insert_barang );
        insert.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kodeBarang = String.valueOf( editTextKodeBarang.getText() );
                namaBarang = String.valueOf( editTextNamaBarang.getText() );
                hargaBarang = String.valueOf( editTextHargaBarang.getText() );
                stokBarang = String.valueOf( editTextStokBarang.getText() );
                HashMap<String, String> param = new HashMap<>();
                param.put( "kode_barang", kodeBarang );
                param.put( "nama_barang", namaBarang );
                param.put( "harga_barang", hargaBarang );
                param.put( "stok_barang", stokBarang );
                network.HttpRequest( TambahBarangActivity.this, Request.Method.POST, Config.URL_INSERT_BARANG, param, new NetworkHelper() {
                    @Override
                    public void getResponseFromServer(JSONObject response) throws JSONException {
                        Toast.makeText( TambahBarangActivity.this, response.getString( "message" ), Toast.LENGTH_SHORT ).show();
                        clearData();
                        mScannerView.resumeCameraPreview( TambahBarangActivity.this );
                    }
                } );
            }
        } );
    }

    private void clearData() {
        editTextKodeBarang.setText( "" );
        editTextNamaBarang.setText( "" );
        editTextHargaBarang.setText( "" );
        editTextStokBarang.setText( "" );
    }

    @Override
    public void onResume() {
        super.onResume();
        scan.setText( "Pause Scan" );
        mScannerView.setResultHandler( this );
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }


    @Override
    public void handleResult(final Result rawResult) {
        scan.setText( "Start Scan" );
        final ProgressDialog loading = new ProgressDialog( TambahBarangActivity.this );
        loading.setMessage( "Mengambil data" );
        loading.show();

        HashMap<String, String> param = new HashMap<>();
        param.put( "kode_barang", rawResult.getText() );
        network.HttpRequest( TambahBarangActivity.this, Request.Method.POST, Config.URL_GET_BARANG, param, new NetworkHelper() {
            @Override
            public void getResponseFromServer(JSONObject response) throws JSONException {
                Log.d( "getResponseFromServer: ", response.toString() );
                loading.dismiss();
                if (!response.getBoolean( "error" )) {
                    kodeBarang = response.getJSONObject( "data" ).getString( "kode_barang" );
                    namaBarang = response.getJSONObject( "data" ).getString( "nama_barang" );
                    hargaBarang = response.getJSONObject( "data" ).getString( "harga_barang" );
                    stokBarang = response.getJSONObject( "data" ).getString( "stok_barang" );

                    editTextKodeBarang.setText( kodeBarang );
                    editTextNamaBarang.setText( namaBarang );
                    editTextHargaBarang.setText( formatRupiah.format( Integer.parseInt( hargaBarang ) ) );
                    editTextStokBarang.setText( stokBarang );
                } else {
                    editTextKodeBarang.setText( rawResult.getText() );
                    editTextStokBarang.setText( "1" );
                }
            }
        } );
    }
}
