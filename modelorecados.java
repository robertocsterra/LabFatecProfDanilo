package modelo;

public class modelorecados {
    private String uid;
    private String Medtrecadostitulo;
    private String Medtrecadosdata;
    private String Medtrecadostexto;

    /*

    public modelorecados(String uid, String medtrecadostitulo, String medtrecadosdata, String medtrecadostexto) {
        this.uid = uid;
        Medtrecadostitulo = medtrecadostitulo;
        Medtrecadosdata = medtrecadosdata;
        Medtrecadostexto = medtrecadostexto;
    }

     */

    public modelorecados() {

    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMedtrecadostitulo() {
        return Medtrecadostitulo;
    }

    public void setMedtrecadostitulo(String medtrecadostitulo) {
        Medtrecadostitulo = medtrecadostitulo;
    }

    public String getMedtrecadosdata() {
        return Medtrecadosdata;
    }

    public void setMedtrecadosdata(String medtrecadosdata) {
        Medtrecadosdata = medtrecadosdata;
    }

    public String getMedtrecadostexto() {
        return Medtrecadostexto;
    }

    public void setMedtrecadostexto(String medtrecadostexto) {
        Medtrecadostexto = medtrecadostexto;
    }

    @Override
    public String toString() {
        return  "  Titulo:  " + Medtrecadostitulo +
                "  Data:  " + Medtrecadosdata +
                "  Recado:  " + Medtrecadostexto;
    }
}

