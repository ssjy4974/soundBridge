package com.soundbridge.global.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

//    /**
//     * Authentication 객체가 필요한 권한을 보유하지 않은 경우 발생합
//     */
//    @ExceptionHandler(AccessDeniedException.class)
//    protected ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException e) {
//        log.error("handleAccessDeniedException", e);
//        final ErrorResponse response = ErrorResponse.of(e.getErrorCode());
//        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
//    }
//
//    /**
//     * 존재하지 않는 리소스에 대한 예외 처리
//     */
//    @ExceptionHandler(NotFoundException.class)
//    protected ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException e) {
//        log.error("handleNotFoundException", e);
//
//        final ErrorResponse response = ErrorResponse.of(e.getErrorCode());
//        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//    }
//
//    /**
//     * 이미 존재하는 자원인 경우 발생
//     */
//    @ExceptionHandler(AlreadyExistResourceException.class)
//    protected ResponseEntity<ErrorResponse> handleAlreadyExistResourceException(AlreadyExistResourceException e) {
//        log.error("handleAlreadyExistResourceException", e);
//
//        final ErrorResponse response = ErrorResponse.of(e.getErrorCode());
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }

//    /**
//     * javax.validation.Valid or @Validated 으로 binding error 발생시 발생한다.
//     */
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
//        log.error("handleMethodArgumentNotValidException", e);
//        final ErrorResponse response = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, e.getBindingResult());
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }
//
//    /**
//     * @ModelAttribut 으로 binding error 발생시 BindException 발생한다.
//     */
//    @ExceptionHandler(BindException.class)
//    protected ResponseEntity<ErrorResponse> handleBindException(BindException e) {
//        log.error("handleBindException", e);
//        final ErrorResponse response = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, e.getBindingResult());
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }


}