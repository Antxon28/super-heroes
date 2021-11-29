package com.antonio.superhero.common.response;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class ErrorDTO {

    private final String status;

    private final String message;
}
