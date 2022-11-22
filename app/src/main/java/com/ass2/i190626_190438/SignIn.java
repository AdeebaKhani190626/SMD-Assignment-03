package com.ass2.i190626_190438;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
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

public class SignIn extends AppCompatActivity {

    private EditText password,email;
    int count = 0;
    TextView showpass;
    Button forgot;
    String em,pass;
    private String URL = "http://192.168.18.107/smd-assignment03/login.php";
    private String URL2 = "http://192.168.18.107/smd-assignment03/forgotPassword.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        password = findViewById(R.id.input_pass);
        email = findViewById(R.id.input_email);
        showpass = findViewById(R.id.showpass);
        forgot = findViewById(R.id.fogotpass);

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                em = email.getText().toString().trim();
                boolean checks = true;

                if (em.isEmpty()) {
                    email.setError("Email is required!");
                    email.requestFocus();
                    checks = false;
                } if (!Patterns.EMAIL_ADDRESS.matcher(em).matches()) {
                    email.setError("Please provide valid email!");
                    email.requestFocus();
                    checks = false;
                }

                if (checks)
                {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, URL2,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    if (response.equals("SUCCESS")) {
                                        Log.d("Forgot", "Success!!!");
                                        Toast.makeText(SignIn.this, "Forgot Success!",Toast.LENGTH_LONG).show();

                                    } else if (response.equals("FAILURE")) {
                                        Log.d("Forgot", "Failure!!!");
                                        Toast.makeText(SignIn.this, "Forgot Failure!",Toast.LENGTH_LONG).show();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("Forgot Error", "Not Happening!!!");
                            Toast.makeText(SignIn.this, error.toString().trim(),Toast.LENGTH_LONG).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> data = new HashMap<>();
                            data.put("email", em);
                            return data;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(SignIn.this);
                    requestQueue.add(stringRequest);
                }
            }
        });

        showpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                if (count%2 == 0)
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                else
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
        });

        final Button signin = findViewById(R.id.signin);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SignIn.this, HomePage.class);
                if (RegisterUser()) {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    if (response.equals("Success")) {
                                        Log.d("SignUp", "Success!!!");
                                        Toast.makeText(SignIn.this, "SignIn Success!",Toast.LENGTH_LONG).show();

                                    } else if (response.equals("Failure")) {
                                        Log.d("SignUp", "Failure!!!");
                                        Toast.makeText(SignIn.this, "SignUp Failure!",Toast.LENGTH_LONG).show();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("SignUp Error", "Not Happening!!!");
                            Toast.makeText(SignIn.this, error.toString().trim(),Toast.LENGTH_LONG).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> data = new HashMap<>();
                            data.put("email", em);
                            data.put("password", pass);
                            return data;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(SignIn.this);
                    requestQueue.add(stringRequest);
                    Toast.makeText(SignIn.this, "SignIn Success!",Toast.LENGTH_LONG).show();
                    startActivity(intent);
                }
            }
        });

        final Button signup = findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(SignIn.this, SignUp.class);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(intent);
                    }
                },10);
            }
        });
    }

    private Boolean RegisterUser() {

        em = email.getText().toString().trim();
        pass = password.getText().toString().trim();
        boolean checks = true;

        // Applying multiple checks
        if (em.isEmpty()) {
            email.setError("Email is required!");
            email.requestFocus();
            checks = false;
        }
        if (pass.isEmpty()) {
            password.setError("Password is required!");
            password.requestFocus();
            checks = false;
        }
        if (pass.length() < 6) {
            password.setError("Min Password length should be 6 characters!");
            password.requestFocus();
            checks = false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(em).matches()) {
            email.setError("Please provide valid email!");
            email.requestFocus();
            checks = false;
        }
        // returns true if all checks satisfied and false if even 1 check is not satisfied
        return checks;
    }
}