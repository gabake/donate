package com.example.admin.donate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class Register_Activity extends AppCompatActivity {
    String urlRegister = "http://10.200.203.195:8080/Donate_app/Register.php";
    String email, username, password;
    EditText edtEmail, edtUsername,edtPassword;
    Button btnOk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);
        AnhXa();
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register(urlRegister);
            }
        });
    }

    private void AnhXa(){
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtUsername = (EditText) findViewById(R.id.edtUsername);
        edtPassword = (EditText) findViewById(R.id.edtUsername);
        btnOk = (Button) findViewById(R.id.btnOk);
    }

    private void getText(){
         email = edtEmail.getText().toString();
         username = edtUsername.getText().toString();
         password = edtPassword.getText().toString();

    }

    private void Register(String url){
        getText();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest Stringrequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("success")){
                    Toast.makeText(Register_Activity.this, "Dang ky thanh cong", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Register_Activity.this, Activity_Home.class);
                    startActivity(i);
                } else {
                    Toast.makeText(Register_Activity.this, "Error 1!!!", Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Register_Activity.this, "Error 2!!"+ error, Toast.LENGTH_SHORT).show();
                        Log.d("error2", "onErrorResponse: "+ error);
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parmas = new HashMap<>();
                parmas.put("email", edtEmail.getText().toString());
                parmas.put("username", edtUsername.getText().toString());
                parmas.put("password", edtPassword.getText().toString());
                return parmas;
            }
        };
        requestQueue.add(Stringrequest);
    }
}
