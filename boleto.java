package com.example.admcondominio;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class boleto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boleto);
    }

    private void sairapp() {

        AlertDialog.Builder builder = new AlertDialog.Builder(boleto.this);
        builder.setMessage("Deseja realmente sair do sistema?");
        builder.setCancelable(true);
        builder.setPositiveButton("Sair", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();


            }
        });
        builder.setNegativeButton("Voltar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();

            }
        });
        AlertDialog alert = builder.create();
        alert.show();

    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menueboleto, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){

            case R.id.menueboletovoltar:
                Intent ebolvoltar = new Intent(boleto.this,inicial.class);
                startActivity(ebolvoltar);
                return (true);

            case R.id.menueboletosair:
                sairapp();
        }

        return (super.onOptionsItemSelected(item));
    }
}

