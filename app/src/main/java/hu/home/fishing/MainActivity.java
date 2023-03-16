package hu.home.fishing;


import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;
    private String URLLogout = "http://10.0.2.2:3000/auth/logout";
    private String tokenUser;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //super.oncreate és setContentView között kell ezeket beállítani
        //teljesképernyő beállítása
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //alapértelmezett actionbar (Toolbar) elrejtése
        // getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        //alapértelmezett sötétmód kikapcsolása
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        init();

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_main_page:
                    frameLayout.setVisibility(View.GONE);
                    break;
                case R.id.nav_calendar:
                    frameLayout.setVisibility((View.VISIBLE));
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragmentContainer,new CalendarFragment()).commit();
                    break;
                case R.id.nav_map:
                    frameLayout.setVisibility(View.VISIBLE);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragmentContainer, new MapFragment()).commit();
                    break;
                case R.id.nav_catches:
                    frameLayout.setVisibility(View.VISIBLE);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragmentContainer, new CatchesFragment()).commit();
                    break;
                case R.id.nav_locations:
                    frameLayout.setVisibility(View.VISIBLE);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragmentContainer, new LocationsFragment()).commit();
                    break;
                // KAPCSOLAT alatti dolgok lekezelése és profil kijelntkeztetése
                case R.id.nav_logout:
                    SharedPreferences sharedPreferences = getSharedPreferences("Adatok", Context.MODE_PRIVATE);
                    Token tokenUser = new Token(sharedPreferences.getString("token", ""));
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.clear();
                    editor.commit();
                    Intent intent = new Intent(MainActivity.this, LogInActivity.class);
                    startActivity(intent);
                    Gson json = new Gson();
                    RequestTask task = new RequestTask(URLLogout, "DELETE", json.toJson(tokenUser));
                    task.execute();
                    finish();

                    //TODO KIjelentkezés

            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

    }

    public void init() {
        toolbar = findViewById(R.id.toolBar);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        frameLayout = findViewById(R.id.fragmentContainer);
        toggle = new ActionBarDrawerToggle(this, drawerLayout,
                toolbar, R.string.nyitva, R.string.zarva);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }



    private class RequestTask extends AsyncTask<Void, Void, Response> {
        String requestUrl;
        String requestType;
        String requestParams;

        public RequestTask(String requestUrl, String requestType, String requestParams) {
            this.requestUrl = requestUrl;
            this.requestType = requestType;
            this.requestParams = requestParams;
        }


        public RequestTask(String requestUrl, String requestType) {
            this.requestUrl = requestUrl;
            this.requestType = requestType;
        }

        @Override
        protected Response doInBackground(Void... voids) {
            Response response = null;
            SharedPreferences sharedPreferences = getSharedPreferences("Adatok",Context.MODE_PRIVATE);
            try {
                switch (requestType) {
                    case "GET":
                        response = RequestHandler.get(requestUrl,null);
                        break;
                    case "DELETE":
                        response = RequestHandler.delete(requestUrl,sharedPreferences.getString("token",null));
                        break;
                }
            } catch (IOException e) {
                Toast.makeText(MainActivity.this,
                        e.toString(), Toast.LENGTH_SHORT).show();
            }
            return response;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Response response) {
            super.onPostExecute(response);
            if (response.getResponseCode() >= 400) {
                Toast.makeText(MainActivity.this,
                        "Hiba történt a kilépés során", Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, response.getContent(), Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(MainActivity.this, "Sikeres kijelentkezés", Toast.LENGTH_SHORT).show();
            }
            switch (requestType) {
                case "GET":

                    break;
                case "POST":

                    break;
                case "PUT":

                    break;
                case "DELETE":

                    break;
            }
        }
    }
}