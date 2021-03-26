package br.com.mgr.control.vacina.exception;

import br.com.mgr.control.vacina.controllers.v1.dto.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Mauri Reis
 * @since 26/03/21
 */
@ControllerAdvice
public class ApiExceptionHandler<T> {


    @ExceptionHandler({Exception.class})
    public ResponseEntity<Response<T>> objectNotFound(Exception e, HttpServletRequest request) {

        Response<T> response = new Response<>();
        response.addErrorMsg(e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}
