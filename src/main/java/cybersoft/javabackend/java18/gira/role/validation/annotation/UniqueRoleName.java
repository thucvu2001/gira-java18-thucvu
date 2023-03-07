package cybersoft.javabackend.java18.gira.role.validation.annotation;

import cybersoft.javabackend.java18.gira.role.validation.validator.UniqueRoleNameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueRoleNameValidator.class) //class xử lí logic của annotation
@Retention(RetentionPolicy.RUNTIME) // annotation co tac dung tai giai doan nao cua ung dung? - Runtime
@Target(ElementType.FIELD) // danh dau cho thuoc tinh, phuong thuc, class, ...?
public @interface UniqueRoleName {
    String message() default "{role.name.existed}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
