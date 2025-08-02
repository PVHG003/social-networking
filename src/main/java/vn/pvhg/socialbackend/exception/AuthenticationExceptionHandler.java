package vn.pvhg.socialbackend.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import vn.pvhg.socialbackend.response.ApiError;

import java.util.List;

@RestControllerAdvice
public class AuthenticationExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException e) {
        String error = e.getMessage();
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, error, List.of(error));
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Object> handleEmailAlreadyExistsException(EmailAlreadyExistsException e) {
        String error = e.getMessage();
        ApiError apiError = new ApiError(HttpStatus.CONFLICT, error, List.of(error));
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException e) {
        String error = e.getMessage();
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, error, List.of(error));
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

}
