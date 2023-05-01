package hu.home.fishing.actvities.Main.Calendar;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import hu.home.fishing.R;
import hu.home.fishing.actvities.Main.Fishings.CatchesFragment;
import hu.home.fishing.actvities.Main.Fishings.CreateFishing;
import hu.home.fishing.actvities.Main.RequestHandler;
import hu.home.fishing.actvities.Main.Response;
import hu.home.fishing.actvities.Register.SignUpActivity;
import hu.home.fishing.actvities.Register.Users;


public class CalendarFragment extends Fragment {
    private CalendarView simpleCalendarView;
    private MaterialButton btnPickStartDate, btnPickEndDate, btnCreateDate;
    private TextView tvStartDateShow, tvEndDateShow;
    private Date selectedStartDate,selectedEndDate;
    private EditText titleOfDate;
    private String URL = "http://10.0.2.2:3000/calendar/add";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        init(view);
        setCAlendar();
        simpleCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                // display the selected date by using a toast
                Toast.makeText(getContext(), dayOfMonth + "/" + month + "/" + year, Toast.LENGTH_LONG).show();
            }
        });

        btnPickStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final java.util.Calendar c = java.util.Calendar.getInstance();

                // on below line we are getting our day, month and year.
                int year = c.get(java.util.Calendar.YEAR);
                int month = c.get(java.util.Calendar.MONTH);
                int day = c.get(java.util.Calendar.DAY_OF_MONTH);

                // on below line we are creating a variable for date picker dialog.
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener(){
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                // on below line we are setting date to our text view.
                                tvStartDateShow.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                                java.util.Calendar selectedDate = Calendar.getInstance();
                                selectedDate.set(year, monthOfYear, dayOfMonth);
                                CalendarFragment.this.selectedStartDate = selectedDate.getTime();
                                selectedDate = Calendar.getInstance();
                                selectedStartDate = selectedDate.getTime();
                            }
                        },
                        // on below line we are passing year, month and day for selected date in our date picker.
                        year, month, day);
                // at last we are calling show to display our date picker dialog.
                datePickerDialog.show();
            }
        });

        btnPickEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final java.util.Calendar c = java.util.Calendar.getInstance();

                // on below line we are getting our day, month and year.
                int year = c.get(java.util.Calendar.YEAR);
                int month = c.get(java.util.Calendar.MONTH);
                int day = c.get(java.util.Calendar.DAY_OF_MONTH);

                // on below line we are creating a variable for date picker dialog.
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener(){
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                // on below line we are setting date to our text view.
                                tvEndDateShow.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                                java.util.Calendar selectedDate = Calendar.getInstance();
                                selectedDate.set(year, monthOfYear, dayOfMonth);
                                CalendarFragment.this.selectedEndDate = selectedDate.getTime();
                                selectedDate = Calendar.getInstance();


                            }
                        },
                        // on below line we are passing year, month and day for selected date in our date picker.
                        year, month, day);
                // at last we are calling show to display our date picker dialog.
                datePickerDialog.show();
            }
        });
        btnCreateDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pattern = "yyyy-MM-dd HH:mm:ss";
                DateFormat df = new SimpleDateFormat(pattern);
                String title = titleOfDate.getText().toString().trim();
                Date startDate = selectedStartDate;
                Date endDate = selectedEndDate ;
                String start = df.format(startDate);
                String end = df.format(endDate);

                if (title.isEmpty()) {
                    titleOfDate.setError("Nem lehet üres a email");
                    return;
                } else {
                    titleOfDate.setError(null);
                }
              /*  if (start.isEmpty()) {
                    titleOfDate.setError("Válassz kérlek kezdési dátumot");
                    return;
                } else {
                    titleOfDate.setError(null);
                }
                if (end.isEmpty()) {
                    titleOfDate.setError("Válassz kérlek végződési dátumot");
                    return;
                } else {
                    titleOfDate.setError(null);
                }
*/
                CalendarDates calendar = new CalendarDates(title,start,end);
                Gson json = new Gson();
                Toast.makeText(getActivity(), ""+calendar.getStart(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), ""+calendar.getEnd(), Toast.LENGTH_SHORT).show();
                RequestTask task = new RequestTask(URL,"POST", json.toJson(calendar));
                task.execute();
            }
        });


        // Inflate the layout for this fragment
       return view;
    }
    private void init(View view){
        simpleCalendarView = view.findViewById(R.id.simpleCalendarView);
        btnPickStartDate = view.findViewById(R.id.btmnFishingStartDate);
        btnPickEndDate = view.findViewById(R.id.btmnFishingEndDate);
        btnCreateDate = view.findViewById(R.id.btmnFishingDateCreate);
        tvStartDateShow = view.findViewById(R.id.tvStartDate);
        tvEndDateShow = view.findViewById(R.id.tvEndDate);
        titleOfDate = view.findViewById(R.id.editTextTitleOFFishing);

    }

    private void setCAlendar() {
        simpleCalendarView.setFirstDayOfWeek(2);
    }

    private class RequestTask extends AsyncTask<Void, Void, Response> {
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
                        response = RequestHandler.get(requestUrl, null);
                        break;
                    case "POST":
                        response = RequestHandler.post(requestUrl, requestParams, sharedPreferencese.getString("token",null));
                        break;
                    case "PUT":
                        response = RequestHandler.put(requestUrl, requestParams, null);
                        break;
                    case "DELETE":
                        response = RequestHandler.delete(requestUrl + "/" + requestParams, null);
                        break;
                }
            } catch (IOException e) {

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
                Toast.makeText(getActivity(), ""+response.getResponseCode(), Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(getActivity(), "Sikeresen létrehozva", Toast.LENGTH_SHORT).show();

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


