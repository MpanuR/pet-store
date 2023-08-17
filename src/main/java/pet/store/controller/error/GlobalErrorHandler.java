package pet.store.controller.error;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalErrorHandler {

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)

    public Map<String, String> handleNoSuchElementException(NoSuchElementException ex) {
        log.error("Exception caught: {}", ex.getMessage());

        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("message", ex.toString());

        return errorMap;

    }
}
