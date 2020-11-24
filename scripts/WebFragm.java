package com.lag.altanizio.conceitosdetopografia;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import java.net.URISyntaxException;


/**
 * A simple {@link Fragment} subclass.
 */
public class WebFragm extends Fragment {
    private  String URL;
    private Button button;

    public WebFragm() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web, container, false);
        button = (Button) view.findViewById(R.id.button3);

        URL  = getArguments().getString("key");

        WebView myWebView = (WebView) view.findViewById(R.id.web);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.loadUrl(URL);

        myWebView.setWebViewClient(new WebViewClient(){

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

                ((MainActivity)getActivity()).openSobre();
                Toast.makeText(getContext().getApplicationContext(), "Erro ao carregar a página", Toast.LENGTH_LONG).show();
                button.setText("ERROR!");

            }
        });

        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                button.setText("");
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                button.setText("Carregando... Por favor aguarde.");

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
