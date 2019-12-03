package modelo;

import android.widget.Spinner;

public class modelolancadespesas {
    private String uid;
    private String Medtldespesasfornecedor;
    private String Medttipodespesa;
    private String Medtldespesasdata;
    private String Medtldespesasdescricao;
    private String Medtldespesasvalor;
    private String Medtldespesasobs;

    /*

    public modelolancadespesas(String uid, String medtldespesasfornecedor, String medttipodespesa, String medtldespesasdata, String medtldespesasdescricao, String medtldespesasvalor, String medtldespesasobs) {
        this.uid = uid;
        Medtldespesasfornecedor = medtldespesasfornecedor;
        Medttipodespesa = medttipodespesa;
        Medtldespesasdata = medtldespesasdata;
        Medtldespesasdescricao = medtldespesasdescricao;
        Medtldespesasvalor = medtldespesasvalor;
        Medtldespesasobs = medtldespesasobs;
    }

     */

    public modelolancadespesas() {

    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMedtldespesasfornecedor() {
        return Medtldespesasfornecedor;
    }

    public void setMedtldespesasfornecedor(String medtldespesasfornecedor) {
        Medtldespesasfornecedor = medtldespesasfornecedor;
    }

    public String getMedttipodespesa() {
        return Medttipodespesa;
    }

    public void setMedttipodespesa(String medttipodespesa) {
        Medttipodespesa = medttipodespesa;
    }

    public String getMedtldespesasdata() {
        return Medtldespesasdata;
    }

    public void setMedtldespesasdata(String medtldespesasdata) {
        Medtldespesasdata = medtldespesasdata;
    }

    public String getMedtldespesasdescricao() {
        return Medtldespesasdescricao;
    }

    public void setMedtldespesasdescricao(String medtldespesasdescricao) {
        Medtldespesasdescricao = medtldespesasdescricao;
    }

    public String getMedtldespesasvalor() {
        return Medtldespesasvalor;
    }

    public void setMedtldespesasvalor(String medtldespesasvalor) {
        Medtldespesasvalor = medtldespesasvalor;
    }

    public String getMedtldespesasobs() {
        return Medtldespesasobs;
    }

    public void setMedtldespesasobs(String medtldespesasobs) {
        Medtldespesasobs = medtldespesasobs;
    }

    @Override
    public String toString() {
        return  "  Fornecedor:  " + Medtldespesasfornecedor +
                "  Tipo Despesa:  "   + Medttipodespesa   +
                "  Data:  " + Medtldespesasdata  +
                "  Descrição:  " + Medtldespesasdescricao +
                "  Valor:  "   + Medtldespesasvalor   +
                "  Observação:  " + Medtldespesasobs;
    }
}
