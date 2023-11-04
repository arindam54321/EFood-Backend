package com.ari.efood.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseWrapper<T> {
    T data;
    Integer status;
    Object headers;

    public static <T> ResponseWrapper<T> wrap(T data, Integer status) {
        return new ResponseWrapper<T>(data, status, null);
    }

    public static <T> ResponseWrapper<T> wrap(T data, Integer status, Object headers) {
        return new ResponseWrapper<T>(data, status, headers);
    }

    public static <T> ResponseWrapper<T> wrap(T data, HttpStatus status) {
        return new ResponseWrapper<T>(data, status.value(), null);
    }

    public static <T> ResponseWrapper<T> wrap(T data, HttpStatus status, Object headers) {
        return new ResponseWrapper<T>(data, status.value(), headers);
    }

    public static <T> ResponseEntity<ResponseWrapper<T>> entity(T data, HttpStatus status) {
        return ResponseEntity.status(status).body(ResponseWrapper.wrap(data, status));
    }

    public static <T> ResponseEntity<ResponseWrapper<T>> entity(T data, HttpStatus status, Object headers) {
        return ResponseEntity.status(status).body(ResponseWrapper.wrap(data, status, headers));
    }

    public static <T> ResponseEntity<ResponseWrapper<T>> entity(T data, Integer status) {
        return ResponseEntity.status(status).body(ResponseWrapper.wrap(data, status));
    }

    public static <T> ResponseEntity<ResponseWrapper<T>> entity(T data, Integer status, Object headers) {
        return ResponseEntity.status(status).body(ResponseWrapper.wrap(data, status, headers));
    }
}
