package br.com.francisco.test.audsat.api.openapi;

import br.com.francisco.test.audsat.api.exceptionhandler.Problem;
import br.com.francisco.test.audsat.api.request.create.InsuranceRequestCreate;
import br.com.francisco.test.audsat.api.request.update.InsuranceRequestUpdate;
import br.com.francisco.test.audsat.api.response.InsuranceResponse;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

@Tag(name = "Insurance")
@OpenAPIDefinition(info = @Info(title = "Audset"))
public interface InsuranceControllerOpenApi {

    @Operation(summary = "Consult the insurance quote", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consultation carried out successfully"),
            @ApiResponse(responseCode = "404", description = "Insurance not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Problem.class
                            )
                    )}
            ),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Problem.class
                            )
                    )}
            )
    })
    ResponseEntity<InsuranceResponse> findInsuranceById(UUID id);

    @Operation(summary = "Update a budget with the data provided", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Update completed successfully"),
            @ApiResponse(responseCode = "404", description = "Insurance not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Problem.class
                            )
                    )}
            ),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Problem.class
                            )
                    )}
            )
    })
    ResponseEntity<Void> update(UUID id, InsuranceRequestUpdate insurance);

    @Operation(summary = "Register a quote with the data provided", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Registration completed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid data",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Problem.class
                            )
                    )}
            ),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Problem.class
                            )
                    )}
            )
    })
    ResponseEntity<InsuranceResponse> create(InsuranceRequestCreate insurance);

    @Operation(summary = "Remove a quote with the provided data", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Removal carried out successfully"),
            @ApiResponse(responseCode = "404", description = "Insurance not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Problem.class
                            )
                    )}
            ),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Problem.class
                            )
                    )}
            )
    })
    ResponseEntity<Void> delete(UUID id);
}
