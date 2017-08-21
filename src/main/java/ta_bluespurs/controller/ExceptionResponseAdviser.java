package ta_bluespurs.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ta_bluespurs.domain.ErrorResponse;

import java.util.NoSuchElementException;

@ControllerAdvice
public class ExceptionResponseAdviser {

    @ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class, NoSuchElementException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    protected ResponseEntity<ErrorResponse> exceptionHandler(RuntimeException e) {
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setMessage(e.getMessage());
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
    }
}
