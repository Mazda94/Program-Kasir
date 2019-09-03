package com.hirano_ali.programkasir;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {
    private final Network network = new Network();
    private HashMap params = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );

        final TextInputEditText editTextIdNumber = findViewById( R.id.id_number );
        editTextIdNumber.setText( "123456" );
        final TextInputEditText editTextPassword = findViewById( R.id.password );
        editTextPassword.setText( "admin123" );

        MaterialButton buttonLogin = findViewById( R.id.b_login );
        buttonLogin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idNumber = String.valueOf( editTextIdNumber.getText() );
                String password = String.valueOf( editTextPassword.getText() );

                params.put( "nomor_id", idNumber );
                params.put( "password", password );

                network.HttpRequest( LoginActivity.this, Request.Method.POST, Config.URL_LOGIN, params, new NetworkHelper() {
                    @Override
                    public void getResponseFromServer(JSONObject response) throws JSONException {
                        Log.d("getResponseFromServer: ", response.toString() );
                        if (!response.getBoolean( "error" )){
                            startActivity( new Intent( LoginActivity.this, AdminActivity.class ) );
                        } else {
                            Toast.makeText( LoginActivity.this, response.getString( "message" ), Toast.LENGTH_SHORT ).show();
                        }
                    }
                } );
            }
        } );
    }
}
