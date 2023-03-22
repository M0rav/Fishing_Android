package hu.home.fishing;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CaughtFishesListAdapter extends ArrayAdapter<Catches> {
    private static  final   String  TAG = "CaughtFishesListAdapter";

    private Context fccontext;
    int fResource;

    public CaughtFishesListAdapter(@NonNull Context context, int resource, ArrayList<Catches> objects) {
        super(context, resource, objects);
        this.fccontext = context;
        fResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String fishSpecies = getItem(position).getFishSpices();
        Double fishWeight = getItem(position).getWeightOfCatch();
        Integer fishLength = getItem(position).getLenghtOfCatch();

        Catches catches = new Catches(fishSpecies,fishWeight,fishLength);

        LayoutInflater inflater = LayoutInflater.from(fccontext);
        convertView= inflater.inflate(fResource,parent,false);

        TextView tvFishSpecies = (TextView) convertView.findViewById(R.id.SpiecesCatchtv);
        TextView tvFishWeight = (TextView) convertView.findViewById(R.id.WeightCatchTW);
        TextView tvFishLength = (TextView) convertView.findViewById(R.id.LengthFishTv);
        tvFishSpecies.setText(fishSpecies);
        tvFishWeight.setText(String.valueOf(fishWeight));
        tvFishLength.setText(String.valueOf(fishLength));

        return convertView;
    }
}
