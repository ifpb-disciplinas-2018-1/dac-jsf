package ifpb.dac.infra.jdbc;

import ifpb.dac.model.CPF;
import ifpb.dac.model.Integrante;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 17/06/2018, 22:20:27
 */
public class IntegranteJDBC {

    //   private static final List<Album> albuns = new CopyOnWriteArrayList<>();
    private Connection conexao;

    public IntegranteJDBC() {
        iniciar();
    }

    public boolean salvar(Integrante integrante) {
        boolean resultado = false;
        String sql = "INSERT INTO integrante (nome,cpf) VALUES(?,?)";
        try {
            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.setString(1, integrante.getNome());
            statement.setString(2, integrante.getCpf().formatado());
            resultado = statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(IntegranteJDBC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
        return resultado;
    }

    public List<Integrante> listarTodos() {
        List<Integrante> integrantes = new ArrayList<>();
        try {
            String consulta = "SELECT * FROM integrante";
            PreparedStatement statement = conexao.prepareStatement(consulta);
            integrantes.addAll(criarIntegrante(statement));
        } catch (SQLException ex) {
            Logger.getLogger(IntegranteJDBC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
        return integrantes;

    }

    public void excluir(Integrante integrantearaExcluir) {
        try {
            String sql = "DELETE FROM integrante WHERE id=?";
            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.setInt(1, integrantearaExcluir.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(IntegranteJDBC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
    }

    public Integrante localizarPor(String nome) {
        String consulta = "SELECT * FROM integrante where nome=?";
        Integrante Integrante = new Integrante();
        try {
            PreparedStatement statement = conexao.prepareStatement(consulta);
            statement.setString(1, nome);
            Integrante = criarIntegrante(statement).get(0);
        } catch (SQLException ex) {
            Logger.getLogger(IntegranteJDBC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();

        }
        return Integrante;
    }

    private List<Integrante> criarIntegrante(PreparedStatement statement) throws SQLException {
        List<Integrante> integrantes = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Integrante integrante = new Integrante(
                    resultSet.getInt("id"),
                    resultSet.getString("nome"),
                    new CPF(resultSet.getString("CPF"))
            );
            integrantes.add(integrante);
        }
        return integrantes;
    }

    private void iniciar() {
        try {
            Class.forName("org.postgresql.Driver");
            this.conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dac-jsf", "postgres", "12345");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(IntegranteJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fecharConexao() {
        try {
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(IntegranteJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
