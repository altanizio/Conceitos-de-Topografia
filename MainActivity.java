package com.lag.altanizio.conceitosdetopografia;



import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;



public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private ViewPager viewPager;
    public ViewPager getViewPager(){
        return  viewPager;
    }
    private Toolbar toolbar;

    public  boolean verificaConexao() {
        ConnectivityManager conectivtyManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = conectivtyManager.getActiveNetworkInfo();
        if (conectivtyManager.getActiveNetworkInfo() != null
                && conectivtyManager.getActiveNetworkInfo().isAvailable()
                && conectivtyManager.getActiveNetworkInfo().isConnected()) {
            if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI){
                Log.d("Internet","Wi-fi");
                return true;
            }
            //Verifica o 3G
            else if(activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE){
                Log.d("Internet","3g ou 4g");
                return true;
            }
            return true;
        } else {
            return false;
        }
    }
    public void hideToolbar(){
        //toolbar.animate().translationY(-toolbar.getBottom()).setInterpolator(new AccelerateInterpolator()).start();

       // toolbar.animate().translationY(toolbar.getHeight()).setInterpolator(new AccelerateInterpolator(2)).start();
       // getSupportActionBar().hide();

        if (getSupportActionBar() != null && getSupportActionBar().isShowing()) {
            if(toolbar != null) {
                toolbar.animate().translationY(-112).setDuration(600L)
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                getSupportActionBar().hide();
                            }
                        }).start();
            } else {
                getSupportActionBar().hide();
            }
    }}

    public void showToolbar(){

        if (getSupportActionBar() != null && !getSupportActionBar().isShowing()) {
            toolbar.animate().translationY(0).setDuration(600L).withEndAction(new Runnable() {
                @Override
                public void run() {
                    getSupportActionBar().show();
                }
            }).start();
        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_main);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Get the ViewPager and set it's PagerAdapter so that it can display items
        //Menu flutuante
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);



        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //

        //Botão flutuante
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(GravityCompat.START);
            }
        });*/

        //Iniciar Primeiro fragmento angulos
        if (savedInstanceState == null) {
            // adicionar o fragmento inicial
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.frame_container, new InicioFragm(),"Conceitos de Topografia").addToBackStack("Conceitos de Topografia").commit();
            getSupportActionBar().setTitle("Conceitos de Topografia");


        }


        if(Build.VERSION.SDK_INT <= 21) {

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Você esta utilizando uma versão do android antiga, Código: " + Build.VERSION.SDK_INT + ", logo erros podem acontecer.\n\nO suporte a sua versão ainda não foi feita.")
                        .setPositiveButton("Ok entendi", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        }).show();

            }

    }

    public String getVersion(){
        try {
            PackageInfo pInfo = this.getPackageManager().getPackageInfo(getPackageName(), 0);
            String version = pInfo.versionName;
            return version;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "Versão relase";
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            /* else{

            int lastBackStackEntryCount = getSupportFragmentManager().getBackStackEntryCount()-2;
                if(lastBackStackEntryCount>=0) {
                    getSupportActionBar().setTitle(getSupportFragmentManager().getBackStackEntryAt(lastBackStackEntryCount).getName());
                    super.onBackPressed();
                }else {
                    getSupportActionBar().setTitle("Início");
                    super.onBackPressed();
                }
        }*/
            // }
            // getSupportFragmentManager()
            //         .beginTransaction()
            //         .add(R.id.frame_container, new InicioFragm()).addToBackStack("Início").commit();
            // super.onBackPressed();


            Fragment escall = getSupportFragmentManager().findFragmentByTag("Conceitos de Topografia");
            // Toast.makeText(getApplicationContext(), escall.getTag(), Toast.LENGTH_SHORT).show();
            if (escall != null && escall.isVisible()) {
                finish();
            }
            Fragment escall2 = getSupportFragmentManager().findFragmentByTag("Horário monitores");

            if (escall2 != null && escall.isVisible()) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new SobreFragm(), "Sobre").addToBackStack("Sobre").commit();
                getSupportActionBar().setTitle("Sobre");

            }
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new InicioFragm(), "Conceitos de Topografia").addToBackStack("Conceitos de Topografia").commit();
            getSupportActionBar().setTitle("Conceitos de Topografia");

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.action_settings) {

           openSobre();

            return true;
        }
            // if(id == R.id.action_feed){
//
       //     openContato();
    //    }


            return super.onOptionsItemSelected(item);
    }

    public void openSobre(){
        getSupportActionBar().setTitle("Sobre");
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_container, new SobreFragm(),"Sobre").addToBackStack("Sobre").commit();
    }
    public void openContato(){
       // getSupportActionBar().setTitle("Contato");
       // getSupportFragmentManager()
        //        .beginTransaction()
         //       .replace(R.id.frame_container, new ContatoFragment(),"Contato").addToBackStack("Contato").commit();

        final Intent con = new Intent(MainActivity.this, Contato.class);
        startActivity(con);

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.eqp_estacao) {
            //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            //        WindowManager.LayoutParams.FLAG_FULLSCREEN);
            estacao();
        }else if(id == R.id.eqp_nivel){
            nivel();
        }
        else if (id == R.id.nav_planilhas) {
            pdfCard();
        } else if (id == R.id.nav_horarios) {
            horarios();
        } else if (id == R.id.nav_bussula) {
            bussola();
        } else if (id == R.id.nav_bolha){
            bolha();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public  void estacao(){
        getSupportActionBar().setTitle("Planimetria");
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_container, new LevantPlanimFragm()).addToBackStack("Planimetria").commit();
    }

    public  void nivel(){
        getSupportActionBar().setTitle("Altimetria");
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_container, new LevantAltimFragm()).addToBackStack("Altimetria").commit();
    }

    public void pdfCard(){
        AssetManager assetManager = getAssets();

        InputStream in = null;
        OutputStream out = null;
        File file = new File(getFilesDir(), "apoio.pdf");
        try
        {
            in = assetManager.open("apoio.pdf");
            out = openFileOutput(file.getName(), Context.MODE_WORLD_READABLE);

            copyFile(in, out);
            in.close();
            in = null;
            out.flush();
            out.close();
            out = null;
        } catch (Exception e)
        {
            Log.e("tag", e.getMessage());
        }

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(
                Uri.parse("file://" + getFilesDir() + "/apoio.pdf"),
                        "application/pdf");
        try
        {
        startActivity(intent);
        }
        catch (Exception e) {
            try{
                intent.setData(
                        Uri.parse("file://" + getFilesDir() + "/apoio.pdf"));
            }catch (Exception d){
            Intent marketIntent = new Intent(Intent.ACTION_VIEW).setData(
                    Uri.parse("market://details?id=" + intent.getPackage()));
            if (marketIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(marketIntent);
            } else {
                Toast.makeText(getApplicationContext(), "Seu aparelho não possui leitor de pdf e Google Play ", Toast.LENGTH_SHORT).show();
            }
        }}
    }




    public void pdfCardOld(){
        if (!verificaConexao()) {


            File fileBrochure = new File(Environment.getExternalStorageDirectory() + "/TopLagPDFs/" + "apoio.pdf");
            if (!fileBrochure.exists())
            {
                CopyAssetsbrochure("apoio.pdf");
            }

            /** PDF reader code */
            File file = new File(Environment.getExternalStorageDirectory() + "/TopLagPDFs/" + "apoio.pdf");
            Intent intent = new Intent(Intent.ACTION_VIEW);
            //intent.setDataAndType(Uri.fromFile(file),"application/pdf");
            intent.setData(Uri.fromFile(file));
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            try
            {
                getApplicationContext().startActivity(intent);
            }
            catch (Exception e)
            {
                Intent marketIntent = new Intent(Intent.ACTION_VIEW).setData(
                        Uri.parse("market://details?id=" + intent.getPackage()));
                if (marketIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(marketIntent);
            }else{
                    Toast.makeText(getApplicationContext(), "Seu aparelho não possui leitor de pdf e Google Play ", Toast.LENGTH_SHORT).show();
                }  }
            //getSupportActionBar().setTitle("Planilhas");



            //PDFreaderFragm pdf = new PDFreaderFragm(file,null);
            //getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, pdf).addToBackStack("PDFreader").commit();
       } else {
            openWeb("Redes de apoio","https://drive.google.com/file/d/0BxB8eJ4IyazGVmJTOUdyR1VRZTQ/view?usp=sharing");
          //  Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/file/d/0BxB8eJ4IyazGVmJTOUdyR1VRZTQ/preview"));
          //  startActivity(intent);
           // getSupportActionBar().setTitle("Planilhas");
           // PDFreaderFragm pdf = new PDFreaderFragm(null,"https://docs.wixstatic.com/ugd/052a5c_be3191e5ea194e6a89f5c3a4d1076a4c.pdf");
           // getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, pdf).addToBackStack("PDFreader").commit();
         //   Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://docs.wixstatic.com/ugd/052a5c_be3191e5ea194e6a89f5c3a4d1076a4c.pdf"));
         //   startActivity(intent);
        }
    }

 //   public  void abrirPDF(String title,String uri){
 //       getSupportActionBar().setTitle(title);
  //      PDFreaderFragm pdf = new PDFreaderFragm(null,uri);
  //      getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, pdf).addToBackStack(title).commit();
 //   }

    public  void horarios(){
        if (verificaConexao()) {
            getSupportActionBar().setTitle("Horários");
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new EquipamentosFragm()).addToBackStack("Equipamentos").commit();


        } else {
            Toast.makeText(getApplicationContext(), "Seu aparelho não esta conectado com a internet.", Toast.LENGTH_LONG).show();
        }
    }

    public  void bussola(){
        PackageManager pm = getPackageManager();
        if (!pm.hasSystemFeature(PackageManager.FEATURE_SENSOR_COMPASS)) {
            Toast.makeText(getApplicationContext(), "Seu aparelho não é compatível.", Toast.LENGTH_LONG).show();
        }else {
            getSupportActionBar().setTitle("Bússola");
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_container, new BussolaFragm()).addToBackStack("Bússola").commit();
        }
    }

    public  void openWeb(String webTitle,String webURL){
        if (verificaConexao()) {
            getSupportActionBar().setTitle(webTitle);

            Fragment webV =new WebFragm();

            Bundle args = new Bundle();
            args.putString("key", webURL);
            webV.setArguments(args);

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_container, webV,webTitle).addToBackStack(webTitle).commit();


        }else{
            Toast.makeText(getApplicationContext(), "Deve estar conectado à internet para acessar este conteúdo", Toast.LENGTH_LONG).show();
        }

    }

    public  void bolha(){
        getSupportActionBar().setTitle("Bolha para nivelar");
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_container, new BolhaFragm()).addToBackStack("Bolha para nivelar").commit();

    }

    public int tamanhoTela(){
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    @Override
    protected void onDestroy() {
        Runtime.getRuntime().gc();
        super.onDestroy();
    }
    @Override
    public void onStop() {
        super.onStop();
        Runtime.getRuntime().gc();
    }

    private void CopyAssetsbrochure(String pdf) {
        AssetManager assetManager = getAssets();
        String[] files = null;
        try
        {
            files = assetManager.list("");
        }
        catch (IOException e)
        {
            Toast.makeText(getApplicationContext(), "Erro ao salvar o arquivo", Toast.LENGTH_SHORT).show();
        }
        for(int i=0; i<files.length; i++)
        {
            String fStr = files[i];
            if(fStr.equalsIgnoreCase(pdf))
            {
                InputStream in = null;
                OutputStream out = null;
                try
                {
                    File directory = new File(Environment.getExternalStorageDirectory() + "/TopLagPDFs/");
                    directory.mkdirs();

                    in = assetManager.open(files[i]);
                    out = new FileOutputStream(Environment.getExternalStorageDirectory() + "/TopLagPDFs/" + files[i]);

                    copyFile(in, out);
                    in.close();
                    in = null;
                    out.flush();
                    out.close();
                    out = null;
                    break;
                }
                catch(Exception e)
                {
                    Toast.makeText(getApplicationContext(), "Erro: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
        }
    }
}
