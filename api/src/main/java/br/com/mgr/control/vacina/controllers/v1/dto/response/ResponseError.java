package br.com.mgr.control.vacina.controllers.v1.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author Mauri Reis
 * @since 26/03/21
 */
@Data
@Builder
@Accessors(chain = true)
public class ResponseError {

    @NotNull(message="Timestamp cannot be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timestamp;

    private int status;

    @NotNull(message="Message cannot be null")
    private String message;
}
