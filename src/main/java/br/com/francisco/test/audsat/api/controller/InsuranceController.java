package br.com.francisco.test.audsat.api.controller;

import br.com.francisco.test.audsat.api.openapi.InsuranceControllerOpenApi;
import br.com.francisco.test.audsat.api.request.create.InsuranceRequestCreate;
import br.com.francisco.test.audsat.api.request.update.InsuranceRequestUpdate;
import br.com.francisco.test.audsat.api.response.InsuranceResponse;
import br.com.francisco.test.audsat.domain.service.InsuranceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping(value = "insurance")
@RequiredArgsConstructor
public class InsuranceController implements InsuranceControllerOpenApi {

    private final InsuranceService service;

    @GetMapping(value = "budget/{insuranceId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InsuranceResponse> findInsuranceById(@PathVariable("insuranceId") UUID id) {
        InsuranceResponse insuranceResponse = service.findById(id);
        return ResponseEntity.ok().body(insuranceResponse);
    }

    @PutMapping(value = "budget/{insuranceId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable("insuranceId") UUID id,
                                       @RequestBody InsuranceRequestUpdate insurance) {
        service.updateById(id, insurance);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "budget", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InsuranceResponse> create(@RequestBody @Valid InsuranceRequestCreate insurance) {
        InsuranceResponse insuranceResponse = service.create(insurance);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(insuranceResponse.getInsuranceId()).toUri();

        return ResponseEntity.created(uri).body(insuranceResponse);
    }

    @DeleteMapping("budget/{insuranceId}")
    public ResponseEntity<Void> delete(@PathVariable("insuranceId") UUID id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
