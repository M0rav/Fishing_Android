package hu.home.fishing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

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
        String titleOfFishing = getItem(position).getTitle();
        String startOfFishing = getItem(position).getStartOfFishing();
        Integer sumOfFishes = getItem(position).getPiecesOfFishes();
        Double weightOfTheFishes = getItem(position).getWeightOfTheFishes();
        String placeOfTheFishing = getItem(position).getPlaceOfTheFishing();

        Fishing fishing = new Fishing(titleOfFishing, startOfFishing, sumOfFishes, weightOfTheFishes, placeOfTheFishing);
        LayoutInflater inflater = LayoutInflater.from(fContext);
        convertView = inflater.inflate(fResource, parent, false);

        TextView tvTitle = (TextView) convertView.findViewById(R.id.TitleFishingTextView);
        TextView tvFishingStart = (TextView) convertView.findViewById(R.id.DateFishingTW);
        TextView tvSumFish = (TextView) convertView.findViewById(R.id.FishingSumWeightTV);
        TextView tvWeightFish = (TextView) convertView.findViewById(R.id.FishingPiecesTextView);
        TextView tvPlaceFish = (TextView) convertView.findViewById(R.id.PlaceFishingTextView);

        tvTitle.setText(titleOfFishing);
        tvFishingStart.setText(startOfFishing);
        tvSumFish.setText(String.valueOf("DB: " + sumOfFishes));
        tvWeightFish.setText(String.valueOf(weightOfTheFishes + " +kg"));
        tvPlaceFish.setText(placeOfTheFishing);

        return convertView;
    }
}
