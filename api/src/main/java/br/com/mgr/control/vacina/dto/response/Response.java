package br.com.mgr.control.vacina.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author Mauri Reis
 * @since 26/03/21
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {

    private T data;

}
