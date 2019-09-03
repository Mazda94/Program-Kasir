package com.hirano_ali.programkasir;

import android.content.Context;

import androidx.annotation.Nullable;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Network {
    public void HttpRequest(final Context context, final int method, final String url,
                            @Nullable final HashMap params,
                            final NetworkHelper networkHelper) {
        RequestQueue queue = Volley.newRequestQueue( context );
        StringRequest request = new StringRequest( method, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    networkHelper.getResponseFromServer( new JSONObject( response ) );
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        } ) {
            @Override
            protected Map<String, String> getParams() {
                return params;
            }

            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headerParams = new HashMap<>();
                headerParams.put( "Content-Type", "application/x-www-form-urlencoded" );
                return headerParams;
            }
        };
        request.setRetryPolicy( new DefaultRetryPolicy( DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT ) );
        queue.add( request );
    }
}
