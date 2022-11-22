package com.ass2.i190626_190438;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {

    private EditText name,email,password;
    private CheckBox agree;
    ImageView g1,g2,g3;
    View sg1, sg2, sg3; // selected gender
    String gender="";
    String terms="";
    String n,em,pass;
    private String URL = "http://192.168.18.107/smd-assignment03/signup.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name = findViewById(R.id.input_name);
        email = findViewById(R.id.input_email);
        password = findViewById(R.id.input_pass);
        agree = findViewById(R.id.checkbox);
        g1 = findViewById(R.id.g1);
        g2 = findViewById(R.id.g2);
        g3 = findViewById(R.id.g3);
        sg1 = findViewById(R.id.neon_g1);
        sg2 = findViewById(R.id.neon_g2);
        sg3 = findViewById(R.id.neon_g3);

        agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                terms = "true";
            }
        });

        g1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender = "male";
                sg1.setVisibility(View.VISIBLE);
                sg2.setVisibility(View.INVISIBLE);
                sg3.setVisibility(View.INVISIBLE);
            }
        });

        g2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender = "female";
                sg2.setVisibility(View.VISIBLE);
                sg1.setVisibility(View.INVISIBLE);
                sg3.setVisibility(View.INVISIBLE);
            }
        });

        g3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender = "unknown";
                sg3.setVisibility(View.VISIBLE);
                sg1.setVisibility(View.INVISIBLE);
                sg2.setVisibility(View.INVISIBLE);
            }
        });

        final Button signup = findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SignUp.this, Profile.class);
                if (RegisterUser() && GenderAgree()) {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    if ("SUCCESS".equals(response)) {
                                        Toast.makeText(SignUp.this, "SignUp Success!!!", Toast.LENGTH_LONG).show();

                                    } else if (response.equals("FAILURE")) {
                                        Toast.makeText(SignUp.this, "SignUp Failure!!!", Toast.LENGTH_LONG).show();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(SignUp.this, error.toString().trim(),Toast.LENGTH_LONG).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> data = new HashMap<>();
                            data.put("name",n);
                            data.put("email", em);
                            data.put("password", pass);
                            data.put("gender", gender);
                            return data;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(SignUp.this);
                    requestQueue.add(stringRequest);
                    Toast.makeText(SignUp.this, "SignUp Success!",Toast.LENGTH_LONG).show();
                    startActivity(intent);
                }
            }
        });

        final Button signin = findViewById(R.id.signin);
        signin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, SignIn.class);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(intent);
                    }
                },10);
            }
        });
    }

    private Boolean GenderAgree() {
        boolean check = true;

        if (gender.equals("male") || gender.equals("female") || gender.equals("unknown")) {
            // Gender = Male or Female or Unknown
            // Selecting the image
        }
        else {
            Toast.makeText(this,"Specify your gender.",Toast.LENGTH_LONG).show();
            check = false;
        }
        if (terms.equals("true")) {
            // Agreed to the terms and conditions
            // Thorugh CheckBox
        }
        else {
            Toast.makeText(this,"Kindly agree to the terms.",Toast.LENGTH_LONG).show();
            check = false;
        }
        return check;
    }

    private Boolean RegisterUser() {

        n = name.getText().toString().trim();
        em = email.getText().toString().trim();
        pass = password.getText().toString().trim();
        boolean checks = true;

        // Applying multiple checks
        if (n.isEmpty()) {
            name.setError("Full name is required!");
            name.requestFocus();
            checks = false;
        }
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