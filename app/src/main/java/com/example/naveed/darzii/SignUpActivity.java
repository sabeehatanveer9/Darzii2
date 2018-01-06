package com.example.naveed.darzii;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    EditText mEmail, mName, mConfirmPassword;
    Button signIn;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    public static final String TAG = "SignUpActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        sp = getSharedPreferences("setting", MODE_PRIVATE);
        mEmail = (EditText) findViewById(R.id.mEmail);
        mName = (EditText) findViewById(R.id.mName);
        mConfirmPassword = (EditText) findViewById(R.id.mConfirmPassword);
        signIn = (Button) findViewById(R.id.signIn);

        onSignUpBtn();
    }

    private void onSignUpBtn() {

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<User> call = SingletonService.getService().userSignUp(new User(mName.getText().toString(),
                        mEmail.getText().toString(),
                        mConfirmPassword.getText().toString()));
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()) {
                            editor = sp.edit();
                            editor.putString("NAME", response.body().getName());
                            editor.putString("EMAIL", response.body().getEmail());
                            editor.apply();
                            startActivity(new Intent(SignUpActivity.this, UserHomeActivity.class));
                            finish();
                            Log.d(TAG, "onResponse:login success ");
                        } else if (response.code() != 200) {
                            Toast.makeText(SignUpActivity.this, "Error in Registration", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.d(TAG, "onFailure: sign up " + t.getLocalizedMessage());
                    }
                });
            }
        });
    }

}
