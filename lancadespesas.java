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
import modelo.modelolancadespesas;

public class lancadespesas extends AppCompatActivity {

    private Button btnldespesasInserir, btnldespesasAlterar, btnldespesasExcluir;
    private EditText uid, edtldespesasFornecedor, edtTipodespesa, edtldespesasData, edtldespesasDescricao, edtldespesasValor, edtldespesasObs;
    private ListView listviewmostraLdespesas;

    private List<modelolancadespesas> modelolancadespesasList = new ArrayList<>();
    private ArrayAdapter<modelolancadespesas> modelolancadespesasArrayAdapter;

    final private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference("server");

    modelolancadespesas despesaSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lancadespesas);

        iniciarFirebase();
        carregaWidgets();
        eventoDatabase();
        botoes();

        listviewmostraLdespesas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long uid) {
                despesaSelecionada = (modelolancadespesas) parent.getItemAtPosition(position);

                edtldespesasFornecedor.setText(despesaSelecionada.getMedtldespesasfornecedor());
                edtTipodespesa.setText(despesaSelecionada.getMedttipodespesa());
                edtldespesasData.setText(despesaSelecionada.getMedtldespesasdata());
                edtldespesasDescricao.setText(despesaSelecionada.getMedtldespesasdescricao());
                edtldespesasValor.setText(despesaSelecionada.getMedtldespesasvalor());
                edtldespesasObs.setText(despesaSelecionada.getMedtldespesasobs());
            }
        });
    }

    private void carregaWidgets() {
        edtldespesasFornecedor = (EditText) findViewById(R.id.edtldespesasfornecedor);
        edtTipodespesa = (EditText) findViewById(R.id.edttipodespesa);
        edtldespesasData = (EditText) findViewById(R.id.edtldespesasdata);
        edtldespesasDescricao = (EditText) findViewById(R.id.edtldespesasdescricao);
        edtldespesasValor = (EditText) findViewById(R.id.edtldespesasvalor);
        edtldespesasObs = (EditText) findViewById(R.id.edtldespesasobs);
        btnldespesasInserir = (Button) findViewById(R.id.btnldespesasinserir);
        btnldespesasAlterar = (Button) findViewById(R.id.btnldespesasalterar);
        btnldespesasExcluir = (Button) findViewById(R.id.btnldespesasexcluir);
        listviewmostraLdespesas = (ListView) findViewById(R.id.listviewmostraldespesas);
    }

    private void iniciarFirebase()
    {
        FirebaseApp.initializeApp(lancadespesas.this);
    }

    private void eventoDatabase() {
        databaseReference.child("lancadespesas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                modelolancadespesasList.clear();
                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    modelolancadespesas l = objSnapshot.getValue(modelolancadespesas.class);
                    modelolancadespesasList.add(l);
                }
                modelolancadespesasArrayAdapter = new ArrayAdapter<modelolancadespesas>(lancadespesas.this,
                        android.R.layout.simple_list_item_1, modelolancadespesasList);
                listviewmostraLdespesas.setAdapter(modelolancadespesasArrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void botoes() {

        btnldespesasInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modelolancadespesas l = new modelolancadespesas();

                l.setUid(UUID.randomUUID().toString());

                l.setMedtldespesasfornecedor(edtldespesasFornecedor.getText().toString());
                l.setMedttipodespesa(edtTipodespesa.getText().toString());
                l.setMedtldespesasdata(edtldespesasData.getText().toString());
                l.setMedtldespesasdescricao(edtldespesasDescricao.getText().toString());
                l.setMedtldespesasvalor(edtldespesasValor.getText().toString());
                l.setMedtldespesasobs(edtldespesasObs.getText().toString());

                databaseReference.child("lancadespesas").child(l.getUid()).setValue(l);
                limparCampos();
            }
        });

        btnldespesasAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modelolancadespesas l = new modelolancadespesas();

                l.setUid(despesaSelecionada.getUid());

                l.setMedtldespesasfornecedor(edtldespesasFornecedor.getText().toString());
                l.setMedttipodespesa(edtTipodespesa.getText().toString());
                l.setMedtldespesasdata(edtldespesasData.getText().toString());
                l.setMedtldespesasdescricao(edtldespesasDescricao.getText().toString());
                l.setMedtldespesasvalor(edtldespesasValor.getText().toString());
                l.setMedtldespesasobs(edtldespesasObs.getText().toString());

                databaseReference.child("lancadespesas").child(l.getUid()).setValue(l);
                limparCampos();
            }
        });

        btnldespesasExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modelolancadespesas l = new modelolancadespesas();

                l.setUid(despesaSelecionada.getUid());

                l.setMedtldespesasfornecedor(edtldespesasFornecedor.getText().toString());
                l.setMedttipodespesa(edtTipodespesa.getText().toString());
                l.setMedtldespesasdata(edtldespesasData.getText().toString());
                l.setMedtldespesasdescricao(edtldespesasDescricao.getText().toString());
                l.setMedtldespesasvalor(edtldespesasValor.getText().toString());
                l.setMedtldespesasobs(edtldespesasObs.getText().toString());

                databaseReference.child("lancadespesas").child(l.getUid()).removeValue();
                limparCampos();
            }
        });
    }

    private void limparCampos() {

        edtldespesasFornecedor.setText("");
        edtTipodespesa.setText("");
        edtldespesasData.setText("");
        edtldespesasDescricao.setText("");
        edtldespesasValor.setText("");
        edtldespesasObs.setText("");

    }

    private void sairapp() {

        AlertDialog.Builder builder = new AlertDialog.Builder(lancadespesas.this);
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
        inflater.inflate(R.menu.menulancadespesas, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){

            case R.id.menulancadespesasvoltar:
                Intent lanvoltar = new Intent(lancadespesas.this,inicial.class);
                startActivity(lanvoltar);
                return (true);

            case R.id.menulancadespesassair:
                sairapp();
        }

        return (super.onOptionsItemSelected(item));
    }
}

