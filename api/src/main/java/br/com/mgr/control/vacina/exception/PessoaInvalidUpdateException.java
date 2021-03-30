package br.com.mgr.control.vacina.exception;

/**
 * @author Mauri Reis
 * @since 30/03/21
 */
public class PessoaInvalidUpdateException extends RuntimeException {

    public PessoaInvalidUpdateException(Long id) {
        super("Pessoa id is invalid : " + id);
    }
}
