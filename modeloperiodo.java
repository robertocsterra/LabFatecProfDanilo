package modelo;

public class modeloperiodo {
    private  String uid;
    private  String medtperiodonome;
    private  String medtperiodoano;
    private  String medtperiodoinicio;
    private  String medtperiodofinal;

    /*

    public modeloperiodo(String uid, String medtperiodonome, String medtperiodoano, String medtperiodoinicio, String medtperiodofinal) {
        this.uid = uid;
        this.medtperiodonome = medtperiodonome;
        this.medtperiodoano = medtperiodoano;
        this.medtperiodoinicio = medtperiodoinicio;
        this.medtperiodofinal = medtperiodofinal;
    }

    */

    public modeloperiodo() {

    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMedtperiodonome() {
        return medtperiodonome;
    }

    public void setMedtperiodonome(String medtperiodonome) {
        this.medtperiodonome = medtperiodonome;
    }

    public String getMedtperiodoano() {
        return medtperiodoano;
    }

    public void setMedtperiodoano(String medtperiodoano) {
        this.medtperiodoano = medtperiodoano;
    }

    public String getMedtperiodoinicio() {
        return medtperiodoinicio;
    }

    public void setMedtperiodoinicio(String medtperiodoinicio) {
        this.medtperiodoinicio = medtperiodoinicio;
    }

    public String getMedtperiodofinal() {
        return medtperiodofinal;
    }

    public void setMedtperiodofinal(String medtperiodofinal) {
        this.medtperiodofinal = medtperiodofinal;
    }

    @Override
    public String toString() {
        return medtperiodonome;
    }
}


