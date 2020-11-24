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
public class TextTabNivelMont extends Fragment {
    private TextView text;
    public ScrollView scroll;
    public ImageButton imgZ;
    public ImageButton img01;
    public ImageButton img02;
    public RelativeLayout layoutz;

    public TextTabNivelMont() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_text_tab_nivel_mont, container, false);

        text = (TextView) view.findViewById(R.id.textTab);
        text.setText(R.string.nivel_mont);
        scroll = (ScrollView) view.findViewById(R.id.scrollV);
        layoutz = (RelativeLayout)view.findViewById(R.id.layoutZ);
        imgZ = (ImageButton) view.findViewById(R.id.imgZoom);
        img01 = (ImageButton) view.findViewById(R.id.imgEst01);
        img02 = (ImageButton) view.findViewById(R.id.imgEst02);

        img01.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                imgZ.setVisibility(View.VISIBLE);
                imgZ.setImageDrawable(img01.getDrawable());

                layoutz.setVisibility(View.VISIBLE);
                scroll.setVisibility(View.INVISIBLE);

            }
        });

        img02.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                imgZ.setVisibility(View.VISIBLE);
                imgZ.setImageDrawable(img02.getDrawable());
                layoutz.setVisibility(View.VISIBLE);
                scroll.setVisibility(View.INVISIBLE);

            }
        });

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
