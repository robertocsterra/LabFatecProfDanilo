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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import modelo.modeloalimentamural;

public class alimentamural extends AppCompatActivity {

    private Button btnPublicamural, btnmuralAlterar, btnmuralExcluir;
    private EditText uid, edtmuralTitulo, edtmuralData, edtmuraltextodaNoticia;
    private ListView listviewmostratextoMural;

    private List<modeloalimentamural> modeloalimentamuralList = new ArrayList<>();
    private ArrayAdapter<modeloalimentamural> modeloalimentamuralArrayAdapter;

    final private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference("server");

    modeloalimentamural alimentamuralSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alimentamural);

        iniciarFirebase();
        carregaWidgets();
        eventoDatabase();
        botoes();

        listviewmostratextoMural.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long uid) {
                alimentamuralSelecionado = (modeloalimentamural) parent.getItemAtPosition(position);

                edtmuralTitulo.setText(alimentamuralSelecionado.getMedtmuraltitulo());
                edtmuralData.setText(alimentamuralSelecionado.getMedtmuraldata());
                edtmuraltextodaNoticia.setText(alimentamuralSelecionado.getMedtmuraltextodanoticia());
            }
        });

    }

    private void carregaWidgets() {
        edtmuralTitulo = (EditText) findViewById(R.id.edtmuraltitulo);
        edtmuralData = (EditText) findViewById(R.id.edtmuraldata);
        edtmuraltextodaNoticia = (EditText) findViewById(R.id.edtmuraltextodanoticia);
        btnPublicamural = (Button) findViewById(R.id.btnpublicamural);
        btnmuralAlterar = (Button) findViewById(R.id.btnmuralalterar);
        btnmuralExcluir = (Button) findViewById(R.id.btnmuralexcluir);
        listviewmostratextoMural = (ListView) findViewById(R.id.listviewmostratextomural);
    }

    private void iniciarFirebase() {
        FirebaseApp.initializeApp(alimentamural.this);
    }

    private void eventoDatabase() {
        databaseReference.child("alimentamural").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                modeloalimentamuralList.clear();
                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    modeloalimentamural a = objSnapshot.getValue(modeloalimentamural.class);
                    modeloalimentamuralList.add(a);
                }
                modeloalimentamuralArrayAdapter = new ArrayAdapter<modeloalimentamural>(alimentamural.this,
                        android.R.layout.simple_list_item_1, modeloalimentamuralList);
                listviewmostratextoMural.setAdapter(modeloalimentamuralArrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void botoes() {

        btnPublicamural.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modeloalimentamural a = new modeloalimentamural();

                a.setUid(UUID.randomUUID().toString());

                a.setMedtmuraltitulo(edtmuralTitulo.getText().toString());
                a.setMedtmuraldata(edtmuralData.getText().toString());
                a.setMedtmuraltextodanoticia(edtmuraltextodaNoticia.getText().toString());

                databaseReference.child("alimentamural").child(a.getUid()).setValue(a);
                limparCampos();
            }
        });

        btnmuralAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modeloalimentamural a = new modeloalimentamural();

                a.setUid(alimentamuralSelecionado.getUid());

                a.setMedtmuraltitulo(edtmuralTitulo.getText().toString());
                a.setMedtmuraldata(edtmuralData.getText().toString());
                a.setMedtmuraltextodanoticia(edtmuraltextodaNoticia.getText().toString());

                databaseReference.child("alimentamural").child(a.getUid()).setValue(a);
                limparCampos();
            }
        });

        btnmuralExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modeloalimentamural a = new modeloalimentamural();

                a.setUid(alimentamuralSelecionado.getUid());

                a.setMedtmuraltitulo(edtmuralTitulo.getText().toString());
                a.setMedtmuraldata(edtmuralData.getText().toString());
                a.setMedtmuraltextodanoticia(edtmuraltextodaNoticia.getText().toString());

                databaseReference.child("alimentamural").child(a.getUid()).removeValue();
                limparCampos();
            }
        });
    }

    private void limparCampos() {

        edtmuralTitulo.setText("");
        edtmuralData.setText("");
        edtmuraltextodaNoticia.setText("");
    }

    private void sairapp() {

        AlertDialog.Builder builder = new AlertDialog.Builder(alimentamural.this);
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
        inflater.inflate(R.menu.menualimentamural, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){

            case R.id.menualimentamuralvoltar:
                Intent amvoltar = new Intent(alimentamural.this,inicial.class);
                startActivity(amvoltar);
                return (true);

            case R.id.menualimentamuralsair:
                sairapp();
        }

        return (super.onOptionsItemSelected(item));
    }
}
