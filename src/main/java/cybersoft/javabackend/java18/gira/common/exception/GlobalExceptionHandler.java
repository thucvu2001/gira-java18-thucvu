package cybersoft.javabackend.java18.gira.common.exception;

import cybersoft.javabackend.java18.gira.common.model.ResponseDTO;
import cybersoft.javabackend.java18.gira.common.util.ResponseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class) // class muon handl
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseDTO> handleConstraintViolationException(ConstraintViolationException exception) {
        return ResponseUtils.error(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class) // class muon handl
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseDTO> handleConstraintViolationException(MethodArgumentNotValidException exception) {
        return ResponseUtils.error(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class) // class muon handl
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseDTO> handleConstraintViolationException(RuntimeException exception) {
        return ResponseUtils.error(exception, HttpStatus.BAD_REQUEST);
    }
}
