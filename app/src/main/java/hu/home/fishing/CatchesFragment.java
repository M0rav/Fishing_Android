package hu.home.fishing;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class CatchesFragment extends Fragment {

    private ListView CatchesListView;
    private ArrayList<Fishing> fishinglist = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_catches, container, false);
        init(view);
        FishingListAdpter adapter = new FishingListAdpter(getContext(), R.layout.catches_adapter_view,fishinglist);
        CatchesListView.setAdapter(adapter);

        //ON Selected iTem change to a new webpage where user can add fishes there
        CatchesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });



        // TESTIN THE LISTVIEW
        Fishing first = new Fishing("Kedven","2002-012",12, 12.0,"Budapest");
        Fishing second = new Fishing("BESZARAS","20021-12",12, 12.0,"Kekemet");
        Fishing secondu = new Fishing("Szarosz","2002--12",12, 12.0,"Balatonomok");
        fishinglist.add(first);
        fishinglist.add(second);
        fishinglist.add(secondu);
        return  view;
    }


    private void init(View view) {
        CatchesListView = view.findViewById(R.id.caughtFishListView);
    }




}