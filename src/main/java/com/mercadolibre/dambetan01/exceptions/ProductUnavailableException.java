package com.mercadolibre.dambetan01.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ProductUnavailableException extends RuntimeException {
    private HttpStatus httpStatus;

    private Object products;

    public ProductUnavailableException(String message, HttpStatus httpStatus, Object products) {
        super(message);
        this.httpStatus = httpStatus;
        this.products = products;
    }
}
