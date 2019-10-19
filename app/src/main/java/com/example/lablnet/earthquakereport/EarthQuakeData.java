package com.example.lablnet.earthquakereport;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;

/**
 * Created by lablnet on 8/9/2017.
 */

public class EarthQuakeData  {
    private static MainActivity mainActivity;
    private static String mResult;
    public EarthQuakeData() {
        mainActivity.getCallingActivity();
        ProgressBar p=new ProgressBar(mainActivity);

    }
    public static ArrayList<EarthQuake> earthQuake(final Context context) throws ExecutionException, InterruptedException {
        ProgressDialog pd=new ProgressDialog(context);
        pd.show();
        final ArrayList<EarthQuake> earthquakeList=new ArrayList<>();
        String baseUrl="https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson";
        Calendar c= Calendar.getInstance();
        int date=c.get(Calendar.DATE);
        SimpleDateFormat format=new SimpleDateFormat("MM");
        Date d=new Date();

        String month=format.format(d);
        int previous_date=date-1;
        SharedPreferences pref= PreferenceManager.getDefaultSharedPreferences(context);
        String  mMagnitude=pref.getString(context.getString(R.string.magnitudeKey),context.getString(R.string.pref_default_display_name));
        URL url=buildURL(baseUrl,"2017-"+month+"-"+previous_date,"2017-"+month+"-"+date,mMagnitude);

       String result= new getDataInBackground().execute(url).get();
                        try {
                            if(result==null){
                                Toast.makeText(context, "Sorry no data", Toast.LENGTH_SHORT).show();
                            }
                            pd.hide();
                            JSONObject jsonObject=new JSONObject(result);
                            JSONArray featureArr=jsonObject.getJSONArray("features");
                            for(int i=0;i<featureArr.length();i++){
                                JSONObject jsonArrayObject=featureArr.getJSONObject(i);
                                JSONObject properties=jsonArrayObject.getJSONObject("properties");
                                Double magnitude=properties.getDouble("mag");
                                String place=properties.getString("place");
                                Long time=properties.getLong("time");
                               String title=properties.getString("title");
                                EarthQuake mEarthQuake=new EarthQuake(magnitude,place,time,title);
                                earthquakeList.add(mEarthQuake);
                            }
                        } catch (JSONException e1) {
                            Log.e("EarthQuakeDaata","Probelm poarsing JSON",e1);
                            e1.printStackTrace();
                        }

        return earthquakeList;
                    }

    public static URL buildURL(String mUrl, String startDate, String endDate, String mMagnitude){
                        Uri buildurl=Uri.parse(mUrl).buildUpon()
                                .appendQueryParameter("starttime",startDate)
                                .appendQueryParameter("endtime",endDate)
                                .appendQueryParameter("minmagnitude", mMagnitude)
                                .build();
                        URL url=null;
                        try {
                            url=new URL(buildurl.toString());
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                        return url;
                    }
    public static String fetchFromUrl(URL url) throws IOException {
                        String result;
                        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                        try {
                            InputStream in = null;
                            try {
                                in = new BufferedInputStream(urlConnection.getInputStream());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            result=readStream(in);
                        } finally {
                            urlConnection.disconnect();
                        }
                        return result;

    }
    public static String readStream(InputStream inputStream) throws IOException {
                        StringBuilder output=new StringBuilder();
                        if(inputStream!=null){
                            InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
                            BufferedReader reader=new BufferedReader(inputStreamReader);
                            String line=reader.readLine();
                            while (line!=null){
                                output.append(line);
                                line=reader.readLine();
                            }

                        }
                        return output.toString();
                    }
    public static class getDataInBackground extends AsyncTask<URL,String,String>{

        @Override
        protected String doInBackground(URL... params) {
            URL url=params[0];
            String result=null;
            try {
                result=fetchFromUrl(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

    }
    }



