package br.com.francisco.test.audsat.domain.service;

import br.com.francisco.test.audsat.api.request.create.InsuranceRequestCreate;
import br.com.francisco.test.audsat.api.request.update.InsuranceRequestUpdate;
import br.com.francisco.test.audsat.api.response.InsuranceResponse;

import java.util.UUID;

public interface InsuranceService {

    InsuranceResponse findById(UUID id);

    void updateById(UUID id, InsuranceRequestUpdate request);

    InsuranceResponse create(InsuranceRequestCreate request);

    void deleteById(UUID id);

}
