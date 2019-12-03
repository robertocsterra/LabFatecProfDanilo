package modelo;

public class modelocondominio {
    private String uid;
    private String Medtcondnome;
    private String Medtcondcnpj;
    private String Medtcondcpfresp;
    private String Medtcondtelefone;
    private String Medtcondemail;
    private String Medtcondendereco;
    private String Medtcondcomplemento;
    private String Medtcondbairro;
    private String Medtcondcidade;
    private String Medtcondcep;

    /*

    public modelocondominio(String uid, String medtcondnome, String medtcondcnpj, String medtcondcpfresp, String medtcondtelefone, String medtcondemail, String medtcondendereco, String medtcondcomplemento, String medtcondbairro, String medtcondcidade, String medtcondcep) {
        this.uid = uid;
        Medtcondnome = medtcondnome;
        Medtcondcnpj = medtcondcnpj;
        Medtcondcpfresp = medtcondcpfresp;
        Medtcondtelefone = medtcondtelefone;
        Medtcondemail = medtcondemail;
        Medtcondendereco = medtcondendereco;
        Medtcondcomplemento = medtcondcomplemento;
        Medtcondbairro = medtcondbairro;
        Medtcondcidade = medtcondcidade;
        Medtcondcep = medtcondcep;
    }

    */

    public modelocondominio() {

    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMedtcondnome() {
        return Medtcondnome;
    }

    public void setMedtcondnome(String medtcondnome) {
        Medtcondnome = medtcondnome;
    }

    public String getMedtcondcnpj() {
        return Medtcondcnpj;
    }

    public void setMedtcondcnpj(String medtcondcnpj) {
        Medtcondcnpj = medtcondcnpj;
    }

    public String getMedtcondcpfresp() {
        return Medtcondcpfresp;
    }

    public void setMedtcondcpfresp(String medtcondcpfresp) {
        Medtcondcpfresp = medtcondcpfresp;
    }

    public String getMedtcondtelefone() {
        return Medtcondtelefone;
    }

    public void setMedtcondtelefone(String medtcondtelefone) {
        Medtcondtelefone = medtcondtelefone;
    }

    public String getMedtcondemail() {
        return Medtcondemail;
    }

    public void setMedtcondemail(String medtcondemail) {
        Medtcondemail = medtcondemail;
    }

    public String getMedtcondendereco() {
        return Medtcondendereco;
    }

    public void setMedtcondendereco(String medtcondendereco) {
        Medtcondendereco = medtcondendereco;
    }

    public String getMedtcondcomplemento() {
        return Medtcondcomplemento;
    }

    public void setMedtcondcomplemento(String medtcondcomplemento) {
        Medtcondcomplemento = medtcondcomplemento;
    }

    public String getMedtcondbairro() {
        return Medtcondbairro;
    }

    public void setMedtcondbairro(String medtcondbairro) {
        Medtcondbairro = medtcondbairro;
    }

    public String getMedtcondcidade() {
        return Medtcondcidade;
    }

    public void setMedtcondcidade(String medtcondcidade) {
        Medtcondcidade = medtcondcidade;
    }

    public String getMedtcondcep() {
        return Medtcondcep;
    }

    public void setMedtcondcep(String medtcondcep) {
        Medtcondcep = medtcondcep;
    }

    @Override
    public String toString() {
        return Medtcondnome;
    }
}
