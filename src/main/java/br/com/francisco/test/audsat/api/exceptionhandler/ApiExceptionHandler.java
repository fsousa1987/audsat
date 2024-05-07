package br.com.francisco.test.audsat.api.exceptionhandler;

import br.com.francisco.test.audsat.api.exceptionhandler.enums.ProblemType;
import br.com.francisco.test.audsat.api.exceptionhandler.exceptions.InsuranceNotFoundException;
import br.com.francisco.test.audsat.api.exceptionhandler.exceptions.InvalidCreateInsuranceException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
@RequiredArgsConstructor
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            @NonNull MethodArgumentNotValidException ex, @NonNull HttpHeaders headers, @NonNull HttpStatusCode status,
            @NonNull WebRequest request) {

        return handleValidationInternal(ex, ex.getBindingResult(), headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            @NonNull HttpMessageNotReadableException ex, @NonNull HttpHeaders headers, @NonNull HttpStatusCode status,
            @NonNull WebRequest request) {

        var problemType = ProblemType.INVALID_DATA;
        var detail = "Some field was filled out incorrectly";
        var problem = createProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(InsuranceNotFoundException.class)
    public ResponseEntity<?> handleInsuranceNotFoundException(InsuranceNotFoundException ex, WebRequest request) {

        var status = HttpStatus.NOT_FOUND;
        var problemType = ProblemType.RESOURCE_NOT_FOUND;
        var detail = ex.getMessage();

        var problem = createProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(InvalidCreateInsuranceException.class)
    public ResponseEntity<?> handleInvalidCreateInsuranceException(InvalidCreateInsuranceException ex,
                                                                     WebRequest request) {
        var status = HttpStatus.BAD_REQUEST;
        var problemType = ProblemType.INVALID_DATA;
        var detail = ex.getMessage();

        var problem = createProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }


    private Problem.ProblemBuilder createProblemBuilder(HttpStatusCode status, ProblemType problemType, String detail) {
        return Problem.builder()
                .timestamp(OffsetDateTime.now())
                .status(status.value())
                .title(problemType.getTitle())
                .detail(detail);
    }

    private ResponseEntity<Object> handleValidationInternal(
            Exception ex, BindingResult bindingResult, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        var problemType = ProblemType.INVALID_DATA;
        var detail = "One or more fields are invalid. Fill in correctly and try again";

        var problemObjects = bindingResult.getAllErrors().stream()
                .map(objectError -> {
                    var message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());

                    var name = objectError.getObjectName();

                    if (objectError instanceof FieldError) {
                        name = ((FieldError) objectError).getField();
                    }

                    return Problem.Object.builder()
                            .name(name)
                            .userMessage(message)
                            .build();
                })
                .collect(Collectors.toList());

        var problem = createProblemBuilder(status, problemType, detail)
                .objects(problemObjects)
                .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

}
