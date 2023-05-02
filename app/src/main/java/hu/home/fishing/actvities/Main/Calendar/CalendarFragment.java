package hu.home.fishing.actvities.Main.Calendar;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;


import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import hu.home.fishing.R;
import hu.home.fishing.actvities.Main.RequestHandler;
import hu.home.fishing.actvities.Main.Response;


public class CalendarFragment extends Fragment {
    private CalendarView simpleCalendarView;
    private MaterialButton btnPickStartDate, btnPickEndDate, btnCreateDate;
    private TextView tvStartDateShow, tvEndDateShow;
    private Date selectedStartDate,selectedEndDate;
    private EditText titleOfDate;
    private ArrayList<CalendarDates> datesFishesArrayList = new ArrayList<>();
    private String URLPOST = "http://10.0.2.2:3000/calendar/add";
    private String URL = "http://10.0.2.2:3000/calendar/info";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        init(view);
        Context context = requireActivity().getApplicationContext();
        SharedPreferences sharedPreferencese = context.getSharedPreferences("Adatok", Context.MODE_PRIVATE);
        Gson json = new Gson();
        CalendarFragment.RequestTask task = new CalendarFragment.RequestTask(URL,"GET",null);
        task.execute();
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
               if (start.isEmpty()) {
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

                CalendarDates calendar = new CalendarDates(title,start,end);
                Gson json = new Gson();
                RequestTask task = new RequestTask(URLPOST,"POST", json.toJson(calendar));
                task.execute();
                CalendarFragment.RequestTask tasks = new CalendarFragment.RequestTask(URL,"GET",null);
                tasks.execute();
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


    public class RequestTask extends AsyncTask<Void, Void, Response> {
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
                        response = RequestHandler.get(requestUrl,sharedPreferencese.getString("token",null));
                        break;
                    case "POST":
                        response = RequestHandler.post(requestUrl, requestParams,sharedPreferencese.getString("token",null));
                        break;
                    case "DELETE":
                        response = RequestHandler.delete(requestUrl,null);
                        break;
                }
            } catch (IOException e) {
                Toast.makeText(getActivity(),
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

            if (response.getResponseCode() >= 401) {
                Toast.makeText(getActivity(),
                        "Hiba történt!"+response.getResponseCode(), Toast.LENGTH_SHORT).show();

            } else {

            }
            switch (requestType) {
                case "GET":

                    Gson gson = new Gson();
                    String json = response.getContent();
                    Type fishingDateType = new TypeToken<List<CalendarDates>>() {}.getType();
                    List<CalendarDates> calendars = gson.fromJson(json, fishingDateType);
                    List<EventDay> events = new ArrayList<>();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                    for (CalendarDates calendar : calendars) {
                        try {
                            Date startDate = sdf.parse(calendar.getStart());
                            Date endDate = sdf.parse(calendar.getEnd());
                            String title = calendar.getTitle();
                            // Create a Calendar instance and set the date range for the event
                            Calendar startCalendar = Calendar.getInstance();
                            startCalendar.setTime(startDate);
                            Calendar endCalendar = Calendar.getInstance();
                            endCalendar.setTime(endDate);
/*
                            String mydate =  "2023-05-06";
                            Calendar mylittleCal = Calendar.getInstance();
                            mylittleCal.setTime(sdf.parse(mydate));
                            events.add(new EventDay(mylittleCal,R.color.purple_500));

                            String mydates =  "2023-05-07";
                            Calendar mylittleCala = Calendar.getInstance();
                            mylittleCal.setTime(sdf.parse(mydates));
                            events.add(new EventDay(mylittleCala,R.color.purple_500));
*/
                            // Set the event color to purple for all the days between the start and end date
                            while (!startCalendar.after(endCalendar)) {
                                Calendar tempCal = Calendar.getInstance();
                                tempCal.setTime(startCalendar.getTime());
                                events.add(new EventDay(tempCal, R.color.purple_500));
                                startCalendar.add(Calendar.SECOND, 86400);
                            }


                        } catch (ParseException e) {
                            // Handle any errors that occur while parsing the dates
                            e.printStackTrace();
                        }
                    }
                    simpleCalendarView.setEvents(events);



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


