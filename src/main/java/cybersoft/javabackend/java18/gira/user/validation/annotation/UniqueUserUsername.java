package cybersoft.javabackend.java18.gira.user.validation.annotation;


import cybersoft.javabackend.java18.gira.user.validation.validator.UniqueUserUsernameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Constraint(validatedBy = UniqueUserUsernameValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface UniqueUserUsername {
    String message() default "{user.username.existed}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
