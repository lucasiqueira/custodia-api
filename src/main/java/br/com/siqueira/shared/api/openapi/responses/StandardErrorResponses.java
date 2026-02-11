package br.com.siqueira.shared.api.openapi.responses;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@BadRequestResponse
@NotFoundResponse
@ConflictResponse
public @interface StandardErrorResponses {
}

