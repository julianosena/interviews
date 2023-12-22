package com.interview.application.controller.api.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ErrorResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("code")
    private Integer code;

    @JsonProperty("message")
    @NotNull
    private String message;

    @JsonProperty("errors")
    private Map<String, Object> errors;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public Map<String, Object> getErrors(){
        if(null == errors) errors = new HashMap<>();
        return this.errors;
    }
}
