package com.algaworks.algafood_api.exceptionhandler;

import com.algaworks.algafood_api.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood_api.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood_api.domain.exception.NegocioException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException e, WebRequest request) {
//        Problema problema = Problema.builder()
//                .message(e.getMessage())
//                .dateTime(LocalDateTime.now())
//                .build();

        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> handleEntidadeEmUsoException(EntidadeEmUsoException e, WebRequest request) {
//        Problema problema = Problema.builder()
//                .message(e.getMessage())
//                .dateTime(LocalDateTime.now())
//                .build();

        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> handleNegocioException(NegocioException e, WebRequest request) {
//        Problema problema = Problema.builder()
//                .message(e.getMessage())
//                .dateTime(LocalDateTime.now())
//                .build();

        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
//        Problema problema = Problema.builder()
//                .message(e.getMessage())
//                .dateTime(LocalDateTime.now())
//                .build();
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problema);
//    }

//    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
//    public ResponseEntity<?> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
//        Problema problema = Problema.builder()
//                .message(e.getMessage())
//                .dateTime(LocalDateTime.now())
//                .build();
//        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(problema);
//    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (body == null) {
            body = Problema.builder()
                    .message(status.getReasonPhrase())
                    .dateTime(LocalDateTime.now())
                    .build();
        } else if (body instanceof String) {
            body = Problema.builder()
                    .message((String) body)
                    .dateTime(LocalDateTime.now())
                    .build();
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }
}
