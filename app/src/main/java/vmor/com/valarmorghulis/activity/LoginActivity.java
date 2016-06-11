package vmor.com.valarmorghulis.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import vmor.com.valarmorghulis.R;
import vmor.com.valarmorghulis.gson.GSONRequest;
import vmor.com.valarmorghulis.gson.MySingleton;

/**
 * Created by Vijay on 11/06/2016.
 */
public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    private EditText userName, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        loginButton = (Button) findViewById(R.id.login_button);
        userName = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.userPassword);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, HomePage.class));
                finish();
                JSONObject parameters = new JSONObject();
                try {
                    parameters.put("userName", userName.getText().toString());
                    parameters.put("password", password.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String url = "123";
                GSONRequest<Boolean> login = new GSONRequest<Boolean>(Request.Method.POST, getResources().getString(R.string.login), Boolean.class, null, new Response.Listener<Boolean>() {
                    @Override
                    public void onResponse(Boolean response) {
                        Toast.makeText(LoginActivity.this, "Successfull", Toast.LENGTH_LONG).show();

                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        });

                MySingleton.getInstance(LoginActivity.this).addToRequestQueue(login);
            }

        });

    }
}
