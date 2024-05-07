package br.com.francisco.test.audsat.api.exceptionhandler.exceptions;

public class InsuranceNotFoundException extends RuntimeException {

    public InsuranceNotFoundException(String message) {
        super(message);
    }

}
