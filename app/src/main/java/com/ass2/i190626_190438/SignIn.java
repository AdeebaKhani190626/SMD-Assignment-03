package com.ass2.i190626_190438;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignIn extends AppCompatActivity {

    public static String PREFS_NAME="MyPrefsFile";
    private EditText password,email;
    int count = 0;
    TextView showpass;
    Button forgot;
    String em,pass;
    private String URL = "http://192.168.0.105/smd-assignment03/login.php";
    private String URLPass = "http://192.168.0.105/smd-assignment03/forgotPassword.php";

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
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, URLPass,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    try {
                                        JSONObject obj=new JSONObject(response);
                                        if(obj.getInt("code") == 1)
                                        {
                                            password.setText(obj.getString("password"));
                                            Toast.makeText(SignIn.this, "Password Issue Resolved",Toast.LENGTH_LONG).show();
                                        }
                                        else{
                                            Toast.makeText(SignIn.this, obj.get("msg").toString(),Toast.LENGTH_LONG
                                            ).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();

                                        Toast.makeText(SignIn.this, "Incorrect JSON",Toast.LENGTH_LONG).show();
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

                                    try {
                                        JSONObject obj=new JSONObject(response);
                                        if(obj.getInt("code") == 1)
                                        {
                                            if (obj.getString("email").equals(em) && obj.getString("password").equals(pass))
                                            {
                                                Toast.makeText(SignIn.this, "SignIn Success!",Toast.LENGTH_LONG).show();
                                                SharedPreferences sharedPreferences = getSharedPreferences(SignIn.PREFS_NAME,0);
                                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                                editor.putBoolean("hasLoggedIn",true);
                                                editor.commit();

                                                startActivity(intent);
                                                finish();
                                            }
                                        }
                                        else{
                                            Toast.makeText(SignIn.this, obj.get("msg").toString(),Toast.LENGTH_LONG
                                            ).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();

                                        Toast.makeText(SignIn.this, "Incorrect JSON",Toast.LENGTH_LONG).show();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("SignIn Error", "Not Happening!!!");
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