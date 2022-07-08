package com.example.univercity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto<T> {

    private boolean success;

    private Integer code;

    private String message;

    private T data;

    private List<ValidatorDto> errors;

    public ResponseDto(boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public ResponseDto(boolean success, Integer code, String message, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseDto(boolean success, Integer code, String message, List<ValidatorDto> errors) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.errors = errors;
    }
}
