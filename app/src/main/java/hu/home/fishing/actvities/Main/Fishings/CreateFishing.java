package hu.home.fishing.actvities.Main.Fishings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.button.MaterialButton;

import hu.home.fishing.R;
import hu.home.fishing.actvities.Main.MainActivity;

public class CreateFishing extends AppCompatActivity {
    EditText etFishingTitle, etFishingPlace, etFishingDate;
    MaterialButton btnFishingCreate, btnFishingCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_fishing);
        init();
        btnFishingCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void init(){
        etFishingTitle = findViewById(R.id.editTextTitleOfFishing);
        etFishingPlace = findViewById(R.id.editTextPlaceOfTheFishing);
        etFishingDate = findViewById(R.id.editDateOfTheFishing);
        btnFishingCancel = findViewById(R.id.buttonFishingCancel);
        btnFishingCreate = findViewById(R.id.btnFishingCreate);

    }
}