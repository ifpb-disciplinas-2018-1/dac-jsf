package ifpb.dac.web;

import ifpb.dac.model.Integrante;
import ifpb.dac.model.ServiceDeIntegrante;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 11/06/2018, 07:24:46
 */
@Named
@SessionScoped
//@RequestScoped
public class ControladorDeIntegrante implements Serializable {

    private Integrante integrante = new Integrante();
    private ServiceDeIntegrante service = new ServiceDeIntegrante();
    private boolean editando = false;

    public String salvar() {
        this.service.adicionar(integrante);
        this.integrante = new Integrante();
        listar();
        return null;
    }

    public String atualizar() {
        this.service.atualizar(integrante);
        this.integrante = new Integrante();
        this.editando = false;
        return null;
    }

    public String editar(Integrante integrante) {
        this.integrante = integrante;
        this.editando = true;
        return null;
    }

    public String excluir(Integrante integrante) {
        this.service.remover(integrante);
        return null;
    }

    public ControladorDeIntegrante() {
    }

    public List<Integrante> listarTodosOsIntegrantes() {
        return this.service.todos();
    }

    public Integrante getIntegrante() {
        return integrante;
    }

    public void setIntegrante(Integrante integrante) {
        this.integrante = integrante;
    }

    public boolean isEditando() {
        return editando;
    }

    public void setEditando(boolean editando) {
        this.editando = editando;
    }

    private void listar() {
        listarTodosOsIntegrantes().forEach(i->System.out.println(i.getNome()));
    }

}
