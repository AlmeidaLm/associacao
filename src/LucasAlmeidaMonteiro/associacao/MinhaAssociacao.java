package LucasAlmeidaMonteiro.associacao;

import java.util.ArrayList;

public class MinhaAssociacao implements InterfaceAssociacao {
    private ArrayList<Associacao> LisAssoci = new ArrayList<>();
    @Override
    public double calcularFrequencia(int numAssociado, int numAssociacao, long inicio, long fim) throws AssociadoNaoExistente, ReuniaoNaoExistente, AssociacaoNaoExistente, ValorInvalido {
        int i = 0;
        int qntreu = 0;
        boolean reu = false;
        double numpres =0;
        Associacao Ass = buscaAssociacao(numAssociacao);
        Associado Asosid = buscaAssociado(numAssociacao,numAssociado);
        if(Asosid!=null){
            ArrayList<Reuniao> R = Ass.getReunios();
            for (Reuniao reuni : R){
                if(reuni.getData() >= inicio && reuni.getData() <= fim){
                    qntreu++;
                    reu = true;
                    ArrayList<String> presenca= reuni.getFrequencia();
                    for (String s : presenca){
                        if(Asosid.getNome().equals(s)){
                            i++;
                        }
                    }
                }
            }
            if (!reu){
               throw new  ReuniaoNaoExistente();
            }
            numpres = ( (double) i /qntreu);
            return numpres;
        }else {
            throw new AssociadoNaoExistente();
        }
    }

    @Override
    public void registrarFrequencia(int codigoAssociado, int numAssociacao, long dataReuniao) throws AssociadoNaoExistente, ReuniaoNaoExistente, AssociacaoNaoExistente, FrequenciaJaRegistrada, FrequenciaIncompativel, ValorInvalido {
        Associado Associ = buscaAssociado(numAssociacao,codigoAssociado);
        if(Associ!=null) {
           ArrayList<Reuniao> Ass = buscaAssociacao(numAssociacao).getReunios();
           for(Reuniao r : Ass){
               if(dataReuniao ==r.getData()){// verificar data do associado, verificar e se freq tá registr
                   ArrayList<String > a = r.getFrequencia();
                   if(a.size()>0) {
                       for (String s : a) {
                           if (Associ.getNome().equals(s)) {
                               throw new FrequenciaJaRegistrada();
                           } else {
                               if (dataReuniao > Associ.getDataAssociacao()) {
                                   r.adicionaFrequencia(Associ.getNome());
                               } else {
                                   throw new FrequenciaIncompativel();
                               }
                           }
                       }
                   }else {
                       if (dataReuniao >= Associ.getDataAssociacao()) {
                           r.adicionaFrequencia(Associ.getNome());
                       } else {
                           throw new FrequenciaIncompativel();
                       }
                   }
               }else {
                   throw new FrequenciaIncompativel();
               }
           }
        }else {
            throw new AssociadoNaoExistente();
        }
    }

    @Override
    public void registrarPagamento(int numAssociacao, String taxa, int vigencia, int numAssociado, long data, double valor) throws AssociacaoNaoExistente, AssociadoNaoExistente, AssociadoJaRemido, TaxaNaoExistente, ValorInvalido {
        if(numAssociacao>0 && !(taxa.trim().isEmpty())&& vigencia>0 && numAssociado>0 && data > 0 && valor>0) {
            boolean no = false;
            boolean b = false;
            Associacao Ass = buscaAssociacao(numAssociacao);
            Associado Associd = buscaAssociado(numAssociacao, numAssociado);
            if (Associd != null) {
                if (Associd.getDataAssociacao() <= data) {
                    ArrayList<Taxa> t = Ass.getTaxa();
                    for (Taxa tx : t) {
                        if (tx.getNome().equals(taxa) && tx.getVigencia() == vigencia) {
                            no = true;
                            if (!(Associd.isRemido())) {//não remido
                                if (valor >= tx.ValorMensal()) {
                                    Ass.AdicionaExtrat(taxa, Associd.getNome(), Associd.getNumero(), valor, data, vigencia);
                                }else {
                                    double valorTxp;
                                    double valoran;
                                    valoran = tx.getValorAno();
                                    valorTxp = Ass.Somaextratos(taxa, numAssociado, vigencia);
                                    valorTxp = valoran - valorTxp;
                                    if (valorTxp <= tx.ValorMensal()) {
                                        double taxap;
                                        taxap = valor - valorTxp;
                                        System.out.println("O valor pago foi: " + taxap + " como última parcela");
                                        if (tx.isAdministrativa()) {
                                            Associd.setRemido();
                                            Ass.AdicionaExtrat(taxa, Associd.getNome(), Associd.getNumero(), taxap, data, vigencia);
                                        }
                                    }else {
                                        throw new ValorInvalido();
                                    }
                                }
                                break;
                            } else {
                                if(!(tx.isAdministrativa())) {
                                    if (valor >= tx.ValorMensal()) {
                                        Ass.AdicionaExtrat(taxa, Associd.getNome(), Associd.getNumero(), valor, data, vigencia);
                                    } else {
                                        double valorTxp;
                                        double valoran;
                                        valoran = tx.getValorAno();
                                        valorTxp = Ass.Somaextratos(taxa, numAssociado, vigencia);
                                        valorTxp = valoran - valorTxp;
                                        if (valorTxp <= tx.ValorMensal()) {
                                            double taxap;
                                            taxap = valorTxp;
                                            System.out.println("O valor pago foi: " + taxap + " como última parcela");
                                            Ass.AdicionaExtrat(taxa, Associd.getNome(), Associd.getNumero(), taxap, data, vigencia);
                                        }else{
                                            throw new ValorInvalido();
                                        }
                                    }
                                    break;
                                }else {
                                    throw new AssociadoJaRemido();
                                }
                            }
                        }
                    }
                    if (!no) {
                        throw new TaxaNaoExistente();
                    }
                } else {
                    throw new ValorInvalido();
                }
            } else {
                throw new AssociadoNaoExistente();
            }
        }else {
            throw new ValorInvalido();
        }
    }

    @Override
    public double somarPagamentoDeAssociado(int numAssociacao, int numAssociado, String nomeTaxa, int vigencia, long inicio, long fim) throws AssociacaoNaoExistente, AssociadoNaoExistente, TaxaNaoExistente, ValorInvalido {
        double somapag =0;
        boolean a = false;
        if(numAssociacao>0&&numAssociado>0&& !(nomeTaxa.trim().isEmpty())&& vigencia>0 && inicio > 0 && fim > 0) {
            Associacao Ass = buscaAssociacao(numAssociacao);
            Associado Associd = buscaAssociado(numAssociacao, numAssociado);
            if(Associd!=null) {
                ArrayList<Extrato> e = Ass.getExtratos();
                if (e != null) {
                    for (Extrato E : e) {
                        if (E.getNumeroassociado() == numAssociado && E.getNomeTaxa().equals(nomeTaxa) && E.getVigenc() == vigencia) {
                            if (E.getDatadepagamento() >= inicio && E.getDatadepagamento() <= fim) {
                                somapag = somapag + E.getValorpago();
                                a = true;
                            }
                        }
                    }
                    if(!a){
                        return somapag;
                    }
                } else {
                    throw new TaxaNaoExistente();
                }
            }else {
                throw new AssociadoNaoExistente();
            }
        }
        return somapag;
    }

    @Override
    public double calcularTotalDeTaxas(int numAssociacao, int vigencia) throws AssociacaoNaoExistente, TaxaNaoExistente, ValorInvalido {
        double tot =0;
        ArrayList<Taxa> t = buscaAssociacao(numAssociacao).getTaxa();
        if(t!=null){
            for (Taxa T: t){
                if(T.getVigencia()==vigencia){
                    tot =tot+ T.getValorAno();
                }
            }
        }else {
            throw new TaxaNaoExistente();
        }
        return tot;
    }
    @Override
    public void adicionar(Associacao a) throws AssociacaoJaExistente, ValorInvalido {
        if(a.getNameAsso()!= null&& (a.getNumberAsso()> 0)) {
            if(!(a.getNameAsso().trim().isEmpty())) {
                try {
                    if (buscaAssociacao(a.getNumberAsso()) != null) {
                        throw new AssociacaoJaExistente();
                    }
                } catch (AssociacaoNaoExistente e) {
                    LisAssoci.add(a);
                }
            }else {
                throw new ValorInvalido();
            }
        }else {
            throw new ValorInvalido();
        }
    }
    @Override// aqui dá pra adicionar dois métodos de busca semelhantes ao que eu fiz hoje.
    public void adicionar(int associacao, Associado a) throws AssociacaoNaoExistente, AssociadoJaExistente, ValorInvalido {
        if(a!= null&&associacao > 0 && a.getNumero()>0 && a.getNumero()>0&&a.getDataAssociacao()>0) {
            if(!(a.getTelefone().trim().isEmpty())&& a.getNascimento()>0) {
                if ((buscaAssociado(associacao, a.getNumero()) != null)) {
                    throw new AssociadoJaExistente();
                } else {
                    Associacao associacao1 = buscaAssociacao(associacao);
                    ArrayList<Associado> Assp = associacao1.getAssociados();
                    Assp.add(a);///colocar as verificações no próprio set
                    associacao1.setAssociados(Assp);//tem uma função pra fazer isso já
                }
            }else {
                throw new ValorInvalido();
            }
        }else {
            throw new ValorInvalido();
        }
    }
    @Override
    public void adicionar(int associacao, Reuniao r) throws AssociacaoNaoExistente, ReuniaoJaExistente, ValorInvalido {
        if(r!=null&& associacao > 0 && r.getAta()!= null&& r.getData()>0) {
            if(r.getData()>0) {//ainda tem bem mais coisa aqui// ainda tem o fato de só deixar isso ocorrer se eu já tiver criado
                ArrayList<Reuniao> Assp = buscaAssociacao(associacao).getReunios();
                if (Assp.size() > 0) {
                    for (Reuniao ass : Assp) {
                        if ((ass.getData() == r.getData()) && ass.getAta().equals(r.getAta())) {//caso o teste n passei aqui eu sei
                            throw new ReuniaoJaExistente();
                        } else {
                            Assp.add(r);
                            buscaAssociacao(associacao).setReunios(Assp);
                            break;
                        }
                    }
                } else {
                    ArrayList<Reuniao> c = new ArrayList<>();
                    c.add(r);
                    buscaAssociacao(associacao).setReunios(c);
                }
            }else {
                throw new ValorInvalido();
            }
        }else{
            throw new ValorInvalido();
        }
    }
    @Override
    public void adicionar(int associacao, Taxa t) throws AssociacaoNaoExistente, TaxaJaExistente, ValorInvalido {
        if(t!=null&& associacao>0 && !(t.getNome().trim().isEmpty())){
            if(t.getVigencia()>0 && t.getParcelas() > 0 && t.getValorAno() > 0){
                ArrayList<Taxa> Assp = buscaAssociacao(associacao).getTaxa();
                if(Assp.size()> 0){
                    for (Taxa tx : Assp){
                        if(!(t.getNome().equals(tx.getNome()))){
                            Assp.add(t);
                            buscaAssociacao(associacao).setTaxa(Assp);
                            break;
                        }else {
                            throw new TaxaJaExistente();
                        }
                    }
                }else {
                    ArrayList<Taxa> c = new ArrayList<>();
                    c.add(t);
                    buscaAssociacao(associacao).setTaxa(c);
                }
            }else {
                throw new ValorInvalido();
            }
        }else {
            throw new ValorInvalido();
        }
    }
    public Associacao buscaAssociacao(int associacao) throws AssociacaoNaoExistente, ValorInvalido {
        if(associacao > 0) {//tem mais validação
            for (Associacao associacao1 : LisAssoci) {
                if (associacao == associacao1.getNumberAsso()) {
                   return associacao1;
                }
            }
            throw new AssociacaoNaoExistente();
        }else {
            throw new ValorInvalido();
        }

    }
    public Associado buscaAssociado(int associacao, int associado) throws AssociacaoNaoExistente, ValorInvalido {
        ArrayList<Associado> Assp = buscaAssociacao(associacao).getAssociados();
        for(Associado Associ : Assp) {
            if(associado == Associ.getNumero()) {
                return Associ;
            }
        }
        return null;
    }
}
