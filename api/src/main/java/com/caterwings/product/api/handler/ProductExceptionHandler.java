package com.caterwings.product.api.handler;

import com.caterwings.product.api.dto.Failure;
import com.caterwings.product.service.exception.ProductNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
@Slf4j
public class ProductExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Failure> exception(Exception exception) {
        log.error("Error during request processing.", exception);
        Failure failure = Failure.builder()
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(failure, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = ProductNotFoundException.class)
    public ResponseEntity<Failure> exception(ProductNotFoundException exception) {
        log.error("Item not found.", exception);
        Failure failure = Failure.builder()
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(failure, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }


}
