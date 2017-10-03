package com.example.lablnet.earthquakereport;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by lablnet on 8/12/2017.
 */

public class customCircle extends AppCompatTextView{

    public customCircle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

@Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p=new Paint();
        Paint d=new Paint();
        d.setColor(Color.BLACK);
        d.setTextSize(30);
        d.setTypeface(Typeface.DEFAULT_BOLD);
        p.setStyle(Paint.Style.FILL);
    p.setColor(getMagnitudeColor(getText().toString()));

    canvas.drawText(getText().toString(),27,80,d);canvas.drawCircle(47,70,46,p);


    }
    private int getMagnitudeColor(String Mymagnitude) {
        int magnitudeColorResourceId;
        double magnitude= Double.parseDouble(String.valueOf(Mymagnitude));
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }

        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}
