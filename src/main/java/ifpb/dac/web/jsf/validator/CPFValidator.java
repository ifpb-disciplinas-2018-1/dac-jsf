package ifpb.dac.web.jsf.validator;

import ifpb.dac.model.domain.CPF;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 14/06/2018, 07:28:55
 */
@FacesValidator(value = "validator.CPF")
public class CPFValidator implements Validator{

    @Override
    public void validate(
            FacesContext context, 
            UIComponent component,
            Object value) throws ValidatorException {
        
        CPF cpf = (CPF) value;
        
        if(!cpf.isValid()){
            FacesMessage message = new FacesMessage("CPF não é válido");
            throw new ValidatorException(message);
        }
        
    }

//    public void validate(CPF cpf){
//        
//    }
}
