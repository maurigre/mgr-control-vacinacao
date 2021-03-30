package br.com.mgr.control.vacina.dto.response;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * @author Mauri Reis
 * @since 26/03/21
 */
@Data
@SuperBuilder
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class ResponseErrorValid  extends ResponseError{

    private final String objectName;
    private final List<ResponseErrorValidField> errors;


}
