package ifpb.dac.infra.memory;

import ifpb.dac.model.Integrantes;
import ifpb.dac.model.domain.Endereco;
import ifpb.dac.model.domain.Integrante;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 11/06/2018, 07:29:39
 */
public class IntegrantesEmMemoria implements Integrantes {

    private static List<Integrante> lista = new ArrayList<>();
    private static AtomicInteger count = new AtomicInteger(1);

    @Override
    public void adicionar(Integrante integrante) {
        integrante.setId(count.getAndIncrement());
        lista.add(integrante);
    }

    @Override
    public List<Integrante> todos() {
        return Collections.unmodifiableList(lista);
    }

    @Override
    public void remover(Integrante integrante) {
        lista.remove(integrante);
    }

    @Override
    public void atualizar(Integrante integrante) {
//        for (Integrante integranteLista : lista) {
//            if(integrante.getId()==integranteLista.getId()){
//                
//            }
//        }

//        for (int i = 0; i < lista.size(); i++) {
//            Integrante get = lista.get(i);
//            if (integrante.getId() == get.getId()) {
//                lista.set(i, integrante);
//            }
//        }
//        remover(integrante);
        lista.removeIf(i -> i.getId() == integrante.getId());
        lista.add(integrante);
    }

    @Override
    public List<Endereco> todosOsEnderecos() {
        return Arrays.asList(
                new Endereco("Minha rua", "Meu bairro", "Minha cidade"),
                new Endereco("Tua rua", "Teu bairro", "Tua cidade"),
                new Endereco("Nossa rua", "Nosso bairro", "Nossa cidade")
        );
    }

    @Override
    public Endereco localizarEnderecoComRua(String rua) {

//        for (Endereco end : todosOsEnderecos()) {
//            if (end.getRua().equalsIgnoreCase(rua)) {
//                return end;
//            }
//        }
//        return new Endereco();
        return todosOsEnderecos()
                .stream()
                //                .filter(new Predicate<Endereco>() {
                //                    @Override
                //                    public boolean test(Endereco t) {
                //                        return t.getRua().equalsIgnoreCase(rua);
                //                    }
                //                })
                //                .filter((Endereco end) -> end.getRua().equalsIgnoreCase(rua))
                .filter(end -> end.getRua().equalsIgnoreCase(rua))
                .findFirst()
                .orElse(new Endereco());
    }
}
