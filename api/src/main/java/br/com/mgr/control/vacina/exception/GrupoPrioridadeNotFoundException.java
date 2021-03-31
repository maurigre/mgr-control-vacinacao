package br.com.mgr.control.vacina.exception;

/**
 * @author Mauri Reis
 * @since 26/03/21
 */
public class GrupoPrioridadeNotFoundException extends RuntimeException {

    public GrupoPrioridadeNotFoundException(Long id) {
        super("Grupo Prioridade id not found : " + id);
    }
}
