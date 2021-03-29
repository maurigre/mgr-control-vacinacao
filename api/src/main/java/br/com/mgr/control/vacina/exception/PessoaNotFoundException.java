package br.com.mgr.control.vacina.exception;

/**
 * @author Mauri Reis
 * @since 26/03/21
 */
public class PessoaNotFoundException extends RuntimeException {

    public PessoaNotFoundException(Long id) {
        super("Pessoa id not found : " + id);
    }
}
