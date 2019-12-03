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
import modelo.modelomoradias;

public class moradias extends AppCompatActivity {


    private Button btnmoradiasCadastrar, btnmoradiasAlterar, btnmoradiasExcluir;
    private EditText uid, edtmoradiaProprietario, edtmoradiaCpfpro, edtmoradiaTelefonepro, edtmoradiaEndereco, edtmoradiaComplemento, edtmoradiaAtual, edtmoradiaObs;
    private ListView listviewmostraMoradias;

    private List<modelomoradias> modelomoradiasList = new ArrayList<>();
    private ArrayAdapter<modelomoradias> modelomoradiasArrayAdapter;

    final private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference("server");

    modelomoradias moradiaSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moradias);

        iniciarFirebase();
        carregaWidgets();
        eventoDatabase();
        botoes();

        listviewmostraMoradias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long uid) {
                moradiaSelecionada = (modelomoradias) parent.getItemAtPosition(position);

                edtmoradiaProprietario.setText(moradiaSelecionada.getMedtmoradiaproprietario());
                edtmoradiaCpfpro.setText(moradiaSelecionada.getMedtmoradiacpfpro());
                edtmoradiaTelefonepro.setText(moradiaSelecionada.getMedtmoradiatelefonepro());
                edtmoradiaEndereco.setText(moradiaSelecionada.getMedtmoradiaendereco());
                edtmoradiaComplemento.setText(moradiaSelecionada.getMedtmoradiacomplemento());
                edtmoradiaAtual.setText(moradiaSelecionada.getMedtmoradiaatual());
                edtmoradiaObs.setText(moradiaSelecionada.getMedtmoradiaobs());
            }
        });

    }

    private void carregaWidgets() {
        edtmoradiaProprietario = (EditText) findViewById(R.id.edtmoradiaproprietario);
        edtmoradiaCpfpro = (EditText) findViewById(R.id.edtmoradiacpfpro);
        edtmoradiaTelefonepro = (EditText) findViewById(R.id.edtmoradiatelefonepro);
        edtmoradiaEndereco = (EditText) findViewById(R.id.edtmoradiaendereco);
        edtmoradiaComplemento = (EditText) findViewById(R.id.edtmoradiacomplemento);
        edtmoradiaAtual = (EditText) findViewById(R.id.edtmoradiaatual);
        edtmoradiaObs = (EditText) findViewById(R.id.edtmoradiaobs);
        btnmoradiasCadastrar = (Button) findViewById(R.id.btnmoradiascadastrar);
        btnmoradiasAlterar = (Button) findViewById(R.id.btnmoradiasalterar);
        btnmoradiasExcluir = (Button) findViewById(R.id.btnmoradiasexcluir);
        listviewmostraMoradias = (ListView) findViewById(R.id.listviewmostramoradias);
    }

    private void iniciarFirebase()
    {
        FirebaseApp.initializeApp(moradias.this);
    }

    private void eventoDatabase() {
        databaseReference.child("moradias").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                modelomoradiasList.clear();
                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    modelomoradias m = objSnapshot.getValue(modelomoradias.class);
                    modelomoradiasList.add(m);
                }
                modelomoradiasArrayAdapter = new ArrayAdapter<modelomoradias>(moradias.this,
                        android.R.layout.simple_list_item_1, modelomoradiasList);
                listviewmostraMoradias.setAdapter(modelomoradiasArrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void botoes() {

        btnmoradiasCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modelomoradias m = new modelomoradias();

                m.setUid(UUID.randomUUID().toString());

                m.setMedtmoradiaproprietario(edtmoradiaProprietario.getText().toString());
                m.setMedtmoradiacpfpro(edtmoradiaCpfpro.getText().toString());
                m.setMedtmoradiatelefonepro(edtmoradiaTelefonepro.getText().toString());
                m.setMedtmoradiaendereco(edtmoradiaEndereco.getText().toString());
                m.setMedtmoradiacomplemento(edtmoradiaComplemento.getText().toString());
                m.setMedtmoradiaatual(edtmoradiaAtual.getText().toString());
                m.setMedtmoradiaobs(edtmoradiaObs.getText().toString());

                databaseReference.child("moradias").child(m.getUid()).setValue(m);
                limparCampos();
            }
        });

        btnmoradiasAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modelomoradias m = new modelomoradias();

                m.setUid(moradiaSelecionada.getUid());

                m.setMedtmoradiaproprietario(edtmoradiaProprietario.getText().toString());
                m.setMedtmoradiacpfpro(edtmoradiaCpfpro.getText().toString());
                m.setMedtmoradiatelefonepro(edtmoradiaTelefonepro.getText().toString());
                m.setMedtmoradiaendereco(edtmoradiaEndereco.getText().toString());
                m.setMedtmoradiacomplemento(edtmoradiaComplemento.getText().toString());
                m.setMedtmoradiaatual(edtmoradiaAtual.getText().toString());
                m.setMedtmoradiaobs(edtmoradiaObs.getText().toString());

                databaseReference.child("moradias").child(m.getUid()).setValue(m);
                limparCampos();
            }
        });

        btnmoradiasExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modelomoradias m = new modelomoradias();

                m.setUid(moradiaSelecionada.getUid());

                m.setMedtmoradiaproprietario(edtmoradiaProprietario.getText().toString());
                m.setMedtmoradiacpfpro(edtmoradiaCpfpro.getText().toString());
                m.setMedtmoradiatelefonepro(edtmoradiaTelefonepro.getText().toString());
                m.setMedtmoradiaendereco(edtmoradiaEndereco.getText().toString());
                m.setMedtmoradiacomplemento(edtmoradiaComplemento.getText().toString());
                m.setMedtmoradiaatual(edtmoradiaAtual.getText().toString());
                m.setMedtmoradiaobs(edtmoradiaObs.getText().toString());

                databaseReference.child("moradias").child(m.getUid()).removeValue();
                limparCampos();
            }
        });
    }

    private void limparCampos() {
        edtmoradiaProprietario.setText("");
        edtmoradiaCpfpro.setText("");
        edtmoradiaTelefonepro.setText("");
        edtmoradiaEndereco.setText("");
        edtmoradiaComplemento.setText("");
        edtmoradiaAtual.setText("");
        edtmoradiaObs.setText("");

    }

    private void sairapp() {

        AlertDialog.Builder builder = new AlertDialog.Builder(moradias.this);
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
        inflater.inflate(R.menu.menumoradia, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){

            case R.id.menumoradiavoltar:
                Intent morvoltar = new Intent(moradias.this,inicial.class);
                startActivity(morvoltar);
                return (true);

            case R.id.menumoradiasair:
                sairapp();
        }

        return (super.onOptionsItemSelected(item));
    }
}
