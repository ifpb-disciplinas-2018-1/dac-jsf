package ifpb.dac.web.jsf;

import ifpb.dac.model.service.ServiceDeCliente;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

//import javax.faces.bean.RequestScoped;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 04/06/2018, 07:47:25
 */
@Named
@RequestScoped
//@ManagedBean
//@RequestScoped
public class ControladorDeCliente {

    private String nome;
    private ServiceDeCliente service = new ServiceDeCliente();
    
    public String salvar(){
//        return null;

        this.nome = service.novoCliente(nome);
        
        return "home.xhtml";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
