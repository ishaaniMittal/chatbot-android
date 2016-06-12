package vmor.com.valarmorghulis.utils;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import org.json.JSONException;
import org.json.JSONObject;

import vmor.com.valarmorghulis.R;
import vmor.com.valarmorghulis.gson.GSONRequest;
import vmor.com.valarmorghulis.gson.MySingleton;

/**
 * Created by imittal on 6/3/16.
 */
public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIIDService";

    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
        sendRegistrationToServer(refreshedToken);

    }

    private void sendRegistrationToServer(String token) {
        //You can implement this method to store the token on your server
        //Not required for current project
        JSONObject parameters = new JSONObject();
        try {
            parameters.put("fcmToken", token);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        GSONRequest<Boolean> registerTokenRequest = new GSONRequest<>(Request.Method.POST, getResources().getString(R.string.register_user_token), Boolean.class, null, new Response.Listener<Boolean>() {
            @Override
            public void onResponse(Boolean response) {
                Log.i("h","yes");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }, parameters);
        MySingleton.getInstance(getApplication().getApplicationContext()).addToRequestQueue(registerTokenRequest);
    }
}
