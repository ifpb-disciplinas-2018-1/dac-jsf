
package ifpb.dac.model;

import ifpb.dac.model.domain.Endereco;
import ifpb.dac.model.domain.Integrante;
import java.util.List;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 18/06/2018, 07:34:23
 */
public interface Integrantes {

    void adicionar(Integrante integrante);

    void atualizar(Integrante integrante);

    Endereco localizarEnderecoComRua(String rua);

    void remover(Integrante integrante);

    List<Integrante> todos();

    List<Endereco> todosOsEnderecos();
    
}
