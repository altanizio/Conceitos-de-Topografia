package com.lag.altanizio.conceitosdetopografia;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class TextTabEstacBio extends Fragment {
    private TextView text;
    public ScrollView scroll;
    public ImageButton imgZ;
    public RelativeLayout layoutz;
    private Button button01;
    private Button button02;
    private Button button03;
    private Button button04;

    public TextTabEstacBio() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_text_tab_estac_bio, container, false);
        text = (TextView) view.findViewById(R.id.textTab);
        text.setText(R.string.estacao_bio);
        scroll = (ScrollView) view.findViewById(R.id.scrollV);
        layoutz = (RelativeLayout)view.findViewById(R.id.layoutZ);
        imgZ = (ImageButton) view.findViewById(R.id.imgZoom);
        button01 = (Button) view.findViewById(R.id.buttonRU);
        button02 = (Button) view.findViewById(R.id.buttonFO);
        button03 = (Button) view.findViewById(R.id.buttonNI);
        button04 = (Button) view.findViewById(R.id.buttonTRI);



        button01.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //((MainActivity)getActivity()).abrirPDF("Estação RUIDE","http://media.wix.com/ugd/052a5c_6d0c514253464f2bbaab6b0eb552764f.pdf");
                //((MainActivity)getActivity()).abrirPDFin("http://media.wix.com/ugd/052a5c_6d0c514253464f2bbaab6b0eb552764f.pdf");

                if(((MainActivity)getActivity()).verificaConexao()) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://media.wix.com/ugd/052a5c_6d0c514253464f2bbaab6b0eb552764f.pdf"));
                    startActivity(intent);
                }else {
                    Toast.makeText( getActivity().getApplicationContext(), "Deve estar conectado à internet para acessar este conteúdo", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button02.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

               // ((MainActivity)getActivity()).abrirPDF("Estação FOIF","http://media.wix.com/ugd/052a5c_f390d24d5f664d39af6126218af3197c.pdf");

                if(((MainActivity)getActivity()).verificaConexao()) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://media.wix.com/ugd/052a5c_f390d24d5f664d39af6126218af3197c.pdf"));
                    startActivity(intent);
                }else {
                    Toast.makeText( getActivity().getApplicationContext(), "Deve estar conectado à internet para acessar este conteúdo", Toast.LENGTH_SHORT).show();
                }
            }
        });
        button03.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

               // ((MainActivity)getActivity()).abrirPDF("Estação Nikon","http://media.wix.com/ugd/052a5c_c01c9add141d4381a72f62be5bfa1cbb.pdf");

                if(((MainActivity)getActivity()).verificaConexao()) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://media.wix.com/ugd/052a5c_c01c9add141d4381a72f62be5bfa1cbb.pdf"));
                    startActivity(intent);
                }else {
                    Toast.makeText( getActivity().getApplicationContext(), "Deve estar conectado à internet para acessar este conteúdo", Toast.LENGTH_SHORT).show();
                }

            }
        });
        button04.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //((MainActivity)getActivity()).abrirPDF("Estação TRIMBLE 3303","http://media.wix.com/ugd/052a5c_720c7a67da7d4d35a5415ee8cd8800b2.pdf");

                if(((MainActivity)getActivity()).verificaConexao()) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://media.wix.com/ugd/052a5c_720c7a67da7d4d35a5415ee8cd8800b2.pdf"));
                    startActivity(intent);
                }else {
                    Toast.makeText( getActivity().getApplicationContext(), "Deve estar conectado à internet para acessar este conteúdo", Toast.LENGTH_SHORT).show();
                }

            }
        });


        return view;

    }

}
