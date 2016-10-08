package com.example.lee.sensortesting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.List;
import android.hardware.Sensor;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private List<Sensor> listOfDeviceSensors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CollectSensorMetrics sensorDataCollector = new CollectSensorMetrics(this);
        sensorDataCollector.startSensorManager();
        listOfDeviceSensors = sensorDataCollector.discoverDeviceSensors();

        //Check to see if the list of sensors could be found
        if(listOfDeviceSensors != null)
            Log.i("INFO ", "Listing device sensors: " + listOfDeviceSensors);

        sensorDataCollector.getSensorType();
        sensorDataCollector.checkSensorPowerConsumption();
    }
}
