package com.fiek.transfuzioni_gjakut.activities;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class fetchData extends AsyncTask<Void, Void, Void> {
    String data ="";
    String dataParsed = "";
    String singleParsed = "";
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("https://api.covid19api.com/summary");

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while(line != null) {
                line = bufferedReader.readLine();
                data = data + line;
            }

//            JSONArray JA = new JSONArray(data);
//            for(int i =0; i < JA.length() ; i++) {
//                JSONObject JO = (JSONObject) JA.get(i);
//                singleParsed = (String) JO.get("Global");
//
//            }

            JSONObject jObj = new JSONObject(data);
            //JSONArray jsonArry = jObj.getJSONArray("Global");


            JSONObject Obj = (JSONObject) jObj.get("Global");
            String TotalConfirmed = Obj.getString("TotalConfirmed");
            String TotalDeaths = Obj.getString("TotalDeaths");
            String TotalRecovered = Obj.getString("TotalRecovered");
//            String obj = jObj.getString("Global");

//                JSONObject obj = jsonArry.getJSONObject(i);
//                user.put("name",obj.getString("name"));
//                user.put("designation",obj.getString("designation"));
//                user.put("location",obj.getString("location"));
//                userList.add(user);

  singleParsed =  "Total Confirmed : " +TotalConfirmed + "\n\n" +
                    "Total Deaths : " +TotalDeaths+ "\n\n" +
                    "Total Recovered : " + TotalRecovered ;



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        Coronavirus_Cases.data.setText(this.singleParsed);
    }
}
