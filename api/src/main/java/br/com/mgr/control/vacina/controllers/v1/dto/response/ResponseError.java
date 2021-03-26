package br.com.mgr.control.vacina.controllers.v1.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Mauri Reis
 * @since 26/03/21
 */
@Data
@Builder
public class ResponseError {

    private LocalDateTime timestamp;
    private String message;
}
