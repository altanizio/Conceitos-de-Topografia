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


import java.io.File;
import java.net.URISyntaxException;


/**
 * A simple {@link Fragment} subclass.
 */
public class PDFreaderFragm extends Fragment {
    private String pdf;
    private File file;
    private Button button01;





    public PDFreaderFragm() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pdfreader, container, false);

        button01 = (Button) view.findViewById(R.id.button);

        button01.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                InicioFragm tab5 = new InicioFragm();
            }
        });

        WebView pdfView = (WebView) view.findViewById(R.id.pdfView);
        pdfView.getSettings().setJavaScriptEnabled(true);
        if (pdf!=null) {
            pdfView.loadUrl("http://docs.google.com/gview?embedded=true&url=" + pdf);
        }else {
            try{
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(file),"application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().startActivity(intent);
            } catch (Exception e) {
                //not an intent uri
            }
        }

            pdfView.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageStarted (WebView view, String url, Bitmap favicon){

                    super.onPageStarted(view, url, favicon);
                   // if (pdf.startsWith("http")) {

                    // }else {
                    //     shouldOverrideUrlLoading(view, url);
                    //  }

                }

                @Override
                public boolean shouldOverrideUrlLoading(WebView webView, String url) {


                    Uri parsedUri = Uri.parse(pdf);
                    PackageManager packageManager = getActivity().getPackageManager();
                    Intent browseIntent = new Intent(Intent.ACTION_VIEW).setData(parsedUri);

                    try {
                        Intent intent = Intent.parseUri(pdf, Intent.URI_INTENT_SCHEME);
                        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                            getActivity().startActivity(intent);
                            return true;
                        }
                        //try to find fallback url
                        String fallbackUrl = intent.getStringExtra("browser_fallback_url");
                        if (fallbackUrl != null) {
                            webView.loadUrl(fallbackUrl);
                            return true;
                        }
                        //invite to install
                        Intent marketIntent = new Intent(Intent.ACTION_VIEW).setData(
                                Uri.parse("market://details?id=" + intent.getPackage()));
                        if (marketIntent.resolveActivity(packageManager) != null) {
                            getActivity().startActivity(marketIntent);
                            return true;
                        }
                    } catch (URISyntaxException e) {
                        //not an intent uri
                    }

                    return true;//do nothing in other cases


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

    @Override
    public void onResume() {
        super.onResume();
        //getActivity().onBackPressed();
    }

}
