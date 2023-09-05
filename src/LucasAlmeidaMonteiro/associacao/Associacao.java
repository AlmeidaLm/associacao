package LucasAlmeidaMonteiro.associacao;

import java.util.ArrayList;
import java.util.Date;

public class Associacao {// aqui so valida a taxa e as reuniões
    int NumberAsso;// validar lá no array
    String NameAsso;// falta os associados,encontros e taxa mensal aqui
    ArrayList<Taxa> taxa = new ArrayList<>();// taxas com todas as infos
    ArrayList<Double> taxaMens =new ArrayList<>();//taxas mensais
    ArrayList<Reuniao> reunios = new ArrayList<>();
    ArrayList<Associado> associados = new ArrayList<>();
    // aqui pode ter uma variável armazenando o total das vigencias
    double totaltaxas;
    int b;
    // melhor criar um novo vetor que contém a classe Extrato de pagamento
    // tendo ela o que pagou "indice da taxa ou nome da taxa",quem string do associado, e quando pagou data.
    // e quanto pagou, e a tag da taxa específica do tipo de taxa
    ArrayList<Extrato> Extratos = new ArrayList<>();
    public Associacao(int num, String nome)  {
        this.NameAsso = nome;
        this.NumberAsso = num;

    }
    public void AdicionaExtrat(String nomeTaxa, String nomeassociado,int numeroassociado, double valorpago, long datadepagamento,int vigen){
        Extrato E = new Extrato(nomeTaxa,nomeassociado,numeroassociado,valorpago,datadepagamento,vigen);
        Extratos.add(E);
    }
    public double Somaextratos(String nomtx,int numAssocia,int vigencia){
        double valorpago = 0;
        for (Extrato e : Extratos){
            if(e.getNomeTaxa().equals(nomtx) && numAssocia == e.getNumeroassociado()&& vigencia == e.getVigenc()){
                valorpago = valorpago + e.getValorpago();
            }
        }
        return valorpago;
    }

    protected void AdicionaTaxa(Taxa t){
        //taxa aceita o mesmo nome só não a mesma vigencia
        taxaMens.add(taxa.get(b).ValorMensal());
        b++;// podia botar o próprio set pra fazer isso aqui já, pq ele atualiza o valor
        setB(b);// chamar essa função dentro do método de adicionar taxa
    }
    protected void AdicionaReuni(Reuniao r) throws ValorInvalido {// falta validar
        if(r.getData()> 0&& !(r.getAta().trim().isEmpty())){
            reunios.add(r);// fazer o mesmo para reunião
        }else {// podia botar o próprio set pra fazer isso aqui já, pq ele atualiza o valor
            throw new ValorInvalido();
        }
    }

    public int getNumberAsso() {
        return NumberAsso;
    }
    protected void setNumberAsso(int numberAsso) {
        NumberAsso = numberAsso;
    }
    public String getNameAsso() {
        return NameAsso;
    }

    protected void setNameAsso(String nameAsso) {
        NameAsso = nameAsso;
    }

    public int getB() {
        return b;
    }

    protected void setB(int b) {
        this.b = b;
    }

    public ArrayList<Associado> getAssociados() {
        return associados;
    }

    protected void setAssociados(ArrayList<Associado> associados) {
        this.associados = associados;
    }

    public ArrayList<Taxa> getTaxa() {
        return taxa;
    }

    protected void setTaxa(ArrayList<Taxa> taxa) {
        taxaMens.add(taxa.get(b).ValorMensal());// vetor de parcelas de cada taxa
        this.totaltaxas = getTotaltaxas() + taxa.get(b).getValorAno();//soma o valor das parcelas
        //adiciona na variável
        b++;//avança uma posição para a próxima taxa a ser adicionada;
        setB(b);
        this.taxa = taxa;
    }
    public ArrayList<Double> getTaxaMens() {
        return taxaMens;
    }
    protected void setTaxaMens(ArrayList<Double> taxaMens) {
        this.taxaMens = taxaMens;
    }

    public ArrayList<Reuniao> getReunios() {
        return reunios;
    }

    protected void setReunios(ArrayList<Reuniao> reunios) {// falta a verificação de
        this.reunios = reunios;
    }

    public double getTotaltaxas() {
        return totaltaxas;
    }

    protected void setTotaltaxas(double totaltaxas) {
        this.totaltaxas = totaltaxas;
    }

    public ArrayList<Extrato> getExtratos() {
        return Extratos;
    }

    protected void setExtratos(ArrayList<Extrato> extratos) {
        Extratos = extratos;
    }
}
