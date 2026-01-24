package cybersoft.javabackend.java18.gira.common.util;

import cybersoft.javabackend.java18.gira.common.model.ResponseDTO;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Collections;

@UtilityClass
public class ResponseUtils {
    
    public static ResponseEntity<ResponseDTO> get(Object result, HttpStatus status) {
        return ResponseEntity.status(status)
                .body(ResponseDTO.builder()
                        .content(result)
                        .hasErrors(false)
                        .errors(Collections.emptyList())
                        .timestamp(DateTimeUtils.now())
                        .status(status.value())
                        .build());
    }

    public static ResponseEntity<ResponseDTO> error(ConstraintViolationException exception, HttpStatus status) {
        return buildErrorResponse(ExceptionUtils.getErrors(exception), status);
    }

    public static ResponseEntity<ResponseDTO> error(RuntimeException exception, HttpStatus status) {
        return buildErrorResponse(ExceptionUtils.getErrors(exception), status);
    }

    public static ResponseEntity<ResponseDTO> error(MethodArgumentNotValidException exception, HttpStatus status) {
        return buildErrorResponse(ExceptionUtils.getErrors(exception), status);
    }

    public static ResponseEntity<ResponseDTO> error(ValidationException exception, HttpStatus status) {
        return buildErrorResponse(ExceptionUtils.getErrors(exception), status);
    }

    private static ResponseEntity<ResponseDTO> buildErrorResponse(java.util.List<String> errors, HttpStatus status) {
        return ResponseEntity.status(status)
                .body(ResponseDTO.builder()
                        .content(null)
                        .hasErrors(true)
                        .errors(errors)
                        .timestamp(DateTimeUtils.now())
                        .status(status.value())
                        .build());
    }
}
