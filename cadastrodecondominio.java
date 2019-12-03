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

import modelo.modelocondominio;

public class cadastrodecondominio extends AppCompatActivity {
    private Button btncondCadastrar, btncondAlterar, btncondExcluir;
    private EditText uid, edtcondNome, edtcondCnpj, edtcondCpfresp, edtcondTelefone, edtcondEmail, edtcondEndereco, edtcondComplemento, edtcondBairro, edtcondCidade, edtcondCep;
    private ListView listviewmostraCondominio;

    private List<modelocondominio> modelocondominioList = new ArrayList<>();
    private ArrayAdapter<modelocondominio> modelocondominioArrayAdapter;

    final private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference("server");

    modelocondominio condominioSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrodecondominio);

        iniciarFirebase();
        carregaWidgets();
        eventoDatabase();
        botoes();

        listviewmostraCondominio.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long uid) {
                condominioSelecionado = (modelocondominio) parent.getItemAtPosition(position);

                edtcondNome.setText(condominioSelecionado.getMedtcondnome());
                edtcondCnpj.setText(condominioSelecionado.getMedtcondcnpj());
                edtcondCpfresp.setText(condominioSelecionado.getMedtcondcpfresp());
                edtcondTelefone.setText(condominioSelecionado.getMedtcondtelefone());
                edtcondEmail.setText(condominioSelecionado.getMedtcondemail());
                edtcondEndereco.setText(condominioSelecionado.getMedtcondendereco());
                edtcondComplemento.setText(condominioSelecionado.getMedtcondcomplemento());
                edtcondBairro.setText(condominioSelecionado.getMedtcondbairro());
                edtcondCidade.setText(condominioSelecionado.getMedtcondcidade());
                edtcondCep.setText(condominioSelecionado.getMedtcondcep());

            }
        });
    }

    private void carregaWidgets() {
        edtcondNome = (EditText) findViewById(R.id.edtcondnome);
        edtcondCnpj = (EditText) findViewById(R.id.edtcondcnpj);
        edtcondCpfresp = (EditText) findViewById(R.id.edtcondcpfresp);
        edtcondTelefone = (EditText) findViewById(R.id.edtcondtelefone);
        edtcondEmail = (EditText) findViewById(R.id.edtcondemail);
        edtcondEndereco = (EditText) findViewById(R.id.edtcondendereco);
        edtcondComplemento = (EditText) findViewById(R.id.edtcondcomplemento);
        edtcondBairro = (EditText) findViewById(R.id.edtcondbairro);
        edtcondCidade = (EditText) findViewById(R.id.edtcondcidade);
        edtcondCep = (EditText) findViewById(R.id.edtcondcep);
        btncondCadastrar= (Button) findViewById(R.id.btncondcadastrar);
        btncondAlterar = (Button) findViewById(R.id.btncondalterar);
        btncondExcluir = (Button) findViewById(R.id.btncondexcluir);
        listviewmostraCondominio = (ListView) findViewById(R.id.listviewmostracondominio);
    }

    private void iniciarFirebase()
    {
        FirebaseApp.initializeApp(cadastrodecondominio.this);
    }

    private void eventoDatabase() {
        databaseReference.child("condominio").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                modelocondominioList.clear();
                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    modelocondominio c = objSnapshot.getValue(modelocondominio.class);
                    modelocondominioList.add(c);
                }
                modelocondominioArrayAdapter = new ArrayAdapter<modelocondominio>(cadastrodecondominio.this,
                        android.R.layout.simple_list_item_1, modelocondominioList);
                listviewmostraCondominio.setAdapter(modelocondominioArrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void botoes() {

        btncondCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modelocondominio c = new modelocondominio();

                c.setUid(UUID.randomUUID().toString());

                c.setMedtcondnome(edtcondNome.getText().toString());
                c.setMedtcondcnpj(edtcondCnpj.getText().toString());
                c.setMedtcondcpfresp(edtcondCpfresp.getText().toString());
                c.setMedtcondtelefone(edtcondTelefone.getText().toString());
                c.setMedtcondemail(edtcondEmail.getText().toString());
                c.setMedtcondendereco(edtcondEndereco.getText().toString());
                c.setMedtcondcomplemento(edtcondComplemento.getText().toString());
                c.setMedtcondbairro(edtcondBairro.getText().toString());
                c.setMedtcondcidade(edtcondCidade.getText().toString());
                c.setMedtcondcep(edtcondCep.getText().toString());

                databaseReference.child("condominio").child(c.getUid()).setValue(c);
                limparCampos();
            }
        });

        btncondAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modelocondominio c = new modelocondominio();

                c.setUid(condominioSelecionado.getUid());

                c.setMedtcondnome(edtcondNome.getText().toString());
                c.setMedtcondcnpj(edtcondCnpj.getText().toString());
                c.setMedtcondcpfresp(edtcondCpfresp.getText().toString());
                c.setMedtcondtelefone(edtcondTelefone.getText().toString());
                c.setMedtcondemail(edtcondEmail.getText().toString());
                c.setMedtcondendereco(edtcondEndereco.getText().toString());
                c.setMedtcondcomplemento(edtcondComplemento.getText().toString());
                c.setMedtcondbairro(edtcondBairro.getText().toString());
                c.setMedtcondcidade(edtcondCidade.getText().toString());
                c.setMedtcondcep(edtcondCep.getText().toString());

                databaseReference.child("condominio").child(c.getUid()).setValue(c);
                limparCampos();
            }
        });

        btncondExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modelocondominio c = new modelocondominio();

                c.setUid(condominioSelecionado.getUid());

                c.setMedtcondnome(edtcondNome.getText().toString());
                c.setMedtcondcnpj(edtcondCnpj.getText().toString());
                c.setMedtcondcpfresp(edtcondCpfresp.getText().toString());
                c.setMedtcondtelefone(edtcondTelefone.getText().toString());
                c.setMedtcondemail(edtcondEmail.getText().toString());
                c.setMedtcondendereco(edtcondEndereco.getText().toString());
                c.setMedtcondcomplemento(edtcondComplemento.getText().toString());
                c.setMedtcondbairro(edtcondBairro.getText().toString());
                c.setMedtcondcidade(edtcondCidade.getText().toString());
                c.setMedtcondcep(edtcondCep.getText().toString());

                databaseReference.child("condominio").child(c.getUid()).removeValue();
                limparCampos();
            }
        });
    }

    private void limparCampos() {

        edtcondNome.setText("");
        edtcondCnpj.setText("");
        edtcondCpfresp.setText("");
        edtcondTelefone.setText("");
        edtcondEmail.setText("");
        edtcondEndereco.setText("");
        edtcondComplemento.setText("");
        edtcondBairro.setText("");
        edtcondCidade.setText("");
        edtcondCep.setText("");
    }

    private void sairapp() {

        AlertDialog.Builder builder = new AlertDialog.Builder(cadastrodecondominio.this);
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
        inflater.inflate(R.menu.menucondominio, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){

            case R.id.menucondominiovoltar:
                Intent cvoltar = new Intent(cadastrodecondominio.this,inicial.class);
                startActivity(cvoltar);
                return (true);

            case R.id.menucondominiosair:
                sairapp();
        }

        return (super.onOptionsItemSelected(item));
    }
}
