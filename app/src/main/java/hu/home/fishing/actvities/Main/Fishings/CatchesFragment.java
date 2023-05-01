package hu.home.fishing.actvities.Main.Fishings;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import hu.home.fishing.R;
import hu.home.fishing.actvities.Main.RequestHandler;
import hu.home.fishing.actvities.Main.Response;

public class CatchesFragment extends Fragment {

    private ListView CatchesListView;
    private ArrayList<Fishing> fishinglist = new ArrayList<>();
    private MaterialButton btnAddNewFishing;
    private ArrayList<Fishing> caughtFishesArrayList = new ArrayList<>();
    String URL = "http://10.0.2.2:3000/catches/info";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_catches, container, false);
        init(view);
        Gson json = new Gson();
        CatchesFragment.RequestTask task = new CatchesFragment.RequestTask(URL,"GET",null);
        task.execute();

        btnAddNewFishing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CreateFishing.class);
                startActivity(intent);
            }
        });

        Fishing proba = new Fishing("Ponty", "23.2","23","Budapest");
        Fishing proba1 = new Fishing("Ponty", "23.2","23","Budapest");
        caughtFishesArrayList.add(proba);
        caughtFishesArrayList.add(proba1);
        FishingListAdpter adapter = new FishingListAdpter(getContext(), R.layout.fishing_adapter_view, caughtFishesArrayList);
        CatchesListView.setAdapter(adapter);

        //ON Selected iTem change to a new webpage where user can add fishes there



        return view;
    }


    //Fragment-nél igy kell inicializálni, hogy az init-be át adjuk a View-t (sima activity-ben meg úgy, ahogy eddig csináltuk)
    private void init(View view) {
        CatchesListView = view.findViewById(R.id.FishingListView);
        btnAddNewFishing = view.findViewById(R.id.btnCatchAdd);
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
            Context context = requireActivity().getApplicationContext();
            SharedPreferences sharedPreferencese = context.getSharedPreferences("Adatok", Context.MODE_PRIVATE);
            try {

                switch (requestType) {
                    case "GET":
                        response = RequestHandler.get(requestUrl,  sharedPreferencese.getString("token",null));
                        break;
                    case "POST":
                        response = RequestHandler.post(requestUrl, requestParams,null);
                        break;
                    case "DELETE":
                        response = RequestHandler.delete(requestUrl, null);
                        break;
                }
            } catch (IOException e) {
                Toast.makeText(getActivity(),
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


            } else {
                Gson gson = new Gson();
                String json = response.getContent().toString();
                Type fishingListType = new TypeToken<List<Fishing>>() {}.getType();
                List<Fishing> fishings = gson.fromJson(json, fishingListType);
                caughtFishesArrayList.clear(); // Clear the current list
                caughtFishesArrayList.addAll(fishings); // Add all the items from 'fishings'
                FishingListAdpter adapter = new FishingListAdpter(getContext(), R.layout.fishing_adapter_view, caughtFishesArrayList);
                CatchesListView.setAdapter(adapter);

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