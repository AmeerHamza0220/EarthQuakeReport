package com.example.lablnet.earthquakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by lablnet on 8/9/2017.
 */

public class CustomAdapter extends ArrayAdapter<EarthQuake>{

    public CustomAdapter(@NonNull Context context , @NonNull List<EarthQuake> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            LayoutInflater layout = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layout.inflate(R.layout.listview_io,null);                    }
        TextView txtPlace=(TextView)convertView.findViewById(R.id.txtPlace);
        TextView txtTime=(TextView)convertView.findViewById(R.id.txtTime);
        TextView txtDate=(TextView)convertView.findViewById(R.id.txtDate);
        EarthQuake earthQuake=getItem(position);
        Date date=new Date(earthQuake.getmTime());
        TextView t=(TextView)convertView.findViewById(R.id.circle) ;
        String magnitude=formatMagnitude(earthQuake.getmMagnitude());
        t.setText(magnitude);
        txtPlace.setText(earthQuake.getmPlace());
        String fomattedTime=formatTime(date);
        txtTime.setText(String.valueOf(fomattedTime));
        String formattedDate=formattedDate(date);
        txtDate.setText(String.valueOf(formattedDate));
        return convertView;
    }
    public String formatTime(Date d){
        SimpleDateFormat timeFormat=new SimpleDateFormat("h:mm a");
        String formatted=timeFormat.format(d);
        return formatted;
    }
    public String formattedDate(Date d){
        SimpleDateFormat timeFormat=new SimpleDateFormat("LLL dd yyy");
        String formatted=timeFormat.format(d);
        return formatted;
    }
    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

}

