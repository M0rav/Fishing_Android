package hu.home.fishing.actvities.Main.Fishings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.google.android.material.button.MaterialButton;

import java.io.IOException;
import java.util.ArrayList;

import hu.home.fishing.R;
import hu.home.fishing.actvities.Main.MainActivity;
import hu.home.fishing.actvities.Main.RequestHandler;
import hu.home.fishing.actvities.Main.Response;

public class CatchesAdding extends AppCompatActivity {
    private MaterialButton  btnAddFish, btnBack;
    private ArrayList<Catches> caughtFishesArrayList = new ArrayList<>();
    private FrameLayout frameLayout;
    private ListView catchesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catches_adding);

        init();

        Catches proba = new Catches("Ponty", 23.2, 122);
        Catches proba2 = new Catches("Csuka", 23.2, 122);
        Catches proba3 = new Catches("Ponty", 23.2, 122);
        caughtFishesArrayList.add(proba);
        caughtFishesArrayList.add(proba2);
        caughtFishesArrayList.add(proba3);

        CaughtFishesListAdapter adapter = new CaughtFishesListAdapter(this, R.layout.activity_fishing_catches_list_adapter, caughtFishesArrayList);
        catchesListView.setAdapter(adapter);




            btnBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                   Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                   startActivity(intent);
                }
            });

    }
    private void init(){
        btnBack = findViewById(R.id.btnCatchesCancel);
        catchesListView =  findViewById(R.id.caughtFishesListView);

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
                            response = RequestHandler.get(requestUrl, null);
                            break;
                        case "POST":
                            response = RequestHandler.post(requestUrl, requestParams, null);
                            break;
                        case "PUT":
                            response = RequestHandler.put(requestUrl, requestParams, null);
                            break;
                        case "DELETE":
                            response = RequestHandler.delete(requestUrl + "/" + requestParams, null);
                            break;
                    }
                } catch (IOException e) {

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


                } else {

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






