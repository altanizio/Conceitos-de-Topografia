package com.lag.altanizio.conceitosdetopografia;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.icu.math.BigDecimal;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class BussolaFragm extends Fragment  implements SensorEventListener {

    private SensorManager bussoManager;
    private Sensor bussomSensor;
    private Sensor accelSensor;
    private ImageView bussola;
    private TextView bussolaText;
    private boolean accelerIsAtivat;
    private boolean magnetoIsAtivat;
    private float[] accelerVector = new float[3];
    private float[] magnetoVector = new float[3];
    private float[] rotation = new float[9];
    private float[] orientacao = new float[3];
    private float angulo = 0f;
    final int SENSOR_VALUE_ADAPTER = 50000;


    public BussolaFragm() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bussola, container, false);
        bussola = (ImageView) view.findViewById(R.id.bussolaImag);
        bussolaText = (TextView)  view.findViewById(R.id.bussolaText);
        bussoManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        bussomSensor = bussoManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        accelSensor = bussoManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        ativateSensors();
        return view;

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            System.arraycopy(event.values, 0, accelerVector, 0, event.values.length);
            accelerIsAtivat = true;
        } else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            System.arraycopy(event.values, 0, magnetoVector, 0, event.values.length);
            magnetoIsAtivat = true;
        } if (accelerIsAtivat && magnetoIsAtivat) {


            bussoManager.getRotationMatrix(rotation, null, accelerVector, magnetoVector);
            bussoManager.getOrientation(rotation, orientacao);

            float azimuteInRadianos = orientacao[0];

            float azimuteInGraus = (float)(Math.toDegrees(azimuteInRadianos)+360)%360;

            RotateAnimation animation = new RotateAnimation(
                angulo,
                -azimuteInGraus,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f);

            animation.setDuration(250);
            animation.setFillAfter(true);

            bussola.startAnimation(animation);
            angulo = -azimuteInGraus;
            angulo = angulo + 360;
            int grau = (int) angulo;
            int minuto= (int)(( angulo -(int) angulo)*60);
            double segundo=(double)((((angulo -(int)angulo)*60)-(int)(( angulo -(int) angulo)*60))*60);
            int seg = (int) segundo;
          //  BigDecimal bd = new BigDecimal(segundo).setScale(3, RoundingMode.HALF_EVEN);
            String ang = grau+"° "+ minuto+"' " + seg+"'' ";
            bussolaText.setText("Ângulo: "+ang);
        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onStop() {
        super.onStop();
        bussoManager.unregisterListener(this);
        Runtime.getRuntime().gc();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        bussoManager.unregisterListener(this);
        Runtime.getRuntime().gc();
    }

    @Override
    public void onResume() {
        ativateSensors();
        super.onResume();
    }

    @Override
    public void onStart() {
        ativateSensors();
        super.onStart();
    }

    private void ativateSensors(){
        bussoManager.registerListener(this, accelSensor, SENSOR_VALUE_ADAPTER);
        bussoManager.registerListener(this, bussomSensor, SENSOR_VALUE_ADAPTER);
    }



}
