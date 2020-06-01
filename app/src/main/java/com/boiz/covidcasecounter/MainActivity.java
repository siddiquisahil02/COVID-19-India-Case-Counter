package com.boiz.covidcasecounter;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{

    private TextView confirmed, deceased, recovered;
    private TextView stateconfirmed, statedeceased, staterecovered;
    private TextView districtconfirmed, districtdeceased, districtrecovered;

    private Spinner stateSpinner, districtSpinner;

    private String sta, dis;

    private int overall = 0, deadth = 0, recover = 0;
    private int st, sd, sr;

    private ArrayList<String> states = new ArrayList<>();
    private ArrayList<String> disrtricts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUID();



        new DataBank().getData(new DataAsyncResponse() {
            @Override
            public void processFinished(final List<Data> dataArrayList) {
                //Log.d("INSIDE MAIN", "processFinished: " + dataArrayList);

                int total=0,dead=0,reco=0,dtotal=0,ddead=0,dreco=0;
                for (int i =0;i<dataArrayList.size();++i)
                {
                    for (int j =0 ; j<dataArrayList.get(i).getDistrictname().size(); ++j)
                    {
                        total = total + dataArrayList.get(i).getConfirmed().get(j);
                        dead = dead + dataArrayList.get(i).getDeath().get(j);
                        reco = reco + dataArrayList.get(i).getRecover().get(j);
                        dtotal = dtotal + dataArrayList.get(i).getDeltaconfirmed().get(j);
                        ddead = ddead + dataArrayList.get(i).getDeltadeath().get(j);
                        dreco = dreco + dataArrayList.get(i).getDeltarecover().get(j);
                    }
                }

                confirmed.setText("Total Confirmed Cases : " + total + "\t\t[+" + dtotal + "]");
                deceased.setText("Total Deceased : " + dead + "\t\t[+" + ddead + "]");
                recovered.setText("Total Recovered : " + reco + "\t\t[+" + dreco + "]");

                for (int i = 0; i<dataArrayList.size(); ++i)
                {
                    states.add(dataArrayList.get(i).getStatename());
                }
                //Log.d("STATES", "onCreate: " + states );
                states.add(0,"Select any State");
                ArrayAdapter<String> sta = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, states );
                sta.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                stateSpinner.setAdapter(sta);
                stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
                        ((TextView) parent.getChildAt(0)).setTextSize(20);
                        ((TextView) parent.getChildAt(0)).setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                        final String s = states.get(position);

                        for (int i=0;i<dataArrayList.size(); ++i)
                        {
                            if(dataArrayList.get(i).getStatename().equals(s))
                            {
                                disrtricts = dataArrayList.get(i).getDistrictname();
                            }
                        }
                        //disrtricts.add(0, "Select any District");
                        ArrayAdapter<String> dis = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, disrtricts);
                        dis.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        districtSpinner.setAdapter(dis);
                        districtSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
                                ((TextView) parent.getChildAt(0)).setTextSize(20);
                                ((TextView) parent.getChildAt(0)).setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                                String d = disrtricts.get(position);

                                Log.d("2nd Spinner", "onItemSelected : State -> " + s + " District -> " + d);
                                for (int i=0;i<dataArrayList.size();++i)
                                {
                                    if (dataArrayList.get(i).getStatename().equals(s) && dataArrayList.get(i).getDistrictname().contains(d))
                                    {
                                        int pos = dataArrayList.get(i).getDistrictname().indexOf(d);

                                        districtconfirmed.setText("Confirmed Cases : " + dataArrayList.get(i).getConfirmed().get(pos)
                                                                    + "\t\t\t[+" + dataArrayList.get(i).getDeltaconfirmed().get(pos) + "]");
                                        districtdeceased.setText("Deceased : " + dataArrayList.get(i).getDeath().get(pos)
                                                                    + "\t\t\t[+" + dataArrayList.get(i).getDeltadeath().get(pos) + "]");
                                        districtrecovered.setText("Recovered : " + dataArrayList.get(i).getRecover().get(pos).toString()
                                                                    + "\t\t\t[+" + dataArrayList.get(i).getDeltarecover().get(pos) + "]");

                                        int sc=0,sd=0,sr=0,sdc=0,sdd=0,sdr=0;
                                        for (int j=0;j<dataArrayList.get(i).getDistrictname().size();++j)
                                        {
                                            sc = sc + dataArrayList.get(i).getConfirmed().get(j);
                                            sd = sd + dataArrayList.get(i).getDeath().get(j);
                                            sr = sr + dataArrayList.get(i).getRecover().get(j);
                                            sdc = sdc + dataArrayList.get(i).getDeltaconfirmed().get(j);
                                            sdd = sdd + dataArrayList.get(i).getDeltadeath().get(j);
                                            sdr = sdr + dataArrayList.get(i).getDeltarecover().get(j);
                                        }

                                        stateconfirmed.setText("Confirmed Cases : " + sc + "\t\t\t[+" + sdc + "]");
                                        statedeceased.setText("Deceased : " + sd + "\t\t\t[+" + sdd + "]");
                                        staterecovered.setText("Recovered : " + sr + "\t\t\t[+" + sdr + "]");


                                    }
                                }

                                if (s.equals("Select any State"))
                                {
                                    ((TextView) parent.getChildAt(0)).setText("Select any City");
                                    resetTextView();
                                }

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });



            }
        });


    }

    private void resetTextView()
    {
        stateconfirmed.setText(R.string.total);
        statedeceased.setText(R.string.death);
        staterecovered.setText(R.string.recover);

        districtconfirmed.setText(R.string.total);
        districtdeceased.setText(R.string.death);
        districtrecovered.setText(R.string.recover);
    }

    private void setupUID() {

        confirmed = findViewById(R.id.tvConfirmed);
        deceased = findViewById(R.id.tvDeceased);
        recovered = findViewById(R.id.tvRecovered);

        stateconfirmed = findViewById(R.id.tvSconfirmed);
        statedeceased = findViewById(R.id.tvSdeceased);
        staterecovered = findViewById(R.id.tvSrecoved);

        districtconfirmed = findViewById(R.id.tvDconfirmed);
        districtdeceased = findViewById(R.id.tvDdeceased);
        districtrecovered = findViewById(R.id.tvDrecoved);

        stateSpinner = findViewById(R.id.spStates);
        districtSpinner = findViewById(R.id.spDistricts);
    }
}
