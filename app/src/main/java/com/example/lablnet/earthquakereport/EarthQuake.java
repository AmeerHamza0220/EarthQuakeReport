package com.example.lablnet.earthquakereport;

/**
 * Created by lablnet on 8/9/2017.
 */

public class EarthQuake  {
    private Double mMagnitude;
    private String mPlace;
    private Long mTime;
    private String mTitle;
    public EarthQuake(Double mMagnitude, String mPlace, Long mTime, String mTitle) {
        this.mMagnitude = mMagnitude;
        this.mPlace = mPlace;
        this.mTime = mTime;
        this.mTitle = mTitle;
    }

    public Double getmMagnitude() {
        return mMagnitude;
    }

    public String getmPlace() {
        return mPlace;
    }

    public Long getmTime() {
        return mTime;
    }

    public String getmTitle() {
        return mTitle;
    }
}
