package com.dev.moduleapi.exception;

import com.dev.moduleapi.dto.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.io.PrintWriter;
import java.io.StringWriter;

@Slf4j
@RestControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler(SearchApplicationException.class)
    public ResponseEntity<?> handleBlogApplicationException(SearchApplicationException e) {
        log.error("{}, errorCode = {}", e.getMessage(), e.getErrorCode());
        return ResponseEntity.status(e.getErrorCode().getStatus())
                .body(Response.error(e.getErrorCode().name()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<?> handleMethodArgumentValidException(MethodArgumentNotValidException e) {
        log.error("Invalid data value 'object' or 'parameter' when API call.", e);
        return ResponseEntity.status(ErrorCode.INVALID_PARAMETER.getStatus())
                .body(Response.error(e.getBindingResult().getAllErrors().get(0).getDefaultMessage()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    private ResponseEntity<?> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.error(String.format("'%s' should be a valid '%s' and '%s' isn't", e.getName(), e.getRequiredType(), e.getValue()));
        return ResponseEntity.status(ErrorCode.INVALID_PARAMETER.getStatus())
                .body(Response.error(String.format("'%s' should be a valid '%s' and '%s' isn't",
                        e.getName(), e.getRequiredType(), e.getValue())));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("Exceptions due to missing request data, etc.", e);
        return ResponseEntity.status(ErrorCode.REQUEST_BODY_MISSING_ERROR.getStatus())
                .body(Response.error(ErrorCode.REQUEST_BODY_MISSING_ERROR.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<?> handleException(Exception e) {
        StringWriter writer = new StringWriter();
        e.printStackTrace(new PrintWriter(writer));
        log.error("Internal Server Error. {}", writer);
        return ResponseEntity.status(ErrorCode.INTERNAL_SERVER_ERROR.getStatus())
                .body(Response.error(ErrorCode.INTERNAL_SERVER_ERROR.getMessage()));
    }
}
