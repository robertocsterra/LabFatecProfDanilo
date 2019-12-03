package modelo;

public class modelomoradias {
    private String uid;
    private String Medtmoradiaproprietario;
    private String Medtmoradiacpfpro;
    private String Medtmoradiatelefonepro;
    private String Medtmoradiaendereco;
    private String Medtmoradiacomplemento;
    private String Medtmoradiaatual;
    private String Medtmoradiaobs;

    /*

    public modelomoradias(String uid, String medtmoradiaproprietario, String medtmoradiacpfpro, String medtmoradiatelefonepro, String medtmoradiaendereco, String medtmoradiacomplemento, String medtmoradiaatual, String medtmoradiaobs) {
        this.uid = uid;
        Medtmoradiaproprietario = medtmoradiaproprietario;
        Medtmoradiacpfpro = medtmoradiacpfpro;
        Medtmoradiatelefonepro = medtmoradiatelefonepro;
        Medtmoradiaendereco = medtmoradiaendereco;
        Medtmoradiacomplemento = medtmoradiacomplemento;
        Medtmoradiaatual = medtmoradiaatual;
        Medtmoradiaobs = medtmoradiaobs;
    }

     */

    public modelomoradias() {

    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMedtmoradiaproprietario() {
        return Medtmoradiaproprietario;
    }

    public void setMedtmoradiaproprietario(String medtmoradiaproprietario) {
        Medtmoradiaproprietario = medtmoradiaproprietario;
    }

    public String getMedtmoradiacpfpro() {
        return Medtmoradiacpfpro;
    }

    public void setMedtmoradiacpfpro(String medtmoradiacpfpro) {
        Medtmoradiacpfpro = medtmoradiacpfpro;
    }

    public String getMedtmoradiatelefonepro() {
        return Medtmoradiatelefonepro;
    }

    public void setMedtmoradiatelefonepro(String medtmoradiatelefonepro) {
        Medtmoradiatelefonepro = medtmoradiatelefonepro;
    }

    public String getMedtmoradiaendereco() {
        return Medtmoradiaendereco;
    }

    public void setMedtmoradiaendereco(String medtmoradiaendereco) {
        Medtmoradiaendereco = medtmoradiaendereco;
    }

    public String getMedtmoradiacomplemento() {
        return Medtmoradiacomplemento;
    }

    public void setMedtmoradiacomplemento(String medtmoradiacomplemento) {
        Medtmoradiacomplemento = medtmoradiacomplemento;
    }

    public String getMedtmoradiaatual() {
        return Medtmoradiaatual;
    }

    public void setMedtmoradiaatual(String medtmoradiaatual) {
        Medtmoradiaatual = medtmoradiaatual;
    }

    public String getMedtmoradiaobs() {
        return Medtmoradiaobs;
    }

    public void setMedtmoradiaobs(String medtmoradiaobs) {
        Medtmoradiaobs = medtmoradiaobs;
    }

    @Override
    public String toString() {
        return Medtmoradiaproprietario;
    }
}
