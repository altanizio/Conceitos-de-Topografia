package com.lag.altanizio.conceitosdetopografia;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class SobreFragm extends Fragment {

    private Button button01;
    private Button button02;
    private TextView textVersion;
    public SobreFragm() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sobre, container, false);

        textVersion = (TextView) view.findViewById(R.id.textView);
        textVersion.setText(((MainActivity)getActivity()).getVersion());

        button01 = (Button) view.findViewById(R.id.button01);
        button01.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.det.ufc.br/lag"));
                startActivity(intent);

              //  ((MainActivity)getActivity()).openWeb("Site LAG","https://www.det.ufc.br/lag");


            }
        });

        button02 = (Button) view.findViewById(R.id.button02);
        button02.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
             //   Toast.makeText(getContext().getApplicationContext(), "Em construção.", Toast.LENGTH_LONG).show();
                ((MainActivity)getActivity()).openWeb("Horário monitores",
                      "https://static.wixstatic.com/media/052a5c_dfd1758456a74412a2691c031e2fbc20~mv2.png/v1/fill/w_594,h_607,al_c,usm_0.66_1.00_0.01/052a5c_dfd1758456a74412a2691c031e2fbc20~mv2.png");

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
