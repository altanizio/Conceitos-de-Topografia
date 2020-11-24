package com.lag.altanizio.conceitosdetopografia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class LaucherActivite extends Activity {

    private  static int SPLASH_TIME_OUT = 500;
    private ImageView imag;
    private TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.hold, R.anim.fade_in);
        setContentView(R.layout.activity_laucher_activite);
        imag = (ImageView) findViewById(R.id.imageLauncher);

        imag.setImageResource(R.drawable.ic_lag3);

      //  RotateAnimation rotate = new RotateAnimation(0, 360,
       //        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
       //        0.5f);
       // rotate.setDuration(500);
      //  rotate.setRepeatCount(Animation.INFINITE);
        //imag.setAnimation(rotate);

        //Animation a = AnimationUtils.loadAnimation(LaucherActivite.this, R.anim.fade_out);
       // a.reset();
        text = (TextView) findViewById(R.id.textIni);
       // text.clearAnimation();
       // text.startAnimation(a);

       // imag.clearAnimation();
        //imag.startAnimation(a);

       new Handler().postDelayed(new Runnable() {

           @Override
           public void run() {


               Intent i = new Intent(LaucherActivite.this,
                       MainActivity.class);

               startActivity(i);
               finish();



              // overridePendingTransition(R.anim.hold, R.anim.fade_out);
           }
       },SPLASH_TIME_OUT);




    }

}
