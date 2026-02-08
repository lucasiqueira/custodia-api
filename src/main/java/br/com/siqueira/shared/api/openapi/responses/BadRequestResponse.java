package br.com.siqueira.shared.api.openapi.responses;

import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import br.com.siqueira.shared.api.error.ErrorResponse;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@APIResponse(
        responseCode = "400",
        description = "Invalid request",
        content = @Content(
                mediaType = MediaType.APPLICATION_JSON,
                schema = @Schema(implementation = ErrorResponse.class)
        )
)
public @interface BadRequestResponse {
}
