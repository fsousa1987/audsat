package br.com.francisco.test.audsat.api.exceptionhandler.enums;

import lombok.Getter;

@Getter
public enum ProblemType {

    RESOURCE_NOT_FOUND("Resource not found"),
    INVALID_DATA("Invalid data");

    private final String title;

    ProblemType(String title) {
        this.title = title;
    }

}
