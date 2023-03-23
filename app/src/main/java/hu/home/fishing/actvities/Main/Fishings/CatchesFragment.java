package hu.home.fishing.actvities.Main.Fishings;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.button.MaterialButton;

import java.io.IOException;
import java.util.ArrayList;

import hu.home.fishing.R;
import hu.home.fishing.actvities.Main.RequestHandler;
import hu.home.fishing.actvities.Main.Response;

public class CatchesFragment extends Fragment {

    private ListView CatchesListView;
    private ArrayList<Fishing> fishinglist = new ArrayList<>();
    private MaterialButton btnAddNewFishing;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_catches, container, false);
        init(view);

        btnAddNewFishing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CreateFishing.class);
                startActivity(intent);
            }
        });

        FishingListAdpter adapter = new FishingListAdpter(getContext(), R.layout.fishing_adapter_view, fishinglist);
        CatchesListView.setAdapter(adapter);

        //ON Selected iTem change to a new webpage where user can add fishes there
        CatchesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), CatchesAdding.class);
                startActivity(intent);

            }
        });


        // TESTIN THE LISTVIEW
        Fishing first = new Fishing("Kedven", "2002-012", 12, 12.0, "Budapest");
        Fishing second = new Fishing("BESZARAS", "20021-12", 12, 12.0, "Kekemet");
        Fishing secondu = new Fishing("Szarosz", "2002--12", 12, 12.0, "Balatonomok");
        fishinglist.add(first);
        fishinglist.add(second);
        fishinglist.add(secondu);

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