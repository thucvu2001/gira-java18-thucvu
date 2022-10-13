package cybersoft.javabackend.java18.gira.role.validation.validator;

import cybersoft.javabackend.java18.gira.role.model.Role;
import cybersoft.javabackend.java18.gira.role.repository.RoleRepository;
import cybersoft.javabackend.java18.gira.role.validation.annotation.UniqueRoleName;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class UniqueRoleNameValidator implements ConstraintValidator<UniqueRoleName, String> {

    private final RoleRepository repository;
    private String message;

    public UniqueRoleNameValidator(RoleRepository roleRepository) {
        this.repository = roleRepository;
    }

    @Override
    public void initialize(UniqueRoleName constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        Optional<Role> roleOptional = repository.findByName(name);

        if (roleOptional.isEmpty()) { // neu chua duoc su dung, thi co the dung => return true
            return true;
        }

        // nếu đã được sử dụng thì tạo 1 lỗi (lỗi chính là message trong class @UniqueRoleName) và return false
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();

        return false;
    }
}
