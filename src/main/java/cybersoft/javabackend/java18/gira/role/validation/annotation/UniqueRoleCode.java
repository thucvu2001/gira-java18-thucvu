package cybersoft.javabackend.java18.gira.role.validation.annotation;


import cybersoft.javabackend.java18.gira.role.validation.validator.UniqueRoleCodeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueRoleCodeValidator.class)
public @interface UniqueRoleCode {

    String message() default "{role.code.existed}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
