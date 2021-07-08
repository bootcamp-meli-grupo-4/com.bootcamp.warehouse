package com.mercadolibre.dambetan01.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice(annotations = RestController.class)
@RequiredArgsConstructor
public class ApiExceptionControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ApiExceptionControllerAdvice.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler({ProductUnavailableException.class})
    public Map<String, Object> handleException(ProductUnavailableException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", ex.getHttpStatus().value());
        response.put("message", ex.getMessage());
        response.put("products_unavailable", ex.getProducts());
        return response;
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handleException(Exception ex) {

        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw));

        return new ApiError(
                ex.getClass().getName(),
                ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value()
                );
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            BadRequestException.class,
            org.springframework.dao.DuplicateKeyException.class,
            org.springframework.web.bind.support.WebExchangeBindException.class,
            org.springframework.http.converter.HttpMessageNotReadableException.class,
            org.springframework.web.server.ServerWebInputException.class
    })
    @ResponseBody
    public ApiError badRequest(Exception ex) {
        logger.info("executing exception handler (REST)");
        return new ApiError(
                ex.getClass().getName(),
                ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
    }



    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ValidationError> handleException(MethodArgumentNotValidException ex) {
        logger.info("Error de validacion");
        return ex.getBindingResult().getAllErrors()
                .stream()
                .map(this::mapError)
                .collect(Collectors.toList());
    }

    private ValidationError mapError(ObjectError objectError) {
        if (objectError instanceof FieldError) {
            return new ValidationError(((FieldError) objectError).getField(),
                    objectError.getDefaultMessage());
        }
        return new ValidationError(objectError.getObjectName(), objectError.getDefaultMessage());
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({
            NotFoundException.class
    })
    @ResponseBody
    public ApiError notFoundRequest(Exception ex) {
        return new ApiError(
                ex.getClass().getName(),
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value()
        );

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            IllegalCategoryProductSector.class, NoSectorSpace.class, InvalidRepresentant.class
    })
    @ResponseBody
    public ApiError invalidCategoryOfProductAndSector(RuntimeException ex) {
        return new ApiError(
                ex.getClass().getName(),
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value()
        );

    }
}