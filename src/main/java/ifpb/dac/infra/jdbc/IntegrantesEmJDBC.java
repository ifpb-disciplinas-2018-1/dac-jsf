package ifpb.dac.infra.jdbc;

import ifpb.dac.model.Integrantes;
import ifpb.dac.model.domain.Endereco;
import ifpb.dac.model.domain.Integrante;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 18/06/2018, 07:33:06
 */
public class IntegrantesEmJDBC implements Integrantes {

    private Connection conexao;

    public IntegrantesEmJDBC() {
        abrirConexao();
    }

    @Override
    public void adicionar(Integrante integrante) {
        String sql = "INSERT INTO integrante (nome,cpf, dataDeNascimento, id_end ) VALUES(?,?,?,?)";
        try {
            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.setString(1, integrante.getNome());
            statement.setString(2, integrante.getCpf().valor());
            statement.setDate(3, Date.valueOf(integrante.getDataDeNascimento()));
            //TODO: verificar existencia do endereco
            statement.setString(4, integrante.getEndereco().getRua());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(IntegrantesEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            fecharConexao();
        }
    }

    @Override
    public void atualizar(Integrante integrante) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Endereco localizarEnderecoComRua(String rua) {
        Endereco endereco = new Endereco();
        try {
            String consulta = "SELECT * FROM endereco WHERE rua=?;";
            PreparedStatement statement = conexao.prepareStatement(consulta);
            statement.setString(1, rua);
            endereco = percorrerEndereco(statement);
        } catch (SQLException ex) {
            Logger.getLogger(IntegrantesEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            fecharConexao();
        }
        return endereco;
    }

    @Override
    public void remover(Integrante integrante) {
        String sql = "DELETE FROM integrante WHERE id=?";
        try {
            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.setInt(1, integrante.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(IntegrantesEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            fecharConexao();
        }
    }

    @Override
    public List<Integrante> todos() {
        List<Integrante> integrantes = new ArrayList<>();
        try {
            String consulta = "SELECT * FROM integrante;";
            PreparedStatement statement = conexao.prepareStatement(consulta);
            percorrerIntegrantes(statement, integrantes);
        } catch (SQLException ex) {
            Logger.getLogger(IntegrantesEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            fecharConexao();
        }
        return integrantes;
    }

    @Override
    public List<Endereco> todosOsEnderecos() {
        List<Endereco> enderecos = new ArrayList<>();
        try {
            String consulta = "SELECT * FROM endereco;";
            PreparedStatement statement = conexao.prepareStatement(consulta);
            percorrerEndereco(statement, enderecos);
        } catch (SQLException ex) {
            Logger.getLogger(IntegrantesEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            fecharConexao();
        }
        return enderecos;
    }

    private Endereco percorrerEndereco(PreparedStatement statement) throws SQLException {
        ResultSet executeQuery = statement.executeQuery();
        executeQuery.next();
        return criarEndereco(executeQuery);
    }

    private void percorrerEndereco(
            PreparedStatement statement,
            List<Endereco> enderecos) throws SQLException {
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            enderecos.add(criarEndereco(result));
        }
    }

    private void percorrerIntegrantes(PreparedStatement statement,
            List<Integrante> integrantes) throws SQLException {

        ResultSet result = statement.executeQuery();
        while (result.next()) {
            integrantes.add(criarIntegrante(result));
        }
    }

    private Integrante criarIntegrante(ResultSet result) throws SQLException {
        Integrante integrante = new Integrante(
                result.getInt("id"),
                result.getString("nome"),
                result.getString("cpf"),
                localizarEnderecoComRua(result.getString("id_end")),
                result.getDate("dataDeNascimento").toLocalDate());

        return integrante;
    }

    private Endereco criarEndereco(ResultSet result) throws SQLException {
        Endereco endereco = new Endereco(
                result.getString("rua"),
                result.getString("bairro"),
                result.getString("cidade"));
        return endereco;
    }

    private void abrirConexao() {
        try {
            Class.forName("org.postgresql.Driver");
            this.conexao = DriverManager.getConnection(
                    "jdbc:postgresql://host-banco:5432/dac-jsf",
                    "postgres", "12345");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(IntegrantesEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fecharConexao() {
        try {
            this.conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(IntegrantesEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
