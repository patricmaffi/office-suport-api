package org.pmf.services.office.modules.orderpackage.entrypoint.http.api.error_handler;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    @Schema(example = "404")
    private int code;

    @Schema(example = "ResourceNotFound")
    private String type;

    @Schema(example = "The resource requested was not found")
    private String message;

    @Schema(example = "{}", description = "An arbitrary set of data")
    private Map<String, Object> details;

    @JsonCreator
    public ErrorResponse(
            @JsonProperty("code") int code,
            @JsonProperty("type") String type,
            @JsonProperty("message") String message,
            @JsonProperty("details") HashMap<String, Object> details)
    {
        this.code = code;
        this.type = type;
        this.message = message;
        this.details = details;
    }

    public int getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public Map<String, Object> getDetails() {
        return details;
    }
}


