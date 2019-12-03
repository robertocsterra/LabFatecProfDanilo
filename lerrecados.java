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
import modelo.modelorecados;

public class lerrecados extends AppCompatActivity {

    private ListView listviewmostratextoMural;

    private List<modelorecados> modelorecadosList = new ArrayList<>();
    private ArrayAdapter<modelorecados> modelorecadosArrayAdapter;

    final private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference("server");

    modelorecados recadosSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lerrecados);

        iniciarFirebase();
        carregaWidgets();
        eventoDatabase();
    }

    private void carregaWidgets() {
        listviewmostratextoMural = (ListView) findViewById(R.id.listviewmostratextomural);
    }

    private void iniciarFirebase() {
        FirebaseApp.initializeApp(lerrecados.this);
    }

    private void eventoDatabase() {
        databaseReference.child("recados").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                modelorecadosList.clear();
                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    modelorecados lr = objSnapshot.getValue(modelorecados.class);
                    modelorecadosList.add(lr);
                }
                modelorecadosArrayAdapter = new ArrayAdapter<modelorecados>(lerrecados.this,
                        android.R.layout.simple_list_item_1, modelorecadosList);
                listviewmostratextoMural.setAdapter(modelorecadosArrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void sairapp() {

        AlertDialog.Builder builder = new AlertDialog.Builder(lerrecados.this);
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
        inflater.inflate(R.menu.menulerrecados, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){

            case R.id.menulerrecadosvoltar:
                Intent lrvoltar = new Intent(lerrecados.this,inicial.class);
                startActivity(lrvoltar);
                return (true);

            case R.id.menulerrecadossair:
                sairapp();
        }

        return (super.onOptionsItemSelected(item));
    }
}
