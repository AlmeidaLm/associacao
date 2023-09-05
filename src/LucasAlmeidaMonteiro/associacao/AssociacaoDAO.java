package LucasAlmeidaMonteiro.associacao;

import java.sql.*;

public class AssociacaoDAO {
      private static final String url = "jdbc:mysql://localhost:3306/assossiabd";
      private static final String user = "root";
      private static final String senha = "lucas2017";

      public void inserirAssociacao(Associacao a) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String sql = "insert into associacoes (NumAssociac,NomeAssociac) values (" + a.getNumberAsso() + ", '" + a.getNameAsso() + ")";
            try (Connection connection = DriverManager.getConnection(url, user, senha)) {
                  try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                        preparedStatement.executeUpdate();
                        System.out.println("Inserção bem-sucedida!");

                  } catch (SQLException e) {
                        throw new RuntimeException(e);
                  }
            } catch (SQLException e) {
                  e.printStackTrace();

            }
      }
}
