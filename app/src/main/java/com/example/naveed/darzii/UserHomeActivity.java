package com.example.naveed.darzii;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.naveed.darzii.Adapters.InformationAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserHomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String TAG = "MTAG";
    ArrayList<Information> list;
    InformationAdapter adapter;
    RecyclerView recyclerView;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    TextView name, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        sp = getSharedPreferences("setting", MODE_PRIVATE);
        list = new ArrayList<>();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserHomeActivity.this, AddActivity.class));
            }
        });

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View navHeader = navigationView.getHeaderView(0);
        name = navHeader.findViewById(R.id.txtName);
        email = navHeader.findViewById(R.id.txtEmail);
        name.setText(sp.getString("NAME", ""));
        email.setText(sp.getString("EMAIL", ""));
        navHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserUpdateFragment fragment = new UserUpdateFragment();
                fragment.setValues(sp.getString("NAME", ""),
                        sp.getString("EMAIL", ""),
                        "0");
                getSupportFragmentManager().beginTransaction().add(R.id.contentFragment, fragment).addToBackStack(null).commit();
                drawer.closeDrawer(GravityCompat.START);
            }
        });
        infoRequest();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.log_out) {
            editor = sp.edit();
            editor.clear();
            editor.apply();
            Intent intent = new Intent(UserHomeActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();


            // Handle the camera action
        } else if (id == R.id.about_us) {
            Toast.makeText(this, "" + id, Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void recyclerView() {
        adapter = new InformationAdapter(UserHomeActivity.this, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

    private void infoRequest() {
        Call<List<Information>> call = SingletonService.getService().getInfo();

        call.enqueue(new Callback<List<Information>>() {
            @Override
            public void onResponse(Call<List<Information>> call, Response<List<Information>> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: successful ");

                    list.addAll(response.body());
                    recyclerView();
                } else if (response.code() != 200) {
                    Log.d(TAG, "onResponse: on else " + response.code() + "  message: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Information>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
    }
}
