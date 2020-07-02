package com.fiek.transfuzioni_gjakut.models;

import android.os.AsyncTask;

import com.fiek.transfuzioni_gjakut.activities.Coronavirus_Cases;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class fetchData extends AsyncTask<Void, Void, Void> {
    String data ="";
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

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        Coronavirus_Cases.data.setText(this.data);
    }
}
