package modelo;

public class modeloalimentamural {
    private String uid;
    private String Medtmuraltitulo;
    private String Medtmuraldata;
    private String Medtmuraltextodanoticia;

    /*

    public modeloalimentamural(String uid, String medtmuraltitulo, String medtmuraldata, String medtmuraltextodanoticia) {
        this.uid = uid;
        Medtmuraltitulo = medtmuraltitulo;
        Medtmuraldata = medtmuraldata;
        Medtmuraltextodanoticia = medtmuraltextodanoticia;
    }

     */

    public modeloalimentamural() {

    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMedtmuraltitulo() {
        return Medtmuraltitulo;
    }

    public void setMedtmuraltitulo(String medtmuraltitulo) {
        Medtmuraltitulo = medtmuraltitulo;
    }

    public String getMedtmuraldata() {
        return Medtmuraldata;
    }

    public void setMedtmuraldata(String medtmuraldata) {
        Medtmuraldata = medtmuraldata;
    }

    public String getMedtmuraltextodanoticia() {
        return Medtmuraltextodanoticia;
    }

    public void setMedtmuraltextodanoticia(String medtmuraltextodanoticia) {
        Medtmuraltextodanoticia = medtmuraltextodanoticia;
    }

    @Override
    public String toString() {
        return  "  Titulo:  " + Medtmuraltitulo +
                "  Data:  " + Medtmuraldata +
                "  Notícia:  " + Medtmuraltextodanoticia;
    }
}