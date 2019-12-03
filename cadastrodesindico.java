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
import modelo.modelosindico;

public class cadastrodesindico extends AppCompatActivity {

    private Button btnsindicoCadastrar, btnsindicoAlterar, btnsindicoExcluir;
    private EditText uid, edtsindicoNome, edtsindicoCpf, edtsindicoRg, edtsindicoTelefone, edtsindicoEmail, edtsindicoSenha, edtsindicoInicio, edtsindicoFinal;
    private ListView listviewmostraSindico;

    private List<modelosindico> modelosindicoList = new ArrayList<>();
    private ArrayAdapter<modelosindico> modelosindicoArrayAdapter;

    final private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference("server");

    modelosindico sindicoSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrodesindico);

        iniciarFirebase();
        carregaWidgets();
        eventoDatabase();
        botoes();

        listviewmostraSindico.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long uid) {
                sindicoSelecionado = (modelosindico) parent.getItemAtPosition(position);

                edtsindicoNome.setText(sindicoSelecionado.getMedtsindiconome());
                edtsindicoCpf.setText(sindicoSelecionado.getMedtsindicocpf());
                edtsindicoRg.setText(sindicoSelecionado.getMedtsindicorg());
                edtsindicoTelefone.setText(sindicoSelecionado.getMedtsindicotelefone());
                edtsindicoEmail.setText(sindicoSelecionado.getMedtsindicoemail());
                edtsindicoSenha.setText(sindicoSelecionado.getMedtsindicosenha());
                edtsindicoInicio.setText(sindicoSelecionado.getMedtsindicoinicio());
                edtsindicoFinal.setText(sindicoSelecionado.getMedtsindicofinal());

            }
        });
    }

    private void carregaWidgets() {
        edtsindicoNome = (EditText) findViewById(R.id.edtsindiconome);
        edtsindicoCpf = (EditText) findViewById(R.id.edtsindicocpf);
        edtsindicoRg = (EditText) findViewById(R.id.edtsindicorg);
        edtsindicoTelefone = (EditText) findViewById(R.id.edtsindicotelefone);
        edtsindicoEmail = (EditText) findViewById(R.id.edtsindicoemail);
        edtsindicoSenha = (EditText) findViewById(R.id.edtsindicosenha);
        edtsindicoInicio = (EditText) findViewById(R.id.edtsindicoinicio);
        edtsindicoFinal = (EditText) findViewById(R.id.edtsindicofinal);
        btnsindicoCadastrar = (Button) findViewById(R.id.btnsindicocadastrar);
        btnsindicoAlterar = (Button) findViewById(R.id.btnsindicoalterar);
        btnsindicoExcluir = (Button) findViewById(R.id.btnsindicoexcluir);
        listviewmostraSindico = (ListView) findViewById(R.id.listviewmostrasindico);

    }

    private void iniciarFirebase()
    {
        FirebaseApp.initializeApp(cadastrodesindico.this);
    }

    private void eventoDatabase() {
        databaseReference.child("sindico").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                modelosindicoList.clear();
                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    modelosindico s = objSnapshot.getValue(modelosindico.class);
                    modelosindicoList.add(s);
                }
                modelosindicoArrayAdapter = new ArrayAdapter<modelosindico>(cadastrodesindico.this,
                        android.R.layout.simple_list_item_1, modelosindicoList);
                listviewmostraSindico.setAdapter(modelosindicoArrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void botoes() {

        btnsindicoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modelosindico s = new modelosindico();

                s.setUid(UUID.randomUUID().toString());

                s.setMedtsindiconome(edtsindicoNome.getText().toString());
                s.setMedtsindicocpf(edtsindicoCpf.getText().toString());
                s.setMedtsindicorg(edtsindicoRg.getText().toString());
                s.setMedtsindicotelefone(edtsindicoTelefone.getText().toString());
                s.setMedtsindicoemail(edtsindicoEmail.getText().toString());
                s.setMedtsindicosenha(edtsindicoSenha.getText().toString());
                s.setMedtsindicoinicio(edtsindicoInicio.getText().toString());
                s.setMedtsindicofinal(edtsindicoFinal.getText().toString());

                databaseReference.child("sindico").child(s.getUid()).setValue(s);
                limparCampos();
            }
        });

        btnsindicoAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modelosindico s = new modelosindico();

                s.setUid(sindicoSelecionado.getUid());

                s.setMedtsindiconome(edtsindicoNome.getText().toString());
                s.setMedtsindicocpf(edtsindicoCpf.getText().toString());
                s.setMedtsindicorg(edtsindicoRg.getText().toString());
                s.setMedtsindicotelefone(edtsindicoTelefone.getText().toString());
                s.setMedtsindicoemail(edtsindicoEmail.getText().toString());
                s.setMedtsindicosenha(edtsindicoSenha.getText().toString());
                s.setMedtsindicoinicio(edtsindicoInicio.getText().toString());
                s.setMedtsindicofinal(edtsindicoFinal.getText().toString());

                databaseReference.child("sindico").child(s.getUid()).setValue(s);
                limparCampos();
            }
        });

        btnsindicoExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modelosindico s = new modelosindico();

                s.setUid(sindicoSelecionado.getUid());

                s.setMedtsindiconome(edtsindicoNome.getText().toString());
                s.setMedtsindicocpf(edtsindicoCpf.getText().toString());
                s.setMedtsindicorg(edtsindicoRg.getText().toString());
                s.setMedtsindicotelefone(edtsindicoTelefone.getText().toString());
                s.setMedtsindicoemail(edtsindicoEmail.getText().toString());
                s.setMedtsindicosenha(edtsindicoSenha.getText().toString());
                s.setMedtsindicoinicio(edtsindicoInicio.getText().toString());
                s.setMedtsindicofinal(edtsindicoFinal.getText().toString());

                databaseReference.child("sindico").child(s.getUid()).removeValue();
                limparCampos();
            }
        });
    }

    private void limparCampos() {

        edtsindicoNome.setText("");
        edtsindicoCpf.setText("");
        edtsindicoRg.setText("");
        edtsindicoTelefone.setText("");
        edtsindicoEmail.setText("");
        edtsindicoSenha.setText("");
        edtsindicoInicio.setText("");
        edtsindicoFinal.setText("");
    }

    private void sairapp() {

        AlertDialog.Builder builder = new AlertDialog.Builder(cadastrodesindico.this);
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
        inflater.inflate(R.menu.menusindico, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){

            case R.id.menusindicovoltar:
                Intent svoltar = new Intent(cadastrodesindico.this,inicial.class);
                startActivity(svoltar);
                return (true);

            case R.id.menusindicosair:
                sairapp();
        }

        return (super.onOptionsItemSelected(item));
    }
}
