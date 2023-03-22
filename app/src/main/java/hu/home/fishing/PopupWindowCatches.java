package hu.home.fishing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.google.android.material.button.MaterialButton;

public class PopupWindowCatches extends AppCompatActivity {
    //TODO NEW FISHES ADDDING
    private MaterialButton btnCancel, btnAddNewFishes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_window_catches);
        popUpWindowCatches();
        init();

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Intent intent = new Intent(getApplicationContext(), CatchesFragment.class);
                startActivity(intent);
            }
        });


    }

    private void init(){
        btnCancel = findViewById(R.id.btnCatchesCheck);
        btnAddNewFishes = findViewById(R.id.btnNewCatch);
    }

    private void popUpWindowCatches(){
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.7),(int)(height*.5));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);


    }
}