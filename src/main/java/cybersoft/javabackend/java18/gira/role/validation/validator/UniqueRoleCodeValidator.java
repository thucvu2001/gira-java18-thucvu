package cybersoft.javabackend.java18.gira.role.validation.validator;

import cybersoft.javabackend.java18.gira.role.model.Role;
import cybersoft.javabackend.java18.gira.role.repository.RoleRepository;
import cybersoft.javabackend.java18.gira.role.validation.annotation.UniqueRoleCode;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class UniqueRoleCodeValidator implements ConstraintValidator<UniqueRoleCode, String> {
    // String is the type of the object to be validated

    private final RoleRepository repository;
    private String message;

    public UniqueRoleCodeValidator(RoleRepository roleRepository) {
        this.repository = roleRepository;
    }

    @Override
    public void initialize(UniqueRoleCode constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String code, ConstraintValidatorContext context) {
        Optional<Role> roleOptional = Optional.ofNullable(repository.findByCode(code));

        if (roleOptional.isEmpty()) {
            return true;
        }

        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();

        return false;
    }
}
