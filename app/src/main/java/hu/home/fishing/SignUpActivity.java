package hu.home.fishing;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.os.PersistableBundle;

import android.view.View;

import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import com.google.gson.Gson;

import java.io.IOException;

public class SignUpActivity extends AppCompatActivity {
    private EditText editTextEmail, editTextPassword, editTextPasswordConfirm, editTextUsername, editTextPhoneNumber;
    private String URL = "http://10.0.2.2:3000/auth/users";
    //10.0.2.2:3000
    private MaterialButton buttonRegister, buttonlogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        init();
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextEmail.getText().toString().trim();
                String username = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                String passwordConfirm = editTextPasswordConfirm.getText().toString().trim();
                String phone = editTextPhoneNumber.getText().toString().trim();
                if (email.isEmpty()) {
                    editTextEmail.setError("Nem lehet üres a email");
                    return;
                } else {
                    editTextEmail.setError(null);

                    if (username.isEmpty()) {
                        editTextUsername.setError("Nem lehet üres a felhasználónév");
                        return;
                    } else {
                        editTextUsername.setError(null);
                    }
                    if (username.length() < 5 )
                    {
                        editTextUsername.setError("Minimum öt karakter kell a felhasználónál");
                    } else {
                        editTextUsername.setError(null);
                    }
                    if (password.isEmpty()) {
                        editTextPassword.setError("Nem lehet üres a jelszó");
                        return;
                    } else {
                        editTextPassword.setError(null);
                    }
                    if (password.length() < 8) {
                        editTextPassword.setError("Minimum nyolc karakter kell a jelszónál");
                    } else {
                        editTextPassword.setError(null);
                    }
                    if (!password.equals(passwordConfirm)) {
                        editTextPasswordConfirm.setError("Nem egyezik meg a jelszó");
                        return;
                    } else {
                        editTextPasswordConfirm.setError(null);
                    }
                }
                if (phone.isEmpty()) {
                    editTextPhoneNumber.setError("Nem lehet üresen hagyni a telefon számot");
                    return;
                } else {
                    editTextPhoneNumber.setError(null);
                }
                Users user = new Users(username, email, password, phone);
                Gson json = new Gson();
                RequestTask task = new RequestTask(URL, "POST", json.toJson(user));
                task.execute();
            }
        });
        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LogInActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void init() {
        editTextPasswordConfirm = findViewById(R.id.editTextPasswordConfirm);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextEmail = findViewById(R.id.editTextEmail);
        buttonRegister = findViewById(R.id.buttonRegisterFinal);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        buttonlogin = findViewById(R.id.buttonBack);
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
            try {
                switch (requestType) {
                    case "GET":
                        response = RequestHandler.get(requestUrl);
                        break;
                    case "POST":
                        response = RequestHandler.post(requestUrl, requestParams);
                        break;
                    case "PUT":
                        response = RequestHandler.put(requestUrl, requestParams);
                        break;
                    case "DELETE":
                        response = RequestHandler.delete(requestUrl + "/" + requestParams);
                        break;
                }
            } catch (IOException e) {
                Toast.makeText(SignUpActivity.this,
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
                Toast.makeText(SignUpActivity.this,
                        "Hiba történt a regisztráció során", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(SignUpActivity.this, "Sikeres Regisztráció", Toast.LENGTH_SHORT).show();
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