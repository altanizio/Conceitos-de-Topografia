package com.lag.altanizio.conceitosdetopografia;


import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class InicioFragm extends Fragment {

    private Button button01;
    private Button button02;
    private Button button03;
    private Button button04;
    private Button button05;
    private Button button06;
    private ImageView img;

    public InicioFragm() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_angulos, container, false);

        img= (ImageView) view.findViewById(R.id.imageView);

        button01 = (Button) view.findViewById(R.id.button01);
        button02 = (Button) view.findViewById(R.id.button02);
        button03 = (Button) view.findViewById(R.id.button03);
        button04 = (Button) view.findViewById(R.id.button04);
        button05 = (Button) view.findViewById(R.id.button05);
        button06 = (Button) view.findViewById(R.id.button06);

        if(Build.VERSION.SDK_INT <= 21) {

            img.setColorFilter(Color.WHITE);

            button03.setVisibility(View.INVISIBLE);
            button03.setEnabled(false);

            button04.setVisibility(View.INVISIBLE);
            button04.setEnabled(false);

            button01.setTextColor(Color.BLACK);
            button02.setTextColor(Color.BLACK);
            button05.setTextColor(Color.BLACK);
            button06.setTextColor(Color.BLACK);
        }


        button01.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ((MainActivity)getActivity()).estacao();
            }
        });

        button02.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ((MainActivity)getActivity()).nivel();

            }
        });
        button03.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ((MainActivity)getActivity()).bussola();
            }
        });
        button04.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ((MainActivity)getActivity()).bolha();
            }
        });
        button05.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ((MainActivity)getActivity()).horarios();
            }
        });
        button06.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ((MainActivity)getActivity()).pdfCard();
            }
        });








        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        Runtime.getRuntime().gc();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Runtime.getRuntime().gc();
    }

}
