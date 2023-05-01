package hu.home.fishing.actvities.Main.Fishings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Date;

import hu.home.fishing.R;
import hu.home.fishing.actvities.Main.Fishings.Fishing;

public class FishingListAdpter extends ArrayAdapter<Fishing> {
    private static final String TAG = "FishingListAdapter";
    private Context fContext;
    int fResource;

    public FishingListAdpter(@NonNull Context context, int resource, ArrayList<Fishing> objects) {
        super(context, resource, objects);
        this.fContext = context;
        fResource = resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //passing the fishing Data
        String Title = getItem(position).getSpecies();
        String Place = getItem(position).getLocation();
        String Weight = getItem(position).getWeight();
        String Length = getItem(position).getLength();


        Fishing fishing = new Fishing(Title, Length, Weight, Place);
        LayoutInflater inflater = LayoutInflater.from(fContext);
        convertView = inflater.inflate(fResource, parent, false);

        TextView tvTitle = (TextView) convertView.findViewById(R.id.SpiecesCatchtv);
        TextView tvFishWeight = (TextView) convertView.findViewById(R.id.WeightCatchTW);
        TextView tvFishLength = (TextView) convertView.findViewById(R.id.LengthFishTv);
        TextView tvPlaceFish = (TextView) convertView.findViewById(R.id.PlaceFishTv);


        tvTitle.setText(Title);
        tvFishWeight.setText(Place);
        tvFishLength.setText((Weight));
        tvPlaceFish.setText(Length);


        return convertView;
    }
}
