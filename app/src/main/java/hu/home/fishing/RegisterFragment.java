package hu.home.fishing;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import java.io.IOException;

public class RegisterFragment extends Fragment {
    private TextInputLayout editTextEmail, editTextPassword, editTextUsername, editTextPhoneNumber;
    private String URL = "http:// 192.168.63.119:3000/api/addUser";
    private MaterialButton buttonRegister;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        init(view);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextEmail.getEditText().getText().toString();
                String username = editTextUsername.getEditText().getText().toString();
                String password = editTextPassword.getEditText().getText().toString();
                String phone = editTextPhoneNumber.getEditText().getText().toString();
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
                if (phone.isEmpty()){
                    editTextPhoneNumber.setError("Nem lehet üresen hagyni a telefon számot");
                }else{
                    editTextPhoneNumber.setError(null);
                }
                Users user = new Users(username,email,password,phone);
                Gson json = new Gson();
                RequestTask task = new RequestTask(URL,"POST",json.toJson(user));
                task.execute();
            }

        });
        return view;
    }

    private void init(View view) {
        editTextUsername = view.findViewById(R.id.editTextUsername);
        editTextPassword = view.findViewById(R.id.editTextPassword);
        editTextEmail = view.findViewById(R.id.editTextEmail);
        buttonRegister = view.findViewById(R.id.buttonRegister);
        editTextPhoneNumber = view.findViewById(R.id.editTextPhoneNumber);
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
                Toast.makeText(getActivity().getApplicationContext(),
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
                Toast.makeText(getActivity().getApplicationContext(),
                        "Hiba történt a regisztráció során", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(getActivity().getApplicationContext(), "Sikeres Regisztráció", Toast.LENGTH_SHORT).show();
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