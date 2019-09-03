package com.hirano_ali.programkasir;

import org.json.JSONException;
import org.json.JSONObject;

public interface NetworkHelper {
    void getResponseFromServer(JSONObject response) throws JSONException;
}
