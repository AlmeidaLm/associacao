package LucasAlmeidaMonteiro.associacao;

public class Associado {
    int numero;//falta def como privado ou protected
    String nome;
    String telefone;
    long dataAssociacao;
    long nascimento;
    boolean remido;// precisa da data de remissão// pagamento depende desse boolean aqui//
    //isso só serve para taxa administrativa,ele ainda paga as taxas sociais.
    double totpagamento;// quando o valor for igual ao total cobrado para o ano em uma associação ele é remido.
    // sendo o total do ano a soma de todos os valores das taxas administrativas já criadas.
    int numpags;//define quantas vezes ele já pagou uma parcela dai salvando, e quando precisar somar
    // multiplica isso pela parcela, contudo taxa é um vetor, então esse vetor tem que acompanhar,o vetor externo
    //que armazena todas as taxas.
    // eu poderia fazer um vetor com lista encadeada, daí o elemento a ser adicionado (data) estaria, de acordo
    // com a posição do vetor referente à taxa, então eu apenas percorreria a lista no índice para contar,
    // e eventualmente multiplicar o valor, o tipo aqui é vetor de inteiros com lista encadeada kkkkkk.
    // ao setar um associado ele precisa de acesso à todas as taxas, no caso de setar uma taxa nova
    // ele precisa pagar ela também então o valor da lista é atualizado para o tamanho atual do array taxas.
    // o problema é como eu vou guardar esse novo objeto já que ele é redimensionado,melhor uma função para
    // redimensionar o array salvando todos os elementos num novo array de posição igual ao .size
    // igual ao novo tamanho do vetor de taxas já existentes.
    long datarm;
    public Associado (int numero, String nome, String telefone,   long dataAssociacao, long nascimento) throws ValorInvalido {
        if(numero>0&&!(nome.trim().isEmpty())&&!(telefone.trim().isEmpty())&& dataAssociacao > 0 && nascimento>0) {
            this.numero = numero;
            this.nome = nome;// falta validar dps lá no array de associações
            this.telefone = telefone;
            this.dataAssociacao = dataAssociacao;
            this.nascimento = nascimento;
            this.remido = false;
        }else {
            throw new ValorInvalido();
        }
    }
    public Associado(int num, String nome, String telef, long dataass, long datanas, long datremis) throws ValorInvalido {
        if(num>0&&!(nome.trim().isEmpty())&&!(telef.trim().isEmpty())&& dataass > 0 && datanas>0 && datremis>0) {
            this.numero = num;
            this.nome = nome;
            this.telefone = telef;
            this.dataAssociacao = dataass;
            this.nascimento = datanas;
            this.remido = true;
            this.datarm = datremis;
        }else {
            throw new ValorInvalido();
        }
    }
    public int getNumero() {
        return numero;
    }

    protected void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    protected void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    protected void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public long getDataAssociacao() {
        return dataAssociacao;
    }

    protected void setDataAssociacao(long dataAssociacao) {
        this.dataAssociacao = dataAssociacao;
    }

    public long getNascimento() {
        return nascimento;
    }

    protected void setNascimento(long nascimento) {
        this.nascimento = nascimento;
    }

    public boolean isRemido() {
        return remido;
    }

    protected void setRemido() {
        this.remido = true;
    }

    public double getTotpagamento() {
        return totpagamento;
    }

    protected void setTotpagamento(double totpagamento) {
        this.totpagamento = totpagamento;
    }

    public int getNumpags() {
        return numpags;
    }

    protected void setNumpags(int numpags) {
        this.numpags = numpags;
    }

}
