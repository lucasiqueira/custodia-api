package br.com.siqueira.shared.api.error;

import java.time.LocalDateTime;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "ErrorResponse", description = "Standard error response")
public record ErrorResponse(

    @Schema(description = "Error timestamp")
    LocalDateTime timestamp,

    @Schema(description = "HTTP status code", examples = "422")
    int status,

    @Schema(description = "Application error code", examples = "UNPROCESSABLE_ENTITY")
    String code,

    @Schema(description = "Error message", examples = "Entity is unprocessable. Check the documentation for details.")
    String message,
    
    @Schema(description = "Origin of the error (class.method:line)")
    String origin,
    
    @Schema(description = "Request path", examples = "/v1/accounts/123")
    String path,

    @Schema(description = "Trace identifier for logs", examples = "d290f1ee-6c54-4b01-90e6-d701748f0851")
    String traceId
) {}
