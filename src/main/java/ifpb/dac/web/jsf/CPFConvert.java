package ifpb.dac.web.jsf;

import ifpb.dac.model.CPF;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 11/06/2018, 08:28:20
 */

@FacesConverter(value = "convert.CPF", forClass = CPF.class)
public class CPFConvert implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        return new CPF(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        CPF cpf = (CPF) value;
        return cpf.valor();
    }

}
//CDI.current().select(Banco.class).get();