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
import modelo.modeloperiodo;

public class periodo extends AppCompatActivity {

    private Button btnperiodoSalvar, btnperiodoAlterar, btnperiodoExcluir;
    private EditText uid, edtperiodoNome, edtperiodoAno, edtperiodoInicio, edtperiodoFinal;
    private ListView listviewmostraPeriodo;

    private List<modeloperiodo> modeloperiodoList = new ArrayList<>();
    private ArrayAdapter<modeloperiodo> modeloperiodoArrayAdapter;

    final private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference("server");

    modeloperiodo periodoSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_periodo);

        iniciarFirebase();
        carregaWidgets();
        eventoDatabase();
        botoes();

        listviewmostraPeriodo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long uid) {
                periodoSelecionado = (modeloperiodo) parent.getItemAtPosition(position);

                edtperiodoNome.setText(periodoSelecionado.getMedtperiodonome());
                edtperiodoAno.setText(periodoSelecionado.getMedtperiodoano());
                edtperiodoInicio.setText(periodoSelecionado.getMedtperiodoinicio());
                edtperiodoFinal.setText(periodoSelecionado.getMedtperiodofinal());

            }
        });

    }

    private void carregaWidgets() {
        edtperiodoNome = (EditText) findViewById(R.id.edtperiodonome);
        edtperiodoAno = (EditText) findViewById(R.id.edtperiodoano);
        edtperiodoInicio = (EditText) findViewById(R.id.edtperiodoinicio);
        edtperiodoFinal = (EditText) findViewById(R.id.edtperiodofinal);
        btnperiodoSalvar = (Button) findViewById(R.id.btnperiodosalvar);
        btnperiodoAlterar = (Button) findViewById(R.id.btnperiodoalterar);
        btnperiodoExcluir = (Button) findViewById(R.id.btnperiodoexcluir);
        listviewmostraPeriodo = (ListView) findViewById(R.id.listviewmostraperiodo);

    }

    private void iniciarFirebase() {
        FirebaseApp.initializeApp(periodo.this);
    }

    private void eventoDatabase() {
        databaseReference.child("periodo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                modeloperiodoList.clear();
                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    modeloperiodo p = objSnapshot.getValue(modeloperiodo.class);
                    modeloperiodoList.add(p);
                }
                modeloperiodoArrayAdapter = new ArrayAdapter<modeloperiodo>(periodo.this,
                        android.R.layout.simple_list_item_1, modeloperiodoList);
                listviewmostraPeriodo.setAdapter(modeloperiodoArrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void botoes() {

        btnperiodoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modeloperiodo p = new modeloperiodo();

                p.setUid(UUID.randomUUID().toString());

                p.setMedtperiodonome(edtperiodoNome.getText().toString());
                p.setMedtperiodoano(edtperiodoAno.getText().toString());
                p.setMedtperiodoinicio(edtperiodoInicio.getText().toString());
                p.setMedtperiodofinal(edtperiodoFinal.getText().toString());

                databaseReference.child("periodo").child(p.getUid()).setValue(p);
                limparCampos();
            }
        });

        btnperiodoAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modeloperiodo p = new modeloperiodo();

                p.setUid(periodoSelecionado.getUid());

                p.setMedtperiodonome(edtperiodoNome.getText().toString());
                p.setMedtperiodoano(edtperiodoAno.getText().toString());
                p.setMedtperiodoinicio(edtperiodoInicio.getText().toString());
                p.setMedtperiodofinal(edtperiodoFinal.getText().toString());

                databaseReference.child("periodo").child(p.getUid()).setValue(p);
                limparCampos();
            }
        });

        btnperiodoExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modeloperiodo p = new modeloperiodo();

                p.setUid(periodoSelecionado.getUid());

                p.setMedtperiodonome(edtperiodoNome.getText().toString());
                p.setMedtperiodoano(edtperiodoAno.getText().toString());
                p.setMedtperiodoinicio(edtperiodoInicio.getText().toString());
                p.setMedtperiodofinal(edtperiodoFinal.getText().toString());

                databaseReference.child("periodo").child(p.getUid()).removeValue();
                limparCampos();
            }
        });
    }

    private void limparCampos() {

        edtperiodoNome.setText("");
        edtperiodoAno.setText("");
        edtperiodoInicio.setText("");
        edtperiodoFinal.setText("");
    }

    private void sairapp() {

        AlertDialog.Builder builder = new AlertDialog.Builder(periodo.this);
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

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuperiodo, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.menuperiodovoltar:
                Intent pervoltar = new Intent(periodo.this, inicial.class);
                startActivity(pervoltar);
                return (true);

            case R.id.menuperiodosair:
                sairapp();
        }

        return (super.onOptionsItemSelected(item));
    }

}




