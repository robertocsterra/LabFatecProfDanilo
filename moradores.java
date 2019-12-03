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
import modelo.modelomoradores;

public class moradores extends AppCompatActivity {

    private Button btnmoradoresCadastrar, btnmoradoresAlterar, btnmoradoresExcluir;
    private EditText uid, edtmoradoresNome, edtmoradoresCpf, edtmoradoresRg, edtmoradoresTelefone, edtmoradoresEmail, edtmoradoresSenha, edtmoradoresEndereco, edtmoradoresBloco, edtmoradoresApto, edtmoradoresBairro, edtmoradoresCidade;
    private ListView listviewmostraMoradores;

    private List<modelomoradores> modelomoradoresList = new ArrayList<>();
    private ArrayAdapter<modelomoradores> modelomoradoresArrayAdapter;

    final private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference("server");

    modelomoradores moradorSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moradores);

        iniciarFirebase();
        carregaWidgets();
        eventoDatabase();
        botoes();

        listviewmostraMoradores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long uid) {
                moradorSelecionado = (modelomoradores) parent.getItemAtPosition(position);

                edtmoradoresNome.setText(moradorSelecionado.getMedtmoradoresnome());
                edtmoradoresCpf.setText(moradorSelecionado.getMedtmoradorescpf());
                edtmoradoresRg.setText(moradorSelecionado.getMedtmoradoresrg());
                edtmoradoresTelefone.setText(moradorSelecionado.getMedtmoradorestelefone());
                edtmoradoresEmail.setText(moradorSelecionado.getMedtmoradoresemail());
                edtmoradoresSenha.setText(moradorSelecionado.getMedtmoradoressenha());
                edtmoradoresEndereco.setText(moradorSelecionado.getMedtmoradoresendereco());
                edtmoradoresBloco.setText(moradorSelecionado.getMedtmoradoresbloco());
                edtmoradoresApto.setText(moradorSelecionado.getMedtmoradoresapto());
                edtmoradoresBairro.setText(moradorSelecionado.getMedtmoradoresbairro());
                edtmoradoresCidade.setText(moradorSelecionado.getMedtmoradorescidade());
            }
        });

    }

    private void carregaWidgets() {
        edtmoradoresNome = (EditText) findViewById(R.id.edtmoradoresnome);
        edtmoradoresCpf = (EditText) findViewById(R.id.edtmoradorescpf);
        edtmoradoresRg = (EditText) findViewById(R.id.edtmoradoresrg);
        edtmoradoresTelefone = (EditText) findViewById(R.id.edtmoradorestelefone);
        edtmoradoresEmail = (EditText) findViewById(R.id.edtmoradoresemail);
        edtmoradoresSenha = (EditText) findViewById(R.id.edtmoradoressenha);
        edtmoradoresEndereco = (EditText) findViewById(R.id.edtmoradoresendereco);
        edtmoradoresBloco = (EditText) findViewById(R.id.edtmoradoresbloco);
        edtmoradoresApto = (EditText) findViewById(R.id.edtmoradoresapto);
        edtmoradoresBairro = (EditText) findViewById(R.id.edtmoradoresbairro);
        edtmoradoresCidade = (EditText) findViewById(R.id.edtmoradorescidade);
        btnmoradoresCadastrar = (Button) findViewById(R.id.btnmoradorescadastrar);
        btnmoradoresAlterar = (Button) findViewById(R.id.btnmoradoresalterar);
        btnmoradoresExcluir = (Button) findViewById(R.id.btnmoradoresexcluir);
        listviewmostraMoradores = (ListView) findViewById(R.id.listviewmostramoradores);
    }

    private void iniciarFirebase()
    {
        FirebaseApp.initializeApp(moradores.this);
    }

    private void eventoDatabase() {
        databaseReference.child("moradores").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                modelomoradoresList.clear();
                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    modelomoradores m = objSnapshot.getValue(modelomoradores.class);
                    modelomoradoresList.add(m);
                }
                modelomoradoresArrayAdapter = new ArrayAdapter<modelomoradores>(moradores.this,
                        android.R.layout.simple_list_item_1, modelomoradoresList);
                listviewmostraMoradores.setAdapter(modelomoradoresArrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void botoes() {

        btnmoradoresCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modelomoradores m = new modelomoradores();

                m.setUid(UUID.randomUUID().toString());

                m.setMedtmoradoresnome(edtmoradoresNome.getText().toString());
                m.setMedtmoradorescpf(edtmoradoresCpf.getText().toString());
                m.setMedtmoradoresrg(edtmoradoresRg.getText().toString());
                m.setMedtmoradorestelefone(edtmoradoresTelefone.getText().toString());
                m.setMedtmoradoresemail(edtmoradoresEmail.getText().toString());
                m.setMedtmoradoressenha(edtmoradoresSenha.getText().toString());
                m.setMedtmoradoresendereco(edtmoradoresEndereco.getText().toString());
                m.setMedtmoradoresbloco(edtmoradoresBloco.getText().toString());
                m.setMedtmoradoresapto(edtmoradoresApto.getText().toString());
                m.setMedtmoradoresbairro(edtmoradoresBairro.getText().toString());
                m.setMedtmoradorescidade(edtmoradoresCidade.getText().toString());

                databaseReference.child("moradores").child(m.getUid()).setValue(m);
                limparCampos();
            }
        });

        btnmoradoresAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modelomoradores m = new modelomoradores();

                m.setUid(moradorSelecionado.getUid());

                m.setMedtmoradoresnome(edtmoradoresNome.getText().toString());
                m.setMedtmoradorescpf(edtmoradoresCpf.getText().toString());
                m.setMedtmoradoresrg(edtmoradoresRg.getText().toString());
                m.setMedtmoradorestelefone(edtmoradoresTelefone.getText().toString());
                m.setMedtmoradoresemail(edtmoradoresEmail.getText().toString());
                m.setMedtmoradoressenha(edtmoradoresSenha.getText().toString());
                m.setMedtmoradoresendereco(edtmoradoresEndereco.getText().toString());
                m.setMedtmoradoresbloco(edtmoradoresBloco.getText().toString());
                m.setMedtmoradoresapto(edtmoradoresApto.getText().toString());
                m.setMedtmoradoresbairro(edtmoradoresBairro.getText().toString());
                m.setMedtmoradorescidade(edtmoradoresCidade.getText().toString());

                databaseReference.child("moradores").child(m.getUid()).setValue(m);
                limparCampos();
            }
        });

        btnmoradoresExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modelomoradores m = new modelomoradores();

                m.setUid(moradorSelecionado.getUid());

                m.setMedtmoradoresnome(edtmoradoresNome.getText().toString());
                m.setMedtmoradorescpf(edtmoradoresCpf.getText().toString());
                m.setMedtmoradoresrg(edtmoradoresRg.getText().toString());
                m.setMedtmoradorestelefone(edtmoradoresTelefone.getText().toString());
                m.setMedtmoradoresemail(edtmoradoresEmail.getText().toString());
                m.setMedtmoradoressenha(edtmoradoresSenha.getText().toString());
                m.setMedtmoradoresendereco(edtmoradoresEndereco.getText().toString());
                m.setMedtmoradoresbloco(edtmoradoresBloco.getText().toString());
                m.setMedtmoradoresapto(edtmoradoresApto.getText().toString());
                m.setMedtmoradoresbairro(edtmoradoresBairro.getText().toString());
                m.setMedtmoradorescidade(edtmoradoresCidade.getText().toString());

                databaseReference.child("moradores").child(m.getUid()).removeValue();
                limparCampos();
            }
        });
    }

    private void limparCampos() {

        edtmoradoresNome.setText("");
        edtmoradoresCpf.setText("");
        edtmoradoresRg.setText("");
        edtmoradoresTelefone.setText("");
        edtmoradoresEmail.setText("");
        edtmoradoresSenha.setText("");
        edtmoradoresEndereco.setText("");
        edtmoradoresBloco.setText("");
        edtmoradoresApto.setText("");
        edtmoradoresBairro.setText("");
        edtmoradoresCidade.setText("");
    }

    private void sairapp() {

        AlertDialog.Builder builder = new AlertDialog.Builder(moradores.this);
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
        inflater.inflate(R.menu.menumoradores, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){

            case R.id.menumoradoresvoltar:
                Intent movoltar = new Intent(moradores.this,inicial.class);
                startActivity(movoltar);
                return (true);

            case R.id.menumoradoressair:
                sairapp();
        }

        return (super.onOptionsItemSelected(item));
    }
}
