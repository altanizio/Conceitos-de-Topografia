package com.lag.altanizio.conceitosdetopografia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Contato extends AppCompatActivity {
    private Button button01;
    private EditText text01;
    private EditText text02;
    private EditText text03;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_contato);
        getSupportActionBar().setTitle("Contato");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        text01 = (EditText) findViewById(R.id.editText01);
        text02 = (EditText) findViewById(R.id.editText02);
        text03 = (EditText) findViewById(R.id.editText03);



        button01 = (Button) findViewById(R.id.button01);
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



    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
