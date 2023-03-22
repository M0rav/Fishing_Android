package hu.home.fishing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class CatchesAdding extends AppCompatActivity {
    private MaterialButton btnBack, btnAddFish;
    private ArrayList<Catches> caughtFishesArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catches_adding);
        ListView catchesListView = findViewById(R.id.caughtFishesListView);

        Catches proba = new Catches("Ponty",23.2,122);
        Catches proba2 = new Catches("Csuka",23.2,122);
        Catches proba3 = new Catches("Ponty",23.2,122);
        caughtFishesArrayList.add(proba);
        caughtFishesArrayList.add(proba2);
        caughtFishesArrayList.add(proba3);

        CaughtFishesListAdapter adapter = new CaughtFishesListAdapter(this, R.layout.activity_fishing_catches_list_adapter,caughtFishesArrayList);
        catchesListView.setAdapter(adapter);


    }

    private void init(){
        btnBack = findViewById(R.id.btnCatchesCancel);
    }
}