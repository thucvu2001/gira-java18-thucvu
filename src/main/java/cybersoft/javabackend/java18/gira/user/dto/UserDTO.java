package cybersoft.javabackend.java18.gira.user.dto;

import cybersoft.javabackend.java18.gira.user.model.User;
import cybersoft.javabackend.java18.gira.user.validation.annotation.UniqueUserEmail;
import cybersoft.javabackend.java18.gira.user.validation.annotation.UniqueUserUsername;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {
    private UUID id;

    @Length(min = 5, max = 100, message = "{user.username.size}")
    @UniqueUserUsername
    private String username;

    @Length(min = 5, max = 100, message = "{user.password.size}")
    private String password;

    @Length(min = 5, max = 100, message = "{user.email.size}")
    @UniqueUserEmail
    private String email;

    @Length(min = 5, max = 30, message = "{user.displayName.size}")
    private String displayName;

    @Length(min = 5, max = 100, message = "{user.fullName.size}")
    private String fullName;
    private String avatar;
    private User.Status status;
}