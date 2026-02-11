package br.com.siqueira.shared.api.openapi.responses;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import br.com.siqueira.shared.api.error.ErrorResponse;
import jakarta.ws.rs.core.MediaType;

@Retention(RetentionPolicy.RUNTIME)
@APIResponse(
        responseCode = "409",
        description = "Business conflict",
        content = @Content(
                mediaType = MediaType.APPLICATION_JSON,
                schema = @Schema(implementation = ErrorResponse.class)
        )
)
public @interface ConflictResponse {
}

