package com.example.naveed.darzii;

import android.content.Intent;
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

public class AddActivity extends AppCompatActivity {

    private static final String TAG = "MTAG";
    EditText customerName, customerAddress, collarSize, shoulderSize, chestSize, armLength, shirtLength;
    Button Insert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        customerName = (EditText) findViewById(R.id.et1);
        customerAddress = (EditText) findViewById(R.id.et2);
        collarSize = (EditText) findViewById(R.id.et3);
        shoulderSize = (EditText) findViewById(R.id.et4);
        chestSize = (EditText) findViewById(R.id.et5);
        armLength = (EditText) findViewById(R.id.et6);
        shirtLength = (EditText) findViewById(R.id.et7);
        Insert = (Button) findViewById(R.id.btn);

        Insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Information user = new Information(
                        customerName.getText().toString(),
                        customerAddress.getText().toString(),
                        collarSize.getText().toString(),
                        chestSize.getText().toString(),
                        armLength.getText().toString(),
                        shirtLength.getText().toString()
                );
                Add(user);

            }
        });
    }

    public void Add(Information user) {


        Call<Information> call = SingletonService.getService().addInfo(user);

        call.enqueue(new Callback<Information>() {
            @Override
            public void onResponse(Call<Information> call, Response<Information> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(AddActivity.this, "on success: " + response.body().getAddress(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddActivity.this, UserHomeActivity.class));
                    finish();
                } else if (response.code() != 200) {
                    Log.d("MTAG", "onFailure: " + response.code());
                    Log.d("MTAG", "onFailure: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Information> call, Throwable t) {
                Log.d("MTAG", "onFailure: " + t.getLocalizedMessage());
            }
        });


    }
}
