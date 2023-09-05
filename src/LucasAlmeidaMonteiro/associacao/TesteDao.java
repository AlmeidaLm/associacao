package LucasAlmeidaMonteiro.associacao;
import static org.junit.Assert.*;
import java.sql.Connection;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

public class TesteDao {
    @Test
    public void testarCadastroDeAssociacaoDAO() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");
        AssociacaoDAO a = new AssociacaoDAO();
        a.inserirAssociacao(a1);
    }
}
