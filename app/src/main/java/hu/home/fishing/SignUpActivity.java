package hu.home.fishing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.android.volley.RequestTask;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

public class SignUpActivity extends AppCompatActivity {
    private EditText editTextEmail, editTextPassword, editTextPasswordConfirm,  editTextUsername, editTextPhoneNumber;
    private MaterialButton buttonLogin, buttonRegister;
    private String URL = "http:// 192.168.63.119:3000/api/addUser";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(R.layout.activity_sign_up);
        buttonRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String email = editTextEmail.getText().toString();
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();
                String phone = editTextPhoneNumber.getText().toString();
                if (username.isEmpty()) {
                    editTextPassword.setError("Nem lehet üres a jelszó");
                } else {
                    editTextPassword.setError(null);
                }
                if (password.isEmpty()) {
                    editTextPassword.setError("Nem lehet üres a jelszó");
                } else {
                    editTextPassword.setError(null);
                }
                if (email.isEmpty()) {
                    editTextEmail.setError("Nem lehet üres a jelszó");
                } else {
                    editTextEmail.setError(null);
                }
                if (phone.isEmpty()) {
                    editTextPhoneNumber.setError("Nem lehet üresen hagyni a telefon számot");
                } else {
                    editTextPhoneNumber.setError(null);
                }
                Users user = new Users(username, email, password, phone);
                Gson json = new Gson();
                RegisterFragment.RequestTask task = new RegisterFragment.RequestTask(URL, "POST", json.toJson(user));
                task.execute();

            }
        });
    }
    private void init(){
        editTextUsername = view.findViewById(R.id.editTextUsername);
        editTextPassword = view.findViewById(R.id.editTextPassword);
        editTextEmail = view.findViewById(R.id.editTextEmail);
        buttonRegister = view.findViewById(R.id.buttonRegister);
        editTextPhoneNumber = view.findViewById(R.id.editTextPhoneNumber);
    }
}