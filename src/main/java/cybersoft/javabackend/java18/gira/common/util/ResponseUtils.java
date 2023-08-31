package cybersoft.javabackend.java18.gira.common.util;

import cybersoft.javabackend.java18.gira.common.model.ResponseDTO;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Collections;

@UtilityClass // chi dung ten class va . toi thuoc tinh, khong tao instance
public class ResponseUtils {
    public static ResponseEntity<ResponseDTO> get(Object result, HttpStatus status) {
        return new ResponseEntity<>(
                ResponseDTO.builder()
                        .content(result)
                        .hasErrors(false)
                        .errors(Collections.emptyList())
                        .timestamp(DateTimeUtils.now())
                        .status(status.value())
                        .build()
                , status
        );
    }

    public static ResponseEntity<ResponseDTO> error(ConstraintViolationException exception, HttpStatus status) {
        return new ResponseEntity<>(
                ResponseDTO.builder()
                        .content(null)
                        .hasErrors(true)
                        .errors(ExceptionUtils.getErrors(exception)) // ExceptionUtils
                        .timestamp(DateTimeUtils.now())
                        .status(status.value())
                        .build()
                , status
        );
    }

    public static ResponseEntity<ResponseDTO> error(RuntimeException exception, HttpStatus status) {
        return new ResponseEntity<>(
                ResponseDTO.builder()
                        .content(null)
                        .hasErrors(true)
                        .errors(ExceptionUtils.getErrors(exception))
                        .timestamp(DateTimeUtils.now())
                        .status(status.value())
                        .build()
                , status
        );
    }

    public static ResponseEntity<ResponseDTO> error(MethodArgumentNotValidException exception, HttpStatus status) {
        return new ResponseEntity<>(
                ResponseDTO.builder()
                        .content(null)
                        .hasErrors(true)
                        .errors(ExceptionUtils.getErrors(exception))
                        .timestamp(DateTimeUtils.now())
                        .status(status.value())
                        .build()
                , status
        );
    }

    public static ResponseEntity<ResponseDTO> error(ValidationException exception, HttpStatus status) {
        return new ResponseEntity<>(
                ResponseDTO.builder()
                        .content(null)
                        .hasErrors(true)
                        .errors(ExceptionUtils.getErrors(exception))
                        .timestamp(DateTimeUtils.now())
                        .status(status.value())
                        .build()
                , status
        );
    }

//    public static ResponseEntity<ResponseDTO> errors(DisabledException exception, HttpStatus status) {
//        return new ResponseEntity<>(
//                ResponseDTO.builder()
//                        .content(null)
//                        .hasErrors(true)
//                        .errors(ExceptionUtils.getErrors(exception))
//                        .timestamp(DateTimeUtils.now())
//                        .status(status.value())
//                        .build()
//                , status
//        );
//    }
}
