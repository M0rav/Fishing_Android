package hu.home.fishing;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

public class ProfileFragment extends Fragment {
    private TextInputLayout editTextEmail, editTextPassword, editTextUsername;

    private MaterialButton buttonRegister;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_profile, container, false);
    init(view);
    buttonRegister.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String email = editTextEmail.getEditText().getText().toString();
            String username = editTextUsername.getEditText().getText().toString();
            String password = editTextPassword.getEditText().getText().toString();
            if (username.isEmpty()){
                editTextPassword.setError("Nem lehet üres a jelszó");
            }else {
                editTextPassword.setError(null);
            }
            if(password.isEmpty()){
                editTextPassword.setError("Nem lehet üres a jelszó");
            }else {
                editTextPassword.setError(null);
            }
            if(email.isEmpty()){
                editTextEmail.setError("Nem lehet üres a jelszó");
            }else {
                editTextEmail.setError(null);
            }
        }
    });
        return view;
    }

    private void init(View view) {
        editTextUsername = view.findViewById(R.id.editTextUsername);
        editTextPassword = view.findViewById(R.id.editTextPassword);
        editTextEmail = view.findViewById(R.id.editTextEmail);
        buttonRegister = view.findViewById(R.id.buttonRegister);
    }
}