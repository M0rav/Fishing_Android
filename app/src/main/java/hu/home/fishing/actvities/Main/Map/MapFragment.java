package hu.home.fishing.actvities.Main.Map;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
// Google Maps
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import hu.home.fishing.R;
import hu.home.fishing.actvities.Main.Fishings.CreateFishing;
import hu.home.fishing.actvities.Main.Fishings.Fishing;
import hu.home.fishing.actvities.Main.Fishings.FishingListAdpter;
import hu.home.fishing.actvities.Main.RequestHandler;
import hu.home.fishing.actvities.Main.Response;


public class MapFragment extends Fragment {
    private EditText etTileCordinates;
    private MaterialButton btnCreateLoc;
    private Float latitude,longitude;
    private String POSTURL = "http://10.0.2.2:3000/locations/add";
    private String GETURL = "http://10.0.2.2:3000/locations/info";
    private ArrayList<Locations> locationsFishesArrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Initialize view
        View view=inflater.inflate(R.layout.fragment_map, container, false);
        mapFragment();
        init(view);
        //Initalize the location data
        MapFragment.RequestTask task = new MapFragment.RequestTask(GETURL,"GET",null);
        task.execute();

        btnCreateLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String comment = etTileCordinates.getText().toString().trim();
                Float xLoccord = latitude;
                Float yLoccord = longitude;
                if (comment.isEmpty()) {
                    etTileCordinates.setError("Nem lehet üresen hagyni a cím mezőt");
                    return;
                } else {
                    etTileCordinates.setError(null);
                }
                    Locations ping = new Locations(xLoccord,yLoccord,comment);
                    Gson json = new Gson();
                    MapFragment.RequestTask task = new MapFragment.RequestTask(POSTURL,"POST",json.toJson(ping));
                    task.execute();
                    MapFragment.RequestTask taskGet = new MapFragment.RequestTask(GETURL,"GET",null);
                    taskGet.execute();
            }
        });

        // Return view
        return view;
    }

    private void init(View view) {
        etTileCordinates = view.findViewById(R.id.editTextPlaceOfTheFishing);
        btnCreateLoc = view.findViewById(R.id.btmnFishingCreate);
    }



    private void mapFragment(){
        // Initialize map fragment
        SupportMapFragment supportMapFragment=(SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.google_map);

        // Async map
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                // When map is loaded
                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        // When clicked on map
                        // Initialize marker options
                        MarkerOptions markerOptions=new MarkerOptions();
                        markerOptions.position(latLng);
                        markerOptions.title(latLng.latitude+" : "+latLng.longitude);
                        googleMap.clear();
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
                        googleMap.addMarker(markerOptions);
                        GoogleMapOptions options = new GoogleMapOptions();
                        options.mapType(GoogleMap.MAP_TYPE_SATELLITE)
                                .compassEnabled(true)
                                .rotateGesturesEnabled(true)
                                .tiltGesturesEnabled(true);


                        latitude = (float) latLng.latitude;
                        longitude = (float) latLng.longitude;

                    }
                });
            }


        });

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
            Context context = requireActivity().getApplicationContext();
            SharedPreferences sharedPreferencese = context.getSharedPreferences("Adatok", Context.MODE_PRIVATE);
            try {

                switch (requestType) {
                    case "GET":
                        response = RequestHandler.get(requestUrl,  sharedPreferencese.getString("token",null));
                        break;
                    case "POST":
                        response = RequestHandler.post(requestUrl, requestParams,sharedPreferencese.getString("token",null));
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
                Toast.makeText(getContext(), "Hiba a felvétel során!", Toast.LENGTH_SHORT).show();

            } else {



            }
            switch (requestType) {
                case "GET":
                         Gson gson = new Gson();
                String json = response.getContent().toString();
                Type fishingLocationType = new TypeToken<List<Locations>>() {}.getType();
                List<Locations> locations = gson.fromJson(json, fishingLocationType);
                locationsFishesArrayList.clear(); // Clear the current list
                locationsFishesArrayList.addAll(locations); // Add all the items from 'fishings'
                    for (int i = 0; i <locations.size(); i++) {
                        Locations location = locations.get(i);
                        Float latitude = location.getxLoccord();
                        Float longitude = location.getyLoccord();
                        String comment = location.getComment();
                        MarkerOptions markerOptions = new MarkerOptions()
                                .position(new LatLng(latitude, longitude))
                                .title(comment);
                        // Add marker to the map
                        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.google_map);
                        mapFragment.getMapAsync(new OnMapReadyCallback() {
                            @Override
                            public void onMapReady(GoogleMap googleMap) {
                                googleMap.addMarker(markerOptions);
                            }
                        });
                    }

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
