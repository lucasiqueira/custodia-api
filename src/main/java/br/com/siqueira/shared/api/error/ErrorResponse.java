package br.com.siqueira.shared.api.error;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(
    name = "ErrorResponse",
    description = "Standard error response",
    examples = {
        """
        {
          "code": "ACCOUNT_ALREADY_EXISTS",
          "message": "Account with name 'Main' and type 'CHECKING' already exists"
        }
        """
    }
)
public record ErrorResponse(
    String code,
    String message
) {}

