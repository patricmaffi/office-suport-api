package org.pmf.services.office.modules.orderpackage.entrypoint.http.api.error_handler;

import io.swagger.v3.oas.annotations.media.Schema;
import kotlin.collections.ArrayDeque;

import java.util.List;

public class ValidationResponse {
    @Schema(example = "false")
    private Boolean validationStatus;

    @Schema(example = "Schema validation error")
    private String description;

    private List<ValidationErrorResponse> errors = new ArrayDeque<ValidationErrorResponse>();
}
