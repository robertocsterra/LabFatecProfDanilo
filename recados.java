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
import modelo.modelorecados;

public class recados extends AppCompatActivity {

    private Button btnrecadosEnviar;
    private EditText uid, edtrecadosTitulo, edtrecadosData, edtrecadosTexto;
    private ListView listviewmostraRecados;

    private List<modelorecados> modelorecadosList = new ArrayList<>();
    private ArrayAdapter<modelorecados> modelorecadosArrayAdapter;

    final private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference("server");

    modelorecados recadoSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recados);

        iniciarFirebase();
        carregaWidgets();
        eventoDatabase();
        botoes();

        listviewmostraRecados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long uid) {
                recadoSelecionado = (modelorecados) parent.getItemAtPosition(position);

                edtrecadosTitulo.setText(recadoSelecionado.getMedtrecadostitulo());
                edtrecadosData.setText(recadoSelecionado.getMedtrecadosdata());
                edtrecadosTexto.setText(recadoSelecionado.getMedtrecadostexto());
            }
        });

    }

    private void carregaWidgets() {
        edtrecadosTitulo = (EditText) findViewById(R.id.edtrecadostitulo);
        edtrecadosData = (EditText) findViewById(R.id.edtrecadosdata);
        edtrecadosTexto = (EditText) findViewById(R.id.edtrecadostexto);
        btnrecadosEnviar = (Button) findViewById(R.id.btnrecadosenviar);
        listviewmostraRecados = (ListView) findViewById(R.id.listviewmostrarecados);
    }

    private void iniciarFirebase()
    {
        FirebaseApp.initializeApp(recados.this);
    }

    private void eventoDatabase() {
        databaseReference.child("recados").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                modelorecadosList.clear();
                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    modelorecados r = objSnapshot.getValue(modelorecados.class);
                    modelorecadosList.add(r);
                }
                modelorecadosArrayAdapter = new ArrayAdapter<modelorecados>(recados.this,
                        android.R.layout.simple_list_item_1, modelorecadosList);
                listviewmostraRecados.setAdapter(modelorecadosArrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void botoes() {

        btnrecadosEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modelorecados r = new modelorecados();

                r.setUid(UUID.randomUUID().toString());

                r.setMedtrecadostitulo(edtrecadosTitulo.getText().toString());
                r.setMedtrecadosdata(edtrecadosData.getText().toString());
                r.setMedtrecadostexto(edtrecadosTexto.getText().toString());

                databaseReference.child("recados").child(r.getUid()).setValue(r);
                limparCampos();
            }
        });
    }

    private void limparCampos() {

        edtrecadosTitulo.setText("");
        edtrecadosData.setText("");
        edtrecadosTexto.setText("");
    }

    private void sairapp() {

        AlertDialog.Builder builder = new AlertDialog.Builder(recados.this);
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
        inflater.inflate(R.menu.menurecados, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){

            case R.id.menurecadosvoltar:
                Intent recvoltar = new Intent(recados.this,inicial.class);
                startActivity(recvoltar);
                return (true);

            case R.id.menurecadossair:
                sairapp();
        }

        return (super.onOptionsItemSelected(item));
    }
}
