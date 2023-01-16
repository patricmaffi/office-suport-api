package org.pmf.services.office.service.entrypoint.http.exception_handlers;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {

        String message = "Malformed JSON request: " + ex.getMessage();

        if (ex.getCause() instanceof InvalidFormatException) {
            message = ((InvalidFormatException) ex.getCause()).getOriginalMessage();
        }

        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, ex, message));
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildValidationError(ex);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        return buildValidationError(ex);
    }

    private ResponseEntity<Object> buildValidationError(BindException ex) {
        logger.error(ex);
        List<ValidationError> errors = new ArrayList<>();

        for (ObjectError error : ex.getAllErrors()) {
            FieldError field = (FieldError) error;
            errors.add(new ValidationError(
                    field.getField(),
                    error.getDefaultMessage()
            ));
        }

        var message = "Input with invalid constraint";
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, ex, message, errors));
    }

//    @ExceptionHandler(Exception.class)
//    public final ResponseEntity<Object> handleAllExceptions(Exception ex) {
//        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, ex));
//    }

    @ExceptionHandler(InvalidFormatException.class)
    public final ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex) {
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, ex));
    }

    @ExceptionHandler(InvocationTargetException.class)
    public final ResponseEntity<Object> handleInvalidFormatException(InvocationTargetException ex) {
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, ex));
    }

    @ExceptionHandler(MismatchedInputException.class)
    public final ResponseEntity<Object> handleMismatchedInputException(MismatchedInputException ex) {
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, ex));
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
