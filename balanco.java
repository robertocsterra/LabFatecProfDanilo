package com.example.admcondominio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;
import modelo.modelolancadespesas;

public class balanco extends AppCompatActivity {

    private ListView tvmostraBalanco;

    private List<modelolancadespesas> modelolancadespesasList = new ArrayList<>();
    private ArrayAdapter<modelolancadespesas> modelolancadespesasArrayAdapter;

    final private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference("server");

    modelolancadespesas lancadespesasSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balanco);

        iniciarFirebase();
        carregaWidgets();
        eventoDatabase();
    }

    private void carregaWidgets() {
        tvmostraBalanco = (ListView) findViewById(R.id.tvmostrabalanco);
    }

    private void iniciarFirebase() {
        FirebaseApp.initializeApp(balanco.this);
    }

    private void eventoDatabase() {
        databaseReference.child("lancadespesas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                modelolancadespesasList.clear();
                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    modelolancadespesas ba = objSnapshot.getValue(modelolancadespesas.class);
                    modelolancadespesasList.add(ba);
                }
                modelolancadespesasArrayAdapter = new ArrayAdapter<modelolancadespesas>(balanco.this,
                        android.R.layout.simple_list_item_1, modelolancadespesasList);
                tvmostraBalanco.setAdapter(modelolancadespesasArrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }





















































    private void sairapp() {

        AlertDialog.Builder builder = new AlertDialog.Builder(balanco.this);
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
        inflater.inflate(R.menu.menubalanco, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){

            case R.id.menubalancovoltar:
                Intent balvoltar = new Intent(balanco.this,inicial.class);
                startActivity(balvoltar);
                return (true);

            case R.id.menubalancorsair:
                sairapp();
        }

        return (super.onOptionsItemSelected(item));
    }
}
