package hu.home.fishing.actvities.Main.Fishings;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;

import java.io.IOException;

import hu.home.fishing.R;
import hu.home.fishing.actvities.Main.MainActivity;
import hu.home.fishing.actvities.Main.RequestHandler;
import hu.home.fishing.actvities.Main.Response;


public class CreateFishing extends AppCompatActivity {
    private EditText  etFishingweight, etFishingLEngth,etFishingPlace;
    private Spinner tvFishingTitle;
    private MaterialButton btnFishingCreate, btnFishingCancel;
    private String URL = "http://10.0.2.2:3000/catches/add";


    // private Date selectedDate = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_fishing);
        init();
        SpinnerList();

        btnFishingCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });


        // set on click listener for create fishing button
        btnFishingCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               String species = tvFishingTitle.getSelectedItem().toString();
                String weight = etFishingLEngth.getText().toString().trim();
                String length = etFishingweight.getText().toString().trim();
                String location = etFishingPlace.getText().toString().trim();
                if (length.isEmpty()) {
                    etFishingLEngth.setError("Nem lehet üresen hagyni a hossz mezőt");
                    return;
                } else {
                    etFishingLEngth.setError(null);
                }if (length.isEmpty()) {
                    etFishingweight.setError("Nem lehet üresen hagyni a suly mezőt");
                    return;
                } else {
                    etFishingweight.setError(null);
                }
                    if (length.isEmpty()) {
                        etFishingPlace.setError("Nem lehet üresen hagyni a hely mezőt");
                        return;
                    } else {
                        etFishingPlace.setError(null);
                    }


                    Fishing catches = new Fishing(species, weight,length,location);
                    Gson json = new Gson();
                    RequestTask task = new RequestTask(URL,"POST",json.toJson(catches));
                task.execute();



            }

        });
    }


    private void init() {
        tvFishingTitle = findViewById(R.id.spinnerFishes);
        etFishingweight = findViewById(R.id.editTextWeightOfFish);
        etFishingLEngth = findViewById(R.id.editTextfishLengt);
        etFishingPlace = findViewById(R.id.editTextWaterDistrict);
        btnFishingCancel = findViewById(R.id.buttonFishingCancel);
        btnFishingCreate = findViewById(R.id.btmnFishingCreate);

    }
    private void SpinnerList(){
        //create a list of items for the spinner.
        String[] items = new String[]{"Ponty", "Harcsa", "Garda", "Csuka", "Busa", "Compó","Jász-keszeg","Kárász","Kecsege","Márna","Paduc","Szilvaorrú keszeg","Amur","Bagolykeszeg","Bodorka","Dévérkeszeg","Ezüstkárász","Fehér busa","Pettyes busa","Karikakeszeg","Kínai razbóra","Lapátorrú tok vagy kanalas tok","Lapos keszeg","Naphal","Nyúldomolykó","Tüskés pikó","Szélhajtó küsz","Vágódurbincs","Vörösszárnyú keszeg","Balin","Domolykó","Süllő","Kősüllő","Menyhal","Sebes pisztráng","Afrikai harcsa","Angolna","Fekete sügér","Kessler géb","Pataki szajbling","Sügér","Szivárványos pisztráng","Törpeharcsa"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        tvFishingTitle.setAdapter(adapter);
    }


    class RequestTask extends AsyncTask<Void, Void, Response> {
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
                        response = RequestHandler.get(requestUrl, null);
                        break;
                    case "POST":
                        response = RequestHandler.post(requestUrl, requestParams, sharedPreferences.getString("token",null));
                        break;
                    case "DELETE":
                        response = RequestHandler.delete(requestUrl, null);
                        break;
                }
            } catch (IOException e) {
                Toast.makeText(CreateFishing.this,
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
                Toast.makeText(CreateFishing.this,
                        "Hiba történt a létrehozáskor", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(CreateFishing.this, "Sikeresen létrehozva", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), CatchesFragment.class);
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