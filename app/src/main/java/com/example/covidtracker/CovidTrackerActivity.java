package com.example.covidtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;
import org.w3c.dom.Text;

public class CovidTrackerActivity extends AppCompatActivity {
    TextView totalCases,activeCases,totalRec,todayRec,totalDeaths,todayDeaths;
    EditText countryName;
    ImageView searchIcon;
    String COUNTRY;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_tracker);

        totalCases=(TextView) findViewById(R.id.total_cases);
        activeCases=(TextView) findViewById(R.id.active_cases);
        totalRec=(TextView) findViewById(R.id.total_rec);
        todayRec=(TextView) findViewById(R.id.today_rec);
        totalDeaths=(TextView) findViewById(R.id.total_dec);
        todayDeaths=(TextView) findViewById(R.id.today_deaths);

        countryName=(EditText) findViewById(R.id.Your_city);
        searchIcon=(ImageView) findViewById(R.id.search);

        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                COUNTRY=countryName.getText().toString();
                String url ="https://corona.lmao.ninja/v2/countries/"+COUNTRY+"?yesterday&strict&query%20";
                getApiData(url);

            }
        });





    }

    public void getApiData(String url){
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    //do something with response
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        totalCases.setText(jsonObject.getString("cases"));
                        activeCases.setText(jsonObject.getString("active"));
                        totalRec.setText(jsonObject.getString("recovered"));
                        todayRec.setText(jsonObject.getString("todayRecovered"));
                        totalDeaths.setText(jsonObject.getString("deaths"));
                        todayDeaths.setText(jsonObject.getString("todayDeaths"));
                    }catch (Exception e){

                    }

                }, error -> Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show());

        // Add the request to the RequestQueue.
        queue.add(stringRequest);


    }
}