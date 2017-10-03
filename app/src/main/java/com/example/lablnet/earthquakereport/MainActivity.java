package com.example.lablnet.earthquakereport;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    int start_date, end_date, mimMagnitude;
    int[] magnitude = new int[10];
    String[] place = new String[10];
    Long[] time = new Long[10];
    String[] title = new String[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.mipmap.ic_launcher);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setTitle("Recent Earthquake");
        getListData();
      //  sendNotification();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.setting:
                Intent i = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(i);
        }
        return super.onOptionsItemSelected(item);

}
public void getListData(){
    ListView listView = (ListView) findViewById(R.id.listView);
    ArrayList<EarthQuake> arrayList = null;
    try {
        arrayList = EarthQuakeData.earthQuake(this);
    } catch (ExecutionException e) {
        e.printStackTrace();
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    CustomAdapter adapter = new CustomAdapter(this, arrayList);
    listView.setAdapter(adapter);
}

    @Override
    protected void onStart() {
        super.onStart();
        getListData();
    }
    public void sendNotification(){
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("My notification");
        String text="This is a Notification Demo";
        builder.setContentText(text);
        Notification notification=builder.build();
        NotificationManagerCompat.from(this).notify(0,notification);
    }
}