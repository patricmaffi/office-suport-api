package org.pmf.services.office.modules.orderpackage.entrypoint.http.api.error_handler;

import io.swagger.v3.oas.annotations.media.Schema;

public class ValidationErrorResponse {
    @Schema(example = "12345")
    private int id;

    @Schema(example = "firstName")
    private String field;

    @Schema(example = "Jo")
    private String value;

    @Schema(example = "Nome tem que ter ao menos 3 caracteres")
    private String message;
}
