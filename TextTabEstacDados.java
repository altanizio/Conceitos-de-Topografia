package com.lag.altanizio.conceitosdetopografia;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TextTabEstacDados extends Fragment {
    private TextView text;
    public ScrollView scroll;
    public ImageButton imgZ;
    public RelativeLayout layoutz;

    public TextTabEstacDados() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_text_tab_estac_dados, container, false);
        text = (TextView) view.findViewById(R.id.textTab);
        text.setText(R.string.estacao_salvardados);
        scroll = (ScrollView) view.findViewById(R.id.scrollV);
        layoutz = (RelativeLayout)view.findViewById(R.id.layoutZ);
        imgZ = (ImageButton) view.findViewById(R.id.imgZoom);


        imgZ.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                imgZ.setVisibility(View.INVISIBLE);
                layoutz.setVisibility(View.INVISIBLE);
                scroll.setVisibility(View.VISIBLE);

            }
        });
        return view;

    }

}
