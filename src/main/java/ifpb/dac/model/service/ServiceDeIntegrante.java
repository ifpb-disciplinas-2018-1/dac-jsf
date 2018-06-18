package ifpb.dac.model.service;

import ifpb.dac.infra.jdbc.IntegrantesEmJDBC;
import ifpb.dac.model.domain.Endereco;
import ifpb.dac.model.domain.Integrante;
import ifpb.dac.model.Integrantes;
import java.util.List;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 11/06/2018, 07:29:39
 */
public class ServiceDeIntegrante {

//    private IntegrantesEmMemoria dados = new IntegrantesEmMemoria();
//    private Integrantes dados = new IntegrantesEmMemoria();
    private Integrantes dados = new IntegrantesEmJDBC();

    public void adicionar(Integrante integrante) {
        this.dados.adicionar(integrante);
    }

    public List<Integrante> todos() {
        return this.dados.todos();
    }

    public void remover(Integrante integrante) {
        this.dados.remover(integrante);
    }

    public void atualizar(Integrante integrante) {
        this.dados.atualizar(integrante);
    }

    public List<Endereco> todosOsEnderecos() {
        return this.dados.todosOsEnderecos();
    }

    public Endereco localizarEnderecoComRua(String rua) {
        return this.dados.localizarEnderecoComRua(rua);
    }
}
