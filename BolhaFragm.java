package com.lag.altanizio.conceitosdetopografia;


import android.app.Activity;


import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import com.lag.altanizio.conceitosdetopografia.MainActivity;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.allowClearUserData;
import static android.R.attr.gravity;


/**
 * A simple {@link Fragment} subclass.
 */
public class BolhaFragm extends Fragment implements SensorEventListener{

    private SensorManager mSensorManager;
    private Sensor mSensor;
    private TextView inclinacao;
    private TextView precisao;
    private ImageView bolha;
    private int x;
    private int y;
    private int aux=0;
    private double raio;
    private double t;
    private double precision =0;
    private double raioMax;
    private int prec=55;
    private int tamanho;
    private double rease;

    public BolhaFragm() {

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bolha, container, false);


        mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this,mSensor,mSensorManager.SENSOR_DELAY_GAME);//SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM

        inclinacao = (TextView) view.findViewById(R.id.inclinacao);
        inclinacao.setText("Teste bolha");

        precisao = (TextView) view.findViewById(R.id.precisao );
        precisao.setText("Aumentar precisão x"+0);


        bolha = (ImageView) view.findViewById(R.id.bolha);

        SeekBar seekBar = (SeekBar) view.findViewById(R.id.seekBar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChanged = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                progressChanged = progress;
                precision =  progressChanged *0.01;
                precisao.setText("Aumentar precisão x"+progress);

            }

            public void onStartTrackingTouch(SeekBar seekBar) {


            }

            public void onStopTrackingTouch(SeekBar seekBar) {


            }
        });


        //raioMax = 7.5*28/60;

        tamanho = ((MainActivity)getActivity()).tamanhoTela();
        rease = tamanho/720;
        return view;
    }


    @Override
    public void onSensorChanged(SensorEvent event) {

        if(aux ==0){
            x = bolha.getLeft();
            y=bolha.getTop();
            aux++;
        }
        raio= Math.pow(Math.pow(event.values[0],2)+Math.pow(event.values[1],2),0.5);
        t = Math.atan(event.values[1]/event.values[0]);
        double angulo = (t*180/Math.PI);
        int grau = (int) angulo;
        int minuto= (int)(( angulo -(int) angulo)*60);
        double segundo=(double)((((angulo -(int)angulo)*60)-(int)(( angulo -(int) angulo)*60))*60);
        int seg = (int) segundo;
        //  BigDecimal bd = new BigDecimal(segundo).setScale(3, RoundingMode.HALF_EVEN);
        String ang = grau+"° "+ minuto+"' " + seg+"'' ";

        inclinacao.setText("Horizontal: " + (int) (event.values[0]*100) + "\nVertical: " + (int) (event.values[1]*100) + "\nRaio: " + (int) (raio*100) + "\n");

        /*
        if(event.values[0]>4.3) {


            if(event.values[1]>4.3 ){
                bolha.setTop((int) (y - 4.3 * 30));
                bolha.setLeft((int) (x + 4.3 * 30));
            }else if(event.values[1]<-4.3){
                bolha.setTop((int) (y + 4.3 * 30));
                bolha.setLeft((int) (x + 4.3 * 30));
            }else{
                bolha.setLeft((int) (x + 4.3 * 30));
                bolha.setTop((int) (y - event.values[1] * 30));
            }




        }else if(event.values[0] <-4.3 ){
            bolha.setLeft((int) (x - 4.3 * 30));
          //  bolha.setTop((int) (y - event.values[1] * 30));
        }else if(event.values[1]>4.3 ){
            bolha.setTop((int) (y - 4.3 * 30));
           // bolha.setLeft((int) (x + event.values[0] * 30));
        }else if(event.values[1]<-4.3){
            bolha.setTop((int) (y + 4.3 * 30));
          //  bolha.setLeft((int) (x + event.values[0] * 30));
        }
        else{
            bolha.setLeft((int) (x + event.values[0] * 30));
            bolha.setTop((int) (y - event.values[1] * 30));

            Log.d("MainActivity", "Teste X: " + bolha.getLeft());
            Log.d("MainActivity", "Teste Y: " + bolha.getTop());
        }
        */
        raioMax =3.1* rease;


       // double raio2 = Math.pow(Math.pow((event.values[0]*5),2)+Math.pow((event.values[1]*5),2),0.5) ;
       // double raioMax2 = Math.pow(Math.pow(((raioMax*Math.cos(t)*prec )),2)+Math.pow((raioMax*Math.sin(t))*prec,2),0.5);

        if(raio+precision > raioMax && event.values[0]>0) {
            bolha.setTop((int) (y - (raioMax * Math.sin(t)) * prec));
            bolha.setLeft((int) (x + (raioMax * Math.cos(t) * prec)));

        }else if (raio+precision > raioMax && event.values[0]<0){
            bolha.setTop((int) (y + (raioMax*Math.sin(t)) *prec ));
            bolha.setLeft((int) (x - (raioMax*Math.cos(t)) *prec));
        } else if(raio+precision < raioMax && event.values[0]>0) {
                bolha.setLeft( (int) (x + ((raio+precision)*Math.cos(t))*(prec)));
                bolha.setTop( (int) (y - ((raio+precision)*Math.sin(t))*(prec)));

        }else{
            bolha.setLeft( (int) (x - ((raio+precision)*Math.cos(t))*(prec)));
            bolha.setTop( (int) (y + ((raio+precision)*Math.sin(t))*(prec)));

        }


    }



    private void setEixo(SensorEvent event){
        if(raio > raioMax && event.values[0]>0) {
            bolha.setTop((int) (y - (raioMax * Math.sin(t)) * prec));
            bolha.setLeft((int) (x + (raioMax * Math.cos(t) * prec)));

        }else if (raio > raioMax && event.values[0]<0){
            bolha.setTop((int) (y + (raioMax*Math.sin(t)) *prec ));
            bolha.setLeft((int) (x - (raioMax*Math.cos(t)) *prec));
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        //Log.d("BolhaFragm", "O evento onStop() ");
        mSensorManager.unregisterListener(this);
        Runtime.getRuntime().gc();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Runtime.getRuntime().gc();
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (getView() != null) {
                mSensorManager.registerListener(this,mSensor,20000);
            }
        }
    }

    @Override
    public void onResume() {
        mSensorManager.registerListener(this,mSensor,20000);

        super.onResume();
    }

    @Override
    public void onStart() {
        mSensorManager.registerListener(this,mSensor,20000);
        super.onStart();
    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }






}
