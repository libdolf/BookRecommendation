package br.com.libdolf.bookrecommendation.exceptions.handler;


import br.com.libdolf.bookrecommendation.exceptions.NotFoundException;
import br.com.libdolf.bookrecommendation.exceptions.details.ExceptionDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDetails> handleBookNotFoundException(NotFoundException ex) {
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .title("not found")
                        .status(HttpStatus.NOT_FOUND.value())
                        .details(ex.getMessage())
                        .developerMessage(ex.getClass().getName())
                        .timestamp(LocalDateTime.now())
                        .build(), HttpStatus.NOT_FOUND);
    }



    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        var exceptionsDetails = ExceptionDetails.builder()
                .title(ex.getMessage())
                .status(statusCode.value())
                .details(ex.getMessage())
                .developerMessage(ex.getClass().getName())
                .timestamp(LocalDateTime.now())
                .build();

        return this.createResponseEntity(exceptionsDetails, headers, statusCode, request);
    }

}
