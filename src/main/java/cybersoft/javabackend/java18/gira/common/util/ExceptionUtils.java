package cybersoft.javabackend.java18.gira.common.util;

import lombok.experimental.UtilityClass;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.List;

@UtilityClass
public class ExceptionUtils {
    public static final String DEFAULT_UNEXPECTED_MESSAGE = "Ops! Something wrong happens...";

    public static List<String> getErrors(Exception exception) {
        return List.of(DEFAULT_UNEXPECTED_MESSAGE);
    }

    public static List<String> getErrors(ConstraintViolationException exception) {
        return exception.getConstraintViolations()
                .stream()
                .map(constraintViolation -> constraintViolation.getMessage())
                .toList();
    }

    public static List<String> getErrors(RuntimeException exception) {
        return exception.getMessage().lines().toList();
    }

    public static List<String> getErrors(MethodArgumentNotValidException exception) {
        return exception.getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
    }

    public static List<String> getErrors(ValidationException exception){
        return exception.getMessage().lines().toList();
    }

//    public static List<String> getErrors(DisabledException exception) {
//        return exception.getMessage().lines().toList();
//    }
}
