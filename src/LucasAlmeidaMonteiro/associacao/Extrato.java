package LucasAlmeidaMonteiro.associacao;

import java.util.Date;

public class Extrato {
    // melhor criar um novo vetor que contém a classe Extrato de pagamento
    // tendo ela o que pagou "indice da taxa ou nome da taxa",quem string do associado, e quando pagou data.
    // e quanto pagou, e a tag da taxa específica do tipo de taxa
    String NomeTaxa;
    String Nomeassociado;
    int Numeroassociado;
    double Valorpago;
    long datadepagamento;
    int vigenc;

    public Extrato(String nomeTaxa, String nomeassociado, int numeroassociado, double valorpago, long datadepagamento,int vigencia) {
        this.NomeTaxa = nomeTaxa;
        this.Nomeassociado = nomeassociado;
        this.Numeroassociado = numeroassociado;
        this.Valorpago = valorpago;
        this.datadepagamento = datadepagamento;
        this.vigenc =vigencia;
    }
    protected Extrato(){

    }

    public String getNomeTaxa() {
        return NomeTaxa;
    }

    protected void setNomeTaxa(String nomeTaxa) {
        NomeTaxa = nomeTaxa;
    }

    public String getNomeassociado() {
        return Nomeassociado;
    }

    protected void setNomeassociado(String nomeassociado) {
        Nomeassociado = nomeassociado;
    }

    public int getNumeroassociado() {
        return Numeroassociado;
    }

    protected void setNumeroassociado(int numeroassociado) {
        Numeroassociado = numeroassociado;
    }

    public double getValorpago() {
        return Valorpago;
    }

    protected void setValorpago(double valorpago) {
        Valorpago = valorpago;
    }

    public long getDatadepagamento() {
        return datadepagamento;
    }

    protected void setDatadepagamento(long datadepagamento) {
        this.datadepagamento = datadepagamento;
    }

    public int getVigenc() {
        return vigenc;
    }

    protected void setVigenc(int vigenc) {
        this.vigenc = vigenc;
    }
}
