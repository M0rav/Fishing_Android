package hu.home.fishing.actvities.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;

import java.io.IOException;

import hu.home.fishing.actvities.Main.MainActivity;
import hu.home.fishing.R;
import hu.home.fishing.actvities.Main.RequestHandler;
import hu.home.fishing.actvities.Main.Response;
import hu.home.fishing.actvities.Register.SignUpActivity;

public class LogInActivity extends AppCompatActivity {
    private EditText editTextLogInUsername, editTextPassword;
    private String URL = "http://10.0.2.2:3000/auth/login";
    private MaterialButton buttonLogin, buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        init();
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
                finish();

            }
        });
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editTextLogInUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                if (username.isEmpty()) {
                    editTextLogInUsername.setError("Nem lehet üres a felhasználónév");
                    return;
                } else {
                    editTextLogInUsername.setError(null);
                }
                if (password.isEmpty()) {
                    editTextPassword.setError("Nem lehet üres a felhasználónév");
                    return;
                } else {
                    editTextPassword.setError(null);
                }
                Login user = new Login(username, password);
                Gson json = new Gson();
                RequestTask task = new RequestTask(URL,"POST", json.toJson(user));
                task.execute();
            }
        });


    }

    private void init() {
        editTextLogInUsername = findViewById(R.id.editTextLogInUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonRegister = findViewById(R.id.buttonRegister);
    }

    public class RequestTask extends AsyncTask<Void, Void, Response> {
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
                        response = RequestHandler.get(requestUrl, null);
                        break;
                    case "POST":
                        response = RequestHandler.post(requestUrl, requestParams,null);
                        break;
                    case "PUT":
                        response = RequestHandler.put(requestUrl, requestParams,null);
                        break;
                    case "DELETE":
                        response = RequestHandler.delete(requestUrl,sharedPreferences.getString("token",null));
                        break;
                }
            } catch (IOException e) {
                Toast.makeText(LogInActivity.this,
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
            Gson converter = new Gson();
            if (response.getResponseCode() >= 400) {
                Toast.makeText(LogInActivity.this,
                        "Hiba történt a belépés során!", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(LogInActivity.this, "Sikeres Bejelentkezés", Toast.LENGTH_SHORT).show();
                Token token = converter.fromJson(response.getContent(), Token.class);
                SharedPreferences sharedPreferences = getSharedPreferences("Adatok", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("token", token.getToken());
                editor.commit();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
            switch (requestType) {
                case "GET":

                    break;
                case "POST":



                    break;
                case "PUT":

                    break;
                case "DELETE":
            }
        }
    }
}