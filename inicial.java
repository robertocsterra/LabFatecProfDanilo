package com.example.admcondominio;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

//import static com.example.admcondominio.R.id.menucadastrovoltar;

public class inicial extends AppCompatActivity {

    // METODO SAIR


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicial);
    }

    private void sairapp() {

        AlertDialog.Builder builder = new AlertDialog.Builder(inicial.this);
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

    // ACTION BAR

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuinicial, menu);
        return (true);
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            // MENU INICIAL

           // case R.id.menusindico:
            //    return (true);

            case R.id.menumorador:
                return (true);

            case R.id.menuinicialsair:
                sairapp();

                /*

                // MENU SINDICO

            case R.id.menumovimentacao:
                return (true);

            case R.id.menucomunicados:
                return (true);

            case R.id.menucadastros:
                return (true);

            case R.id.menusindicovoltar:
                Intent sindicov = new Intent(inicial.this,inicial.class);
                startActivity(sindicov);
                return (true);

            case R.id.menusindicosair:
                sairapp();

                // MENU MOVIMENTAÇÕES

            case R.id.menumovimentacaodespesas:
                Intent lancadespesas = new Intent(inicial.this,lancadespesas.class);
                startActivity(lancadespesas);
                return (true);

            case R.id.menumovimentacaoperiodo:
                Intent infoperiodo = new Intent(inicial.this, periodo.class);
                startActivity(infoperiodo);
                return (true);


            case R.id.menumovimentacaoemiteboleto:
                Intent emitirboleto = new Intent(inicial.this,boleto.class);
                startActivity(emitirboleto);
                return (true);

            case R.id.menumovimentacaovoltar:
                Intent movimentacaov = new Intent(inicial.this,inicial.class);
                startActivity(movimentacaov);
                return (true);

            case R.id.menumovimentacaosair:
                sairapp();

                // MENU COMUNICADOS

            case R.id.menucomunicadosalimentamural:
                Intent alimentarmural = new Intent(inicial.this,alimentamural.class);
                startActivity(alimentarmural);
                return (true);

            case R.id.menucomunicadoslerrecados:
                Intent lerrecado = new Intent(inicial.this,lerrecados.class);
                startActivity(lerrecado);
                return (true);

            case R.id.menucomunicadosvoltar:
                Intent comunicadosv = new Intent(inicial.this,inicial.class);
                startActivity(comunicadosv);
                 return (true);

            case R.id.menucomunicadossair:
                sairapp();

                // MENU CADASTROS

            case R.id.menucadastrofornecedores:
                Intent fornecedores = new Intent(inicial.this,fornecedores.class);
                startActivity(fornecedores);
                return (true);

            case R.id.menucadastromoradores:
                Intent moradores = new Intent(inicial.this,moradores.class);
                startActivity(moradores);
                return (true);

            case R.id.menucadastrosindico:
                Intent sindicoo = new Intent(inicial.this,cadastrodesindico.class);
                startActivity(sindicoo);
                return (true);

            case R.id.menucadastrocondominio:
                Intent condominio = new Intent(inicial.this,cadastrodecondominio.class);
                startActivity(condominio);
                return (true);

            case R.id.menucadastromoradia:
                Intent moradia = new Intent(inicial.this,moradias.class);
                startActivity(moradia);
                return (true);

            case menucadastrovoltar:
                Intent cadastrov = new Intent(inicial.this,inicial.class);
                startActivity(cadastrov);
                return (true);

            case R.id.menucadastrosair:
                sairapp();


                 */

                // MENU MORADOR

            case R.id.menumoradorbalanco:
                Intent historico = new Intent(inicial.this,balanco.class);
                startActivity(historico);
                return (true);

            case R.id.menumoradorvboleto:
                Intent verboleto = new Intent(inicial.this,vboleto.class);
                startActivity(verboleto);
                return (true);

            case R.id.menumoradorvnoticias:
                Intent munoticias = new Intent(inicial.this,vnoticias.class);
                startActivity(munoticias);
                return (true);

            case R.id.menumoradorrecados:
                Intent editrecados = new Intent(inicial.this,recados.class);
                startActivity(editrecados);
                return (true);

            case R.id.menumoradorvoltar:
                Intent moradorv = new Intent(inicial.this,inicial.class);
                startActivity(moradorv);
                return (true);

            case R.id.menumoradorsair:
                sairapp();
        }

        return (super.onOptionsItemSelected(item));
    }
}
