package br.com.mgr.control.vacina.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author Mauri Reis
 * @since 26/03/21
 */
@Data
@SuperBuilder
@Accessors(chain = true)
public class ResponseError {

    @NotNull(message="Timestamp cannot be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timestamp;
    private final int code;
    private final String status;
    @NotNull(message="Message cannot be null")
    private String message;
}
