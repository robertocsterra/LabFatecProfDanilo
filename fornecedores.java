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
import modelo.modelofornecedores;

public class fornecedores extends AppCompatActivity {

    private Button btnforCadastrar, btnforAlterar, btnforExcluir;
    private EditText uid, edtforNome, edtforCnpj, edtforCpf, edtforTelefone, edtforEmail, edtforSeguimento;
    private ListView listviewmostraFornecedores;

    private List<modelofornecedores> modelofornecedoresList = new ArrayList<>();
    private ArrayAdapter<modelofornecedores> modelofornecedoresArrayAdapter;

    final private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference("server");

    modelofornecedores fornecedorSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fornecedores);

        iniciarFirebase();
        carregaWidgets();
        eventoDatabase();
        botoes();

        listviewmostraFornecedores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long uid) {
                fornecedorSelecionado = (modelofornecedores) parent.getItemAtPosition(position);

                edtforNome.setText(fornecedorSelecionado.getMedtfornome());
                edtforCnpj.setText(fornecedorSelecionado.getMedtforcnpj());
                edtforCpf.setText(fornecedorSelecionado.getMedtforcpf());
                edtforTelefone.setText(fornecedorSelecionado.getMedtfortelefone());
                edtforEmail.setText(fornecedorSelecionado.getMedtforemail());
                edtforSeguimento.setText(fornecedorSelecionado.getMedtforseguimento());
            }
        });

    }

    private void carregaWidgets() {
        edtforNome = (EditText) findViewById(R.id.edtfornome);
        edtforCnpj = (EditText) findViewById(R.id.edtforcnpj);
        edtforCpf = (EditText) findViewById(R.id.edtforcpf);
        edtforTelefone = (EditText) findViewById(R.id.edtfortelefone);
        edtforEmail = (EditText) findViewById(R.id.edtforemail);
        edtforSeguimento = (EditText) findViewById(R.id.edtforseguimento);
        btnforCadastrar = (Button) findViewById(R.id.btnforcadastrar);
        btnforAlterar = (Button) findViewById(R.id.btnforalterar);
        btnforExcluir = (Button) findViewById(R.id.btnforexcluir);
        listviewmostraFornecedores = (ListView) findViewById(R.id.listviewmostrafornecedores);
    }

    private void iniciarFirebase()
    {
        FirebaseApp.initializeApp(fornecedores.this);
    }

    private void eventoDatabase() {
        databaseReference.child("fornecedores").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                modelofornecedoresList.clear();
                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    modelofornecedores f = objSnapshot.getValue(modelofornecedores.class);
                    modelofornecedoresList.add(f);
                }
                modelofornecedoresArrayAdapter = new ArrayAdapter<modelofornecedores>(fornecedores.this,
                        android.R.layout.simple_list_item_1, modelofornecedoresList);
                listviewmostraFornecedores.setAdapter(modelofornecedoresArrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void botoes() {

        btnforCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modelofornecedores f = new modelofornecedores();

                f.setUid(UUID.randomUUID().toString());

                f.setMedtfornome(edtforNome.getText().toString());
                f.setMedtforcnpj(edtforCnpj.getText().toString());
                f.setMedtforcpf(edtforCpf.getText().toString());
                f.setMedtfortelefone(edtforTelefone.getText().toString());
                f.setMedtforemail(edtforEmail.getText().toString());
                f.setMedtforseguimento(edtforSeguimento.getText().toString());

                databaseReference.child("fornecedores").child(f.getUid()).setValue(f);
                limparCampos();
            }
        });

        btnforAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modelofornecedores f = new modelofornecedores();

                f.setUid(fornecedorSelecionado.getUid());

                f.setMedtfornome(edtforNome.getText().toString());
                f.setMedtforcnpj(edtforCnpj.getText().toString());
                f.setMedtforcpf(edtforCpf.getText().toString());
                f.setMedtfortelefone(edtforTelefone.getText().toString());
                f.setMedtforemail(edtforEmail.getText().toString());
                f.setMedtforseguimento(edtforSeguimento.getText().toString());

                databaseReference.child("fornecedores").child(f.getUid()).setValue(f);
                limparCampos();
            }
        });

        btnforExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modelofornecedores f = new modelofornecedores();

                f.setUid(fornecedorSelecionado.getUid());

                f.setMedtfornome(edtforNome.getText().toString());
                f.setMedtforcnpj(edtforCnpj.getText().toString());
                f.setMedtforcpf(edtforCpf.getText().toString());
                f.setMedtfortelefone(edtforTelefone.getText().toString());
                f.setMedtforemail(edtforEmail.getText().toString());
                f.setMedtforseguimento(edtforSeguimento.getText().toString());

                databaseReference.child("fornecedores").child(f.getUid()).removeValue();
                limparCampos();
            }
        });
    }

    private void limparCampos() {
        edtforNome.setText("");
        edtforCnpj.setText("");
        edtforCpf.setText("");
        edtforTelefone.setText("");
        edtforEmail.setText("");
        edtforSeguimento.setText("");
    }

    private void sairapp() {

        AlertDialog.Builder builder = new AlertDialog.Builder(fornecedores.this);
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
        inflater.inflate(R.menu.menufornecedor, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){

            case R.id.menufornecedorvoltar:
                Intent forvoltar = new Intent(fornecedores.this,inicial.class);
                startActivity(forvoltar);
                return (true);

            case R.id.menufornecedorsair:
                sairapp();
        }

        return (super.onOptionsItemSelected(item));
    }
}
