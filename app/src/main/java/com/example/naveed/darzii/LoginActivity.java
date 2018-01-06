package com.example.naveed.darzii;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText mEmail, mPassword;
    Button login_btn;
    private static final String TAG = "MTAG";
    TextView newAccount;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_btn = (Button) findViewById(R.id.loginBtn);
        initViews();
        sp = getSharedPreferences("setting", MODE_PRIVATE);
        String name = sp.getString("NAME", "none");
        if (!name.equals("none")) {
            startActivity(new Intent(LoginActivity.this, UserHomeActivity.class));
            finish();
        }


        newAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });

        onLoginBtn();
    }

    private void onLoginBtn() {
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: ");
                Call<User> call = SingletonService.getService().userLogin(new User(mEmail.getText().toString(),
                        mPassword.getText().toString()));

                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()) {
                            editor = sp.edit();
                            editor.putString("NAME", response.body().getName());
                            editor.putString("EMAIL", response.body().getEmail());
                            editor.apply();
                            startActivity(new Intent(LoginActivity.this, UserHomeActivity.class));
                            finish();
                            Log.d(TAG, "onResponse:login success ");
                        } else if (response.code() == 400) {
                            mEmail.setError("Invalid Email");
                            mPassword.setError("Invalid Password");
                            Log.d(TAG, "onResponse: else" + response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.d(TAG, "onFailure: login error: " + t.getLocalizedMessage());
                    }
                });


            }
        });
    }


    /*----Initialize the views---------*/
    public void initViews() {
        newAccount = (TextView) findViewById(R.id.txtNewAccount);
        mEmail = (EditText) findViewById(R.id.mEmail);
        mPassword = (EditText) findViewById(R.id.mName);

    }

}
