package ifpb.dac.web.jsf.convert;

import ifpb.dac.model.domain.Endereco;
import ifpb.dac.model.service.ServiceDeIntegrante;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 14/06/2018, 08:01:33
 */
@FacesConverter(forClass = Endereco.class, value = "convert.Endereco")
public class EnderecoConvert implements Converter {

    private ServiceDeIntegrante service = new ServiceDeIntegrante();

    @Override
    public Object getAsObject(FacesContext context,
            UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        return this.service.localizarEnderecoComRua(value);
//        return new Endereco();
    }

    @Override
    public String getAsString(FacesContext context,
            UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        Endereco endereco = (Endereco) value;
        return endereco.getRua();
//        return value.toString();
    }

}
