package ifpb.dac.model;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 11/06/2018, 08:22:15
 */
public class CPF {

    private String numero;

    public CPF(String numero) {
        this.numero = numero;
    }

    public String valor() {
        return numero; // 123.123.123-09;
    }

    public String formatado() {
        return numero.substring(0, 3) + "."
                + numero.substring(3, 6) + "."
                + numero.substring(6, 9) + "-"
                + numero.substring(9, 11); // 123.123.123-09;
    }

    public boolean isValid() {
        return true; // se tem 11 caracteres
    }
}
