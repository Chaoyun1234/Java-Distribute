package com.example.v_chzha4.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.microsoft.azure.mobile.CustomProperties;
import com.microsoft.azure.mobile.MobileCenter;
import com.microsoft.azure.mobile.analytics.Analytics;
import com.microsoft.azure.mobile.crashes.Crashes;
import com.microsoft.azure.mobile.distribute.Distribute;
import com.microsoft.azure.mobile.push.Push;

public class MainActivity extends AppCompatActivity {
//public class MainActivity extends Application {

    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Crashes.generateTestCrash();
            }
        });

        Push.setListener(new MyPushListener());
        Distribute.setListener(new MyDistributeListener());
        //MobileCenter.setLogUrl("https://in-staging-south-centralus.staging.avalanch.es");

        MobileCenter.start(getApplication(), "6a00e122-d020-4d5c-b6d3-8a2eb7227954",
                Analytics.class, Crashes.class, Distribute.class, Push.class);
        Log.e("installID", "" + MobileCenter.getInstallId().get());
//        MobileCenter.start(this, "b4be3d4e-e63f-40e6-a04a-459894f938ab",
//                Analytics.class, Crashes.class);
        //Crashes.generateTestCrash();
        CustomProperties properties = new CustomProperties();
        properties.set("color", "blue").set("score", 10).clear("score");
        MobileCenter.setCustomProperties(properties);

    }
}
