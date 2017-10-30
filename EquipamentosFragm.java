package com.lag.altanizio.conceitosdetopografia;


import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.net.URISyntaxException;

import static android.R.attr.description;


/**
 * A simple {@link Fragment} subclass.
 */
public class EquipamentosFragm extends Fragment {

    private Button myButton;
    private Button myButton2;
    private WebView myWebView;
    private ImageView imageL;
    private RotateAnimation rotate;


    public EquipamentosFragm() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_equipamentos, container, false);

        myButton = (Button) view.findViewById(R.id.button);
        myButton2 = (Button) view.findViewById(R.id.button2);
        myButton2.setVisibility(View.INVISIBLE);
        myButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage(R.string.ajuda_marcar)
                        .setPositiveButton("Ok entendi", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        }).show();
            }
        });


        myButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                ((MainActivity)getActivity()).openContato();


            }
        });




        imageL = (ImageView) view.findViewById(R.id.imageLoad);

       rotate = new RotateAnimation(0, 360,
               Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
               0.5f);
        rotate.setDuration(1000);
        rotate.setRepeatCount(Animation.INFINITE);
        imageL.setAnimation(rotate);
        imageL.startAnimation(rotate);






        myWebView = (WebView) view.findViewById(R.id.webview);

        myWebView.getSettings().setJavaScriptEnabled(true);
        //String url= "ttps://docs.google.com/spreadsheets/d/12iowqE2agpU-7eKUXN61YmUfJ8v_iJggKR_iOvd9VZQ/edit?usp=sharing";
       // myWebView.loadUrl("http://drive.google.com/viewerng/viewer?embedded=true&url=https://docs.google.com/spreadsheets/d/12iowqE2agpU-7eKUXN61YmUfJ8v_iJggKR_iOvd9VZQ/edit?usp=sharing");
        myWebView.loadUrl("https://docs.google.com/spreadsheets/d/1Ippm_FBwZ2PqVjRI95doCy3rPOi13je31EZNWzwZZIg/edit?usp=sharing");
        myWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished (WebView view, String url){
                super.onPageFinished(view, url);
                myWebView.setVisibility(View.VISIBLE);
                myButton.setText("Ajuda");
                myButton2.setVisibility(View.VISIBLE);
                imageL.clearAnimation();
                imageL.setVisibility(View.INVISIBLE);
                imageL.setEnabled(false);
            }

            @Override
            public void onPageStarted (WebView view, String url, Bitmap favicon){
                super.onPageStarted(view, url, favicon);
                myWebView.setVisibility(View.INVISIBLE);
                myButton.setText("Carregando... Por favor aguarde.");
                myButton2.setVisibility(View.INVISIBLE);
               // Toast.makeText( getActivity().getApplicationContext(), "Carregando...\nPor favor aguarde.", Toast.LENGTH_SHORT).show();


            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String url) {


                if (url.startsWith("http")) return false;//open web links as usual
                //try to find browse activity to handle uri
                Uri parsedUri = Uri.parse(url);
                PackageManager packageManager = getActivity().getPackageManager();
                Intent browseIntent = new Intent(Intent.ACTION_VIEW).setData(parsedUri);
                if (browseIntent.resolveActivity(packageManager) != null) {
                    getActivity().startActivity(browseIntent);
                    return true;
                }
                //if not activity found, try to parse intent://
                if (url.startsWith("intent:")) {
                    try {
                        Intent intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
                        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                            getActivity().startActivity(intent);
                            return true;
                        }
                        //try to find fallback url
                    //    String fallbackUrl = intent.getStringExtra("browser_fallback_url");
                    //   if (fallbackUrl != null) {
                    //        webView.loadUrl(fallbackUrl);
                    //        return true;
                    //}
                        //invite to install
                        Intent marketIntent = new Intent(Intent.ACTION_VIEW).setData(
                                Uri.parse("market://details?id=" + intent.getPackage()));
                        if (marketIntent.resolveActivity(packageManager) != null) {
                            getActivity().startActivity(marketIntent);
                            return true;
                        }else{
                            Toast.makeText( getActivity().getApplicationContext(), "Por favor verificar se seu aparelho possui google play instalado", Toast.LENGTH_SHORT).show();
                        }
                    } catch (URISyntaxException e) {
                        //not an intent uri
                    }
                }
                return true;//do nothing in other cases
            }

        });



        /*
        Uri uri = Uri.parse("https://docs.google.com/spreadsheets/d/12iowqE2agpU-7eKUXN61YmUfJ8v_iJggKR_iOvd9VZQ/edit?usp=sharing");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
        */

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
