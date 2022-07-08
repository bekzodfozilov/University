package com.example.univercity.exceptions;

import com.example.univercity.dto.ValidatorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidatorDto>> handle(MethodArgumentNotValidException e) {
        List<ValidatorDto> errors = e.getBindingResult().getFieldErrors().stream()
                .map(ee -> ValidatorDto.builder()
                        .fieldName(ee.getField())
                        .error(ee.getDefaultMessage())
                        .build())
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
