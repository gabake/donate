package com.example.admin.donate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Login_Activity extends AppCompatActivity implements View.OnClickListener {
    EditText edtUsername, edtPassword;
    Button btnLogin;
    TextView txtRegister, txtForgot;
    String urlRegister = "http://192.168.0.121:9999/dang-nhap";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);

        edtUsername = (EditText) findViewById(R.id.edtUsername);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        txtRegister = (TextView) findViewById(R.id.txtRegister);
        txtForgot = (TextView) findViewById(R.id.txtForgot);

        btnLogin.setOnClickListener(this);
        txtRegister.setOnClickListener(this);
        txtForgot.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnLogin:
                Register(urlRegister);
                break;

            case R.id.txtRegister:
                startActivity(new Intent(getApplicationContext(), Register_Activity.class));
                break;

            case R.id.txtForgot:
                startActivity(new Intent(getApplicationContext(), Activity_Forgot.class));
                break;
        }
    }

    private void Register(String url){

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest Stringrequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("")){
                    Toast.makeText(Login_Activity.this, "Dang nhap thanh cong", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Login_Activity.this, Activity_Home.class);
                    startActivity(i);
                } else {
                    Toast.makeText(Login_Activity.this, "Error 1!!!", Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Login_Activity.this, "Error 2!!"+ error, Toast.LENGTH_SHORT).show();
                        Log.d("error2", "onErrorResponse: "+ error);
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parmas = new HashMap<>();
                parmas.put("email", edtUsername.getText().toString());
                parmas.put("pwd", edtPassword.getText().toString());
                return parmas;
            }
        };
        requestQueue.add(Stringrequest);
    }
}
