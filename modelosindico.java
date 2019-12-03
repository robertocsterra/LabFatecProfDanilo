package modelo;

public class modelosindico {
    private String uid;
    private String Medtsindiconome;
    private String Medtsindicocpf;
    private String Medtsindicorg;
    private String Medtsindicotelefone;
    private String Medtsindicoemail;
    private String Medtsindicosenha;
    private String Medtsindicoinicio;
    private String Medtsindicofinal;

    /*

    public modelosindico(String uid, String medtsindiconome, String medtsindicocpf, String medtsindicorg, String medtsindicotelefone, String medtsindicoemail, String medtsindicosenha, String medtsindicoinicio, String medtsindicofinal) {
        this.uid = uid;
        Medtsindiconome = medtsindiconome;
        Medtsindicocpf = medtsindicocpf;
        Medtsindicorg = medtsindicorg;
        Medtsindicotelefone = medtsindicotelefone;
        Medtsindicoemail = medtsindicoemail;
        Medtsindicosenha = medtsindicosenha;
        Medtsindicoinicio = medtsindicoinicio;
        Medtsindicofinal = medtsindicofinal;
    }

     */

    public modelosindico() {

    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMedtsindiconome() {
        return Medtsindiconome;
    }

    public void setMedtsindiconome(String medtsindiconome) {
        Medtsindiconome = medtsindiconome;
    }

    public String getMedtsindicocpf() {
        return Medtsindicocpf;
    }

    public void setMedtsindicocpf(String medtsindicocpf) {
        Medtsindicocpf = medtsindicocpf;
    }

    public String getMedtsindicorg() {
        return Medtsindicorg;
    }

    public void setMedtsindicorg(String medtsindicorg) {
        Medtsindicorg = medtsindicorg;
    }

    public String getMedtsindicotelefone() {
        return Medtsindicotelefone;
    }

    public void setMedtsindicotelefone(String medtsindicotelefone) {
        Medtsindicotelefone = medtsindicotelefone;
    }

    public String getMedtsindicoemail() {
        return Medtsindicoemail;
    }

    public void setMedtsindicoemail(String medtsindicoemail) {
        Medtsindicoemail = medtsindicoemail;
    }

    public String getMedtsindicosenha() {
        return Medtsindicosenha;
    }

    public void setMedtsindicosenha(String medtsindicosenha) {
        Medtsindicosenha = medtsindicosenha;
    }

    public String getMedtsindicoinicio() {
        return Medtsindicoinicio;
    }

    public void setMedtsindicoinicio(String medtsindicoinicio) {
        Medtsindicoinicio = medtsindicoinicio;
    }

    public String getMedtsindicofinal() {
        return Medtsindicofinal;
    }

    public void setMedtsindicofinal(String medtsindicofinal) {
        Medtsindicofinal = medtsindicofinal;
    }

    @Override
    public String toString() {
        return Medtsindiconome;
    }
}
