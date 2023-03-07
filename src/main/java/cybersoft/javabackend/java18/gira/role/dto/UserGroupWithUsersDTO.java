package cybersoft.javabackend.java18.gira.role.dto;

import cybersoft.javabackend.java18.gira.role.validation.annotation.UniqueRoleCode;
import cybersoft.javabackend.java18.gira.role.validation.annotation.UniqueRoleName;
import cybersoft.javabackend.java18.gira.user.dto.UserDTO;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserGroupWithUsersDTO {
    private UUID id;
    @NotBlank
    @UniqueRoleName
    @Length(min = 5, max = 100, message = "{userGroup.name.size}")
    private String name;
    @NotBlank
    @UniqueRoleCode
    @Length(min = 3, max = 10, message = "{userGroup.code.size}")
    private String code;
    @NotBlank(message = "{userGroup.description.blank}")
    private String description;
    private Set<UserDTO> users = new LinkedHashSet<>();
}