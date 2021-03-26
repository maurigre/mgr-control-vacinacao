package br.com.mgr.control.vacina.controllers.v1.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Mauri Reis
 * @since 26/03/21
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {

    private T data;
    private Object errors;


    public void addErrorMsg(String msgErros) {
        ResponseError responseError = ResponseError.builder()
                .timestamp(LocalDateTime.now())
                .message(msgErros)
                .build();
        System.out.println(responseError);
        setErrors(responseError);
    }

}
