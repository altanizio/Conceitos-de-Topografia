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
public class TextTabNivelBio extends Fragment {
    private TextView text;
    public ScrollView scroll;
    public ImageButton imgZ;
    public RelativeLayout layoutz;
    private Button button01;


    public TextTabNivelBio() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_text_tab_nivel_bio, container, false);
        text = (TextView) view.findViewById(R.id.textTab);
        text.setText(R.string.nivel_bio);
        scroll = (ScrollView) view.findViewById(R.id.scrollV);
        layoutz = (RelativeLayout)view.findViewById(R.id.layoutZ);
        imgZ = (ImageButton) view.findViewById(R.id.imgZoom);
        button01 = (Button) view.findViewById(R.id.buttonNI);




        button01.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(((MainActivity)getActivity()).verificaConexao()) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://media.wix.com/ugd/052a5c_4934c3f1da174654900425fbc29c7be9.pdf"));
                    startActivity(intent);
                }else {
                    Toast.makeText( getActivity().getApplicationContext(), "Deve estar conectado à internet para acessar este conteúdo", Toast.LENGTH_SHORT).show();
                }
            }
        });




        return view;

    }

}
