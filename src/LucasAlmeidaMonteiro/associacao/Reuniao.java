package LucasAlmeidaMonteiro.associacao;

import java.util.ArrayList;
import java.util.Date;
public class Reuniao {//é só ir salvando as tag de cada um
    long data;
    String ata;
    private ArrayList<String> Frequencia = new ArrayList<>();// é aqui mesmo a frequência
    int a =0;
    public Reuniao(long data, String ata) throws ValorInvalido {
        if(data > 0 && !(ata.trim().isEmpty())){
        this.data = data;
        this.ata = ata;
        }else {
            throw new ValorInvalido();// depois eu vou tirar esse exception pq se não n passa nos teste do prof
        }
    }
    public Reuniao(){

    }

    //public Reuniao(long dataReuni, String aumentoDeTaxas) {
    //}

    public void adicionaFrequencia(String s){// mandar uma string do nome mais P(presen)ou F de falta.
          Frequencia.add(s);
    }
    public long getData() {
        return data;
    }

    protected void setData(long data) {
        this.data = data;
    }

    public String getAta() {
        return ata;
    }

    protected void setAta(String ata) {
        this.ata = ata;
    }

    public int getA() {
        return a;
    }

    protected void setA(int a) {
        this.a = a;
    }

    public ArrayList<String> getFrequencia() {
        return Frequencia;
    }

    protected void setFrequencia(ArrayList<String> frequencia) {
        Frequencia = frequencia;
    }
}
