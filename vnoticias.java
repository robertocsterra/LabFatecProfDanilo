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
import modelo.modeloalimentamural;

public class vnoticias extends AppCompatActivity {

    private ListView listviewmostraVnoticias;

    private List<modeloalimentamural> modeloalimentamuralList = new ArrayList<>();
    private ArrayAdapter<modeloalimentamural> modeloalimentamuralArrayAdapter;

    final private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference("server");

    modeloalimentamural alimentamuralSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vnoticias);

        iniciarFirebase();
        carregaWidgets();
        eventoDatabase();
    }

    private void carregaWidgets() {
        listviewmostraVnoticias = (ListView) findViewById(R.id.listviewmostravnoticias);
    }

    private void iniciarFirebase() {
        FirebaseApp.initializeApp(vnoticias.this);
    }

    private void eventoDatabase() {
        databaseReference.child("alimentamural").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                modeloalimentamuralList.clear();
                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    modeloalimentamural vn = objSnapshot.getValue(modeloalimentamural.class);
                    modeloalimentamuralList.add(vn);
                }
                modeloalimentamuralArrayAdapter = new ArrayAdapter<modeloalimentamural>(vnoticias.this,
                        android.R.layout.simple_list_item_1, modeloalimentamuralList);
                listviewmostraVnoticias.setAdapter(modeloalimentamuralArrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void sairapp() {

        AlertDialog.Builder builder = new AlertDialog.Builder(vnoticias.this);
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
        inflater.inflate(R.menu.menuvnoticias, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){

            case R.id.menuvnoticiasvoltar:
                Intent vnotvoltar = new Intent(vnoticias.this,inicial.class);
                startActivity(vnotvoltar);
                return (true);

            case R.id.menuvnoticiassair:
                sairapp();
        }

        return (super.onOptionsItemSelected(item));
    }
}
