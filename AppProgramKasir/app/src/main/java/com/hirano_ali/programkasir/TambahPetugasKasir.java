package com.hirano_ali.programkasir;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class TambahPetugasKasir extends AppCompatActivity {
    TextInputEditText editTextKasir;
    TextInputEditText editTextNIP;
    TextInputEditText editTextNama;
    TextInputEditText editTextPassword;
    TextInputEditText editTextRePassword;
    TextInputLayout layoutNIP;
    TextInputLayout layoutNama;
    TextInputLayout layoutPassword;
    TextInputLayout layoutRePassword;

    Network network = new Network();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_tambah_petugas_kasir );

        editTextNIP = findViewById( R.id.nip );
        editTextNama = findViewById( R.id.nama_user );
        editTextPassword = findViewById( R.id.password );
        editTextRePassword = findViewById( R.id.re_password );

        layoutNIP = findViewById( R.id.layout_nip );
                layoutNama = findViewById( R.id.layout_nama );
        layoutPassword = findViewById( R.id.layout_password );
                layoutRePassword = findViewById( R.id.layout_repassword );

        MaterialButton insertKasir = findViewById( R.id.insert_kasir );
        insertKasir.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nip = String.valueOf( editTextNIP.getText() );
                String nama = String.valueOf( editTextNama.getText() );
                String password = String.valueOf( editTextPassword.getText() );
                String rePassword = String.valueOf( editTextRePassword.getText() );

                if (nip.equals( "" )){
                    layoutNIP.setErrorEnabled( true );
                    layoutNIP.setError( "Form tidak boleh kosong" );
                } else {
                    layoutNIP.setErrorEnabled( false );
                    layoutNIP.setError( "" );
                    if (nama.equals( "" )){
                        layoutNama.setErrorEnabled( true );
                        layoutNama.setError( "Form tidak boleh kosong" );
                    } else {
                        layoutNama.setErrorEnabled( false );
                        layoutNama.setError( "" );
                        if (password.equals( "" )){
                            layoutPassword.setErrorEnabled( true );
                            layoutPassword.setError( "Form tidak boleh kosong" );
                        } else {
                            layoutPassword.setErrorEnabled( false );
                            layoutPassword.setError( "" );
                            if (!rePassword.equals( password )){
                                layoutRePassword.setErrorEnabled( true );
                                layoutRePassword.setError( "Password tidak sama" );
                            } else {
                                layoutRePassword.setErrorEnabled( false );
                                layoutRePassword.setError( "" );

                                HashMap<String, String> param = new HashMap<>(  );
                                param.put( "nip", nip );
                                param.put( "fullname", nama );
                                param.put( "password", password );
                                param.put( "role", "kasir");

                                network.HttpRequest( TambahPetugasKasir.this, Request.Method.POST, Config.URL_TAMBAH_USER, param, new NetworkHelper() {
                                    @Override
                                    public void getResponseFromServer(JSONObject response) throws JSONException {
                                        Log.d( "getResponseFromServer: ", response.toString() );
                                        Toast.makeText( TambahPetugasKasir.this, response.getString( "message" ), Toast.LENGTH_SHORT ).show();
                                    }
                                } );
                            }
                        }
                    }
                }
            }
        } );

    }
}
