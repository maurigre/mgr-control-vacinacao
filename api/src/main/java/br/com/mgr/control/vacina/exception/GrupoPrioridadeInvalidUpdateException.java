package br.com.mgr.control.vacina.exception;

/**
 * @author Mauri Reis
 * @since 30/03/21
 */
public class GrupoPrioridadeInvalidUpdateException extends RuntimeException {

    public GrupoPrioridadeInvalidUpdateException(Long id) {
        super("Grupo Prioridade id is invalid : " + id);
    }
}
