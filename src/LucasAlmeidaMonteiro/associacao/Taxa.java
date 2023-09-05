package LucasAlmeidaMonteiro.associacao;

public class Taxa {
    String nome;//falta validar e declarar como protected
    int vigencia;
    double valorAno;
    int parcelas;
    boolean administrativa = false;
    boolean social = false;
    public Taxa(String nome, int vigencia, double valorAno, int parcelas, boolean administrativa) throws ValorInvalido {
        if(!(nome.trim().isEmpty())&& vigencia>0&&valorAno>0&&parcelas>0) {
            this.nome = nome;
            this.vigencia = vigencia;
            this.valorAno = valorAno;
            this.parcelas = parcelas;
            if (administrativa) {
                this.administrativa = true;
            } else {
                this.social = true;
            }
        }else {
            throw new ValorInvalido();//talvez eu deva tirar o valor inválido daqui não sei como tá o teste do prof
        }
    }

    public double ValorMensal(){
        double Mensal;
        Mensal =  valorAno/parcelas;
        return Mensal;
    }

    public String getNome() {
        return nome;
    }

    protected void setNome(String nome) {
        this.nome = nome;
    }

    public int getVigencia() {
        return vigencia;
    }

    protected void setVigencia(int vigencia) {
        this.vigencia = vigencia;
    }

    public double getValorAno() {
        return valorAno;
    }

    protected void setValorAno(double valorAno) {
        this.valorAno = valorAno;
    }

    public int getParcelas() {
        return parcelas;
    }

    protected void setParcelas(int parcelas) {
        this.parcelas = parcelas;
    }

    public boolean isAdministrativa() {
        return administrativa;
    }

    protected void setAdministrativa(boolean administrativa) {
        this.administrativa = administrativa;
    }

    public boolean isSocial() {
        return social;
    }

    protected void setSocial(boolean social) {
        this.social = social;
    }
}
