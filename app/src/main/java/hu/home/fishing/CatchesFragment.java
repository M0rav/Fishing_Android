package hu.home.fishing;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class CatchesFragment extends Fragment {
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private EditText newcatchpopup_title,  newcatchpopup_weight, newcatchpopup_length, newcatchpopup_location;
    private MaterialButton btnFishAdd, btnFishDel;
    private ListView FishDisplayList;
    private String URLGETFISHES = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_catches, container, false);
        init(view);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.caughtfish_item_layout, R.id.fishspeciesTextView, getData());
        FishDisplayList.setAdapter(adapter);
        return  view;
    }

    private void init(View view) {
        FishDisplayList = view.findViewById(R.id.caughtFishListView);
    }

    private List<String> getData() {
        List<String> data = new ArrayList<>();
        data.add("Row 1, line 1\nRow 1, line 2\nRow 1, line 3");
        data.add("Row 2, line 1\nRow 2, line 2");
        data.add("Row 3, line 1\nRow 3, line 2\nRow 3, line 3\nRow 3, line 4");
        return data;
    }


}