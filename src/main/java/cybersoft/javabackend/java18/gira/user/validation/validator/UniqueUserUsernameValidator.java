package cybersoft.javabackend.java18.gira.user.validation.validator;

import cybersoft.javabackend.java18.gira.user.model.User;
import cybersoft.javabackend.java18.gira.user.repository.UserRepository;
import cybersoft.javabackend.java18.gira.user.validation.annotation.UniqueUserUsername;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUserUsernameValidator implements ConstraintValidator<UniqueUserUsername, String> {

    private String message;

    private final UserRepository userRepository;

    public UniqueUserUsernameValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(UniqueUserUsername constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        User userOptional = userRepository.findByUsername(username).orElse(null);

        if (userOptional == null) {
            return true;
        }

        constraintValidatorContext.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }
}
