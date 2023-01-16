package org.pmf.services.office.modules.orderpackage.entrypoint.http.api.error_handler;

import org.pmf.services.office.modules.orderpackage.domain.exception.OrderPackageNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class officeExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger  = LoggerFactory.getLogger(officeExceptionHandler.class);

    @ExceptionHandler(OrderPackageNotFoundException.class)
    public ResponseEntity<ErrorResponse>  handle(OrderPackageNotFoundException ex)
    {
        logger.error(ex.toString());
        ErrorResponse body = new ErrorResponse(404, ex.getClass().getSimpleName(), ex.getMessage(), new HashMap<String, Object>());
        return new ResponseEntity<ErrorResponse>(body, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex) {
        ex.printStackTrace();
        ErrorResponse body = new ErrorResponse(400, ex.getClass().getSimpleName(), ex.getMessage(), new HashMap<String, Object>());
        return new ResponseEntity<ErrorResponse>(body, HttpStatus.BAD_REQUEST);
    }
}
