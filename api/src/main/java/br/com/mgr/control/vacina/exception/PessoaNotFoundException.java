package br.com.mgr.control.vacina.exception;

/**
 * @author Mauri Reis
 * @since 26/03/21
 */
public class PessoaNotFoundException extends Exception {

    public PessoaNotFoundException() {
        super();
    }

    public PessoaNotFoundException(String message) {
        super(message);
    }

    public PessoaNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
