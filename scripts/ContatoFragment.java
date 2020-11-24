package com.lag.altanizio.conceitosdetopografia;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContatoFragment extends Fragment {

    private Button button01;
    private EditText text01;
    private EditText text02;
    private EditText text03;

    public ContatoFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_contato, container, false);
        text01 = (EditText) view.findViewById(R.id.editText01);
        text02 = (EditText) view.findViewById(R.id.editText02);
        text03 = (EditText) view.findViewById(R.id.editText03);



        button01 = (Button) view.findViewById(R.id.button01);
        button01.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                final Intent _Intent = new Intent(android.content.Intent.ACTION_SEND);
                _Intent.setType("message/html");
                _Intent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{ "monitoria.lag@gmail.com" });
               _Intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Marcar horário");
                _Intent.putExtra(android.content.Intent.EXTRA_TEXT, "Nome: "+text01.getText()+"\n\n"+"Turma: " +text03.getText()+"\n\n"+text02.getText());
                startActivity(Intent.createChooser(_Intent, "Mandar email pelo"));


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
