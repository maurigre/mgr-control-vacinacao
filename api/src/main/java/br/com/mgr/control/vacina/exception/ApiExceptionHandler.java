package br.com.mgr.control.vacina.exception;

import br.com.mgr.control.vacina.controllers.v1.dto.response.Response;
import br.com.mgr.control.vacina.controllers.v1.dto.response.ResponseError;
import br.com.mgr.control.vacina.error.RestExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * @author Mauri Reis
 * @since 26/03/21
 */
@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler({Exception.class})
    public ResponseEntity<ResponseError> objectNotFound(Exception e, HttpServletRequest request) {
        ResponseError errors = getResponseError(e, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ResponseError> customHandleBadRequest(Exception ex, WebRequest request) {
        ResponseError errors = getResponseError(ex, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(PessoaNotFoundException.class)
    public ResponseEntity<ResponseError> customHandleNotFound(Exception ex, WebRequest request) {
        ResponseError errors = getResponseError(ex, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    private ResponseError getResponseError(Exception ex, HttpStatus status) {
        return ResponseError.builder()
                .timestamp(LocalDateTime.now())
                .message(ex.getMessage())
                .status(status.value())
                .build();
    }
}
