package br.com.mgr.control.vacina.exception;

import br.com.mgr.control.vacina.dto.response.ResponseError;
import br.com.mgr.control.vacina.dto.response.ResponseErrorValid;
import br.com.mgr.control.vacina.dto.response.ResponseErrorValidField;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Mauri Reis
 * @since 26/03/21
 */
@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        List<ResponseErrorValidField> errors = getErrorsValidFields(ex);
        ResponseErrorValid errorResponse = getResponseErrorValid(ex, status, errors);
        return new ResponseEntity<>(errorResponse, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        ResponseError errors = getResponseError(ex, status);
        return new ResponseEntity<>(errors, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        ResponseError errors = getResponseError(ex, status);
        return new ResponseEntity<>(errors, status);
    }

    @ExceptionHandler({
            MethodArgumentTypeMismatchException.class,
            NumberFormatException.class,
            PessoaInvalidUpdateException.class,
            GrupoPrioridadeInvalidUpdateException.class,
            RuntimeException.class
    })
    public ResponseEntity<ResponseError> customHandleBadRequest(RuntimeException ex) {
        ResponseError errors = getResponseError(ex, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler({
            PessoaNotFoundException.class,
            GrupoPrioridadeNotFoundException.class,
            Exception.class
    })
    public ResponseEntity<ResponseError> customHandleNotFound(Exception ex) {
        ResponseError errors = getResponseError(ex, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    private ResponseError getResponseError(Exception ex, HttpStatus status) {
        return ResponseError.builder()
                .timestamp(LocalDateTime.now())
                .message(ex.getMessage())
                .code(status.value())
                .status(status.getReasonPhrase())
                .build();
    }

    private ResponseErrorValid getResponseErrorValid(MethodArgumentNotValidException ex, HttpStatus status, List<ResponseErrorValidField> errors)  {
        return ResponseErrorValid.builder()
                .objectName(ex.getBindingResult().getObjectName())
                .errors(errors)
                .timestamp(LocalDateTime.now())
                .message("Requisição possui campos inválidos")
                .code(status.value())
                .status(status.getReasonPhrase()).build();

    }

    private List<ResponseErrorValidField> getErrorsValidFields(BindException ex) {
        return ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new ResponseErrorValidField(error.getDefaultMessage(), error.getField(), error.getRejectedValue()))
                .collect(Collectors.toList());
    }
}
