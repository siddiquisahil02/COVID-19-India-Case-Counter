package com.boiz.covidcasecounter;

import android.app.Application;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DataBank
{
    ArrayList<Data> datalist = new ArrayList<>();
    private String url = "https://api.covid19india.org/v2/state_district_wise.json";

    public List<Data> getData(final DataAsyncResponse callback)
    {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET
                , url, (JSONArray) null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response)
            {
                if (response != null)
                {
                    for (int i =0; i<response.length();++i)
                    {
                        try {
                            Data data = new Data();
                            JSONObject jsonObject = response.getJSONObject(i);
                            data.setStatename(jsonObject.getString("state"));

                            JSONArray jsonArray = jsonObject.getJSONArray("districtData");
                            ArrayList<String> dis = new ArrayList<String>();
                            ArrayList<Integer> con = new ArrayList<Integer>();
                            ArrayList<Integer> dec = new ArrayList<Integer>();
                            ArrayList<Integer> rec = new ArrayList<Integer>();
                            ArrayList<Integer> nwcon = new ArrayList<Integer>();
                            ArrayList<Integer> nwdec = new ArrayList<Integer>();
                            ArrayList<Integer> nwrec = new ArrayList<Integer>();
                            for (int j=0;j<jsonArray.length();++j)
                            {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(j);
                                dis.add(jsonObject1.getString("district"));
                                con.add(jsonObject1.getInt("confirmed"));
                                dec.add(jsonObject1.getInt("deceased"));
                                rec.add(jsonObject1.getInt("recovered"));

                                JSONObject jsonObject2 = jsonObject1.getJSONObject("delta");
                                //Log.d("DELTA", "onResponse: " + jsonObject2);
                                nwcon.add(jsonObject2.getInt("confirmed"));
                                nwdec.add(jsonObject2.getInt("deceased"));
                                nwrec.add(jsonObject2.getInt("recovered"));
                            }
                            data.setDistrictname(dis);
                            data.setConfirmed(con);
                            data.setDeath(dec);
                            data.setRecover(rec);

                            data.setDeltaconfirmed(nwcon);
                            data.setDeltadeath(nwdec);
                            data.setDeltarecover(nwrec);

                            datalist.add(data);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    Log.d("DATALIST", "onResponse: DATALIST -> " + datalist);

                    if (null != callback)
                    {
                        callback.processFinished(datalist);
                    }
                }
                else
                {
                    Log.d("EMPTY", "onResponse: Resonse is null");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        AppController.getInstance().addToRequestQueue(jsonArrayRequest);

        return datalist;
    }
}
