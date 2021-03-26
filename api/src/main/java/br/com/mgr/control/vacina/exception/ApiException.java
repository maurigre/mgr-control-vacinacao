package br.com.mgr.control.vacina.exception;

/**
 * @author Mauri Reis
 * @since 26/03/21
 */
public class ApiException extends Exception {

    public ApiException() {
        super();
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
