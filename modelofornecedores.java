package modelo;

public class modelofornecedores {
    private String uid;
    private String Medtfornome;
    private String Medtforcnpj;
    private String Medtforcpf;
    private String Medtfortelefone;
    private String Medtforemail;
    private String Medtforseguimento;

    /*

    public modelofornecedores(String uid, String medtfornome, String medtforcnpj, String medtforcpf, String medtfortelefone, String medtforemail, String medtforseguimento) {
        this.uid = uid;
        Medtfornome = medtfornome;
        Medtforcnpj = medtforcnpj;
        Medtforcpf = medtforcpf;
        Medtfortelefone = medtfortelefone;
        Medtforemail = medtforemail;
        Medtforseguimento = medtforseguimento;
    }

     */

    public modelofornecedores() {

    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMedtfornome() {
        return Medtfornome;
    }

    public void setMedtfornome(String medtfornome) {
        Medtfornome = medtfornome;
    }

    public String getMedtforcnpj() {
        return Medtforcnpj;
    }

    public void setMedtforcnpj(String medtforcnpj) {
        Medtforcnpj = medtforcnpj;
    }

    public String getMedtforcpf() {
        return Medtforcpf;
    }

    public void setMedtforcpf(String medtforcpf) {
        Medtforcpf = medtforcpf;
    }

    public String getMedtfortelefone() {
        return Medtfortelefone;
    }

    public void setMedtfortelefone(String medtfortelefone) {
        Medtfortelefone = medtfortelefone;
    }

    public String getMedtforemail() {
        return Medtforemail;
    }

    public void setMedtforemail(String medtforemail) {
        Medtforemail = medtforemail;
    }

    public String getMedtforseguimento() {
        return Medtforseguimento;
    }

    public void setMedtforseguimento(String medtforseguimento) {
        Medtforseguimento = medtforseguimento;
    }

    @Override
    public String toString() {
        return Medtfornome;
    }
}

