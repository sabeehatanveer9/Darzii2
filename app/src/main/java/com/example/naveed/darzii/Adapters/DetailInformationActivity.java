package com.example.naveed.darzii.Adapters;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.naveed.darzii.Information;
import com.example.naveed.darzii.R;
import com.example.naveed.darzii.SingletonService;
import com.example.naveed.darzii.UserHomeActivity;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailInformationActivity extends AppCompatActivity {

    Information information;
    ImageButton delete;
    TextView textId, textName, textAddress, textCollarSize, textChestSize, textArmLenght, textShirtLenght;
    int id;
    String name, address, collar_size, chest_size, arm_lenght, shirt_lenght;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_information);

        delete = (ImageButton) findViewById(R.id.imgBtndelete);
        textId = (TextView) findViewById(R.id.tvId);
        textName = (TextView) findViewById(R.id.tvName);
        textAddress = (TextView) findViewById(R.id.tvAddress);
        textCollarSize = (TextView) findViewById(R.id.tvCollarSize);
        textChestSize = (TextView) findViewById(R.id.tvChestSize);
        textArmLenght = (TextView) findViewById(R.id.tvArmLenght);
        textShirtLenght = (TextView) findViewById(R.id.tvShirtLenght);


        id = this.getIntent().getIntExtra("id", -1);
        name = this.getIntent().getStringExtra("name");
        address = this.getIntent().getStringExtra("address");
        collar_size = this.getIntent().getStringExtra("collarSize");
        chest_size = this.getIntent().getStringExtra("chestSize");
        shirt_lenght = this.getIntent().getStringExtra("shirtLenght");
        arm_lenght = this.getIntent().getStringExtra("armLenght");

        textId.setText(id+"");
        textName.setText(name);
        textAddress.setText(address);
        textCollarSize.setText(collar_size);
        textChestSize.setText(chest_size);
        textArmLenght.setText(shirt_lenght);
        textShirtLenght.setText(shirt_lenght);

        Toast.makeText(this, "id " + id, Toast.LENGTH_SHORT).show();
        deleteInfo();
    }

    private void deleteInfo() {
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<ResponseBody> call = SingletonService.getService().deleteInfo(id);

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(DetailInformationActivity.this, "successfully Deleted", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(DetailInformationActivity.this, UserHomeActivity.class));
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d(UserHomeActivity.TAG, "onFailure: delete  " + t.getLocalizedMessage());
                    }
                });
            }
        });

    }

}
