package cybersoft.javabackend.java18.gira.role.dto;

import cybersoft.javabackend.java18.gira.user.dto.UserDTO;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserGroupWithUsersDTO implements Serializable {
    private UUID id;
    @Length(min = 5, max = 100, message = "UserGroup name must have length between {min} and {max}")
    private String name;
    @Length(min = 3, max = 10, message = "UserGroup code must have length between {min} and {max}")
    private String code;
    @NotBlank
    private String description;
    private Set<UserDTO> users = new LinkedHashSet<>();
}