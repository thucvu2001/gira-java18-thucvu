package cybersoft.javabackend.java18.gira.user.validation.annotation;


import cybersoft.javabackend.java18.gira.user.validation.validator.UniqueUserEmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Constraint(validatedBy = UniqueUserEmailValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueUserEmail {
    String message() default "{user.email.existed}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
