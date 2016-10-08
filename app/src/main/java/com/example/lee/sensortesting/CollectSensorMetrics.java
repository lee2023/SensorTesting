package com.example.lee.sensortesting;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;

import java.util.List;

/**
 * Created by rosemaryespinal on 10/7/16.
 *
 * The purpose of this class is to serve as a device sensor data collector.
 *
 */

public class CollectSensorMetrics {
    private Context context;
    private String sensorName;
    private int sensorType;
    private List<Sensor> deviceSensors;
    private float sensorStartTime;
    private float sensorEndTime;
    private int numberOfActiveSensors;
    private float powerConsumption;
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private int sensorReportingMode;

    public CollectSensorMetrics(Context context){
        this.context = context;
    }


    public String getSensorName(){
        return sensorName;
    }

    public void getSensorType() {
        for(int x=0; x<deviceSensors.size(); x++){
            sensorType = deviceSensors.get(x).getType();
            Log.i("INFO ", "Sensor " + deviceSensors.get(x).getName() +  " is of type: " + sensorType);
        }
    }

    public void setSensorType(int sensorType) {
        this.sensorType = sensorType;
    }

    //Method to start the sensor management service
    public void startSensorManager(){
        Log.i("INFO ", "Starting Sensor Manager...");
        mSensorManager = (SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
        if(mSensorManager == null)
            Log.e("ERROR: ","Failed to start the Sensor Manager.");
    }

    //method to start the Sensor management service
    public boolean isSensorManagerStarted(){
        if(mSensorManager != null){
            return true;
        }
        return false;
    }

    //Method to discover all types of sensors that a device has
    public List<Sensor> discoverDeviceSensors(){
        if(isSensorManagerStarted()){
            Log.i("INFO ", "Discovering all device sensors...");
            deviceSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        }else{
            Log.d("DEBUG ", "The Sensor Manager has not been started! Starting the Sensor Manager...");
            startSensorManager();
        }
        return deviceSensors;
    }

    //Method to check and see if a sensor is activated or not
    public boolean isSensorActivated(){
        return false;
    }

    //Method to check and see if there are multiple instances of the sensor being activated
    public boolean checkForMultipleActiveSensors(){
        if(isSensorActivated()){
            Log.i("INFO ", "Sensor activity detected.");
            return true;
        }
        return false;
    }


    //Method to query a sensor's power consumption
    public void checkSensorPowerConsumption(){
        //add code to do the check
        if(deviceSensors == null)
            return;

        for(int i=0; i<deviceSensors.size(); i++){
            //powerConsumption = mSensor.getPower();
            powerConsumption = deviceSensors.get(i).getPower();
            Log.i("INFO ", "Power consumed by the sensor " + deviceSensors.get(i).getName() + " is: " + powerConsumption + " mA");
        }
    }

    //Method to query sensor reporting mode
    public void querySensorReportingMode(){
        for(int i=0; i<deviceSensors.size(); i++){
            sensorReportingMode = deviceSensors.get(i).getReportingMode();
            Log.i("INFO ", "Sensor " + deviceSensors.get(i).getName() + " reporting mode is: " + sensorReportingMode);
        }
    }

    //Method to query sensor start time and end time
}
