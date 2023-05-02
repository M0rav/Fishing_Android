package hu.home.fishing.actvities.Register;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import android.view.View;

import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.regex.Pattern;

import hu.home.fishing.actvities.Login.LogInActivity;
import hu.home.fishing.R;
import hu.home.fishing.actvities.Main.RequestHandler;
import hu.home.fishing.actvities.Main.Response;

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

                    if (isValid(email) == false) {
                        editTextEmail.setError("Nem megfelelő az e-mail formátuma");
                        return;
                    } else {
                        editTextEmail.setError(null);
                    }

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
                    if (isValidCase(password) == true) {
                        editTextPassword.setError("Minimum 1 szám és egy nagybetű kell");
                        return;
                    } else {
                        editTextPassword.setError(null);
                    }
                    if (isValidSpace(password) == false) {
                        editTextPassword.setError("Maximum 20 karakter lehet és nem lehet szoköz");
                        return;
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
                if (phone.length() < 7) {
                    editTextPhoneNumber.setError("Nem megfelelő a telefonszám formátuma");
                    return;
                } else {
                    editTextPhoneNumber.setError(null);
                }


                Users user = new Users(username, email, password, phone);
                Gson json = new Gson();
                RequestTask task = new RequestTask(URL,"POST", json.toJson(user));
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

    private boolean isValid(String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;

        return pat.matcher(email).matches();
    }
    private boolean isValidCase(String email){
        String emailRegex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])";
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;

        return pat.matcher(email).matches();
    }
    //8-20 karakter  + szoköz
    private boolean isValidSpace(String email){
        String emailRegex = "(?=\\S+$).{8,20}$";
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;

        return pat.matcher(email).matches();
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
            SharedPreferences sharedPreferences = getSharedPreferences("Adatok", Context.MODE_PRIVATE);
            try {

                switch (requestType) {
                    case "GET":
                        response = RequestHandler.get(requestUrl,null);
                        break;
                    case "POST":
                        response = RequestHandler.post(requestUrl, requestParams,null);
                        break;
                    case "DELETE":
                        response = RequestHandler.delete(requestUrl,sharedPreferences.getString("token",null));
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
                Intent intent = new Intent(getApplicationContext(), LogInActivity.class);
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

                    break;
            }
        }
    }

}