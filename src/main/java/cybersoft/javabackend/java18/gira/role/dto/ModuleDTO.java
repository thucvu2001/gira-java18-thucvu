package cybersoft.javabackend.java18.gira.role.dto;


import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModuleDTO {
    private UUID id;

    @NotBlank
    @Length(min = 5, max = 100, message = "{module.name.size}")
    private String name;

    @NotBlank
    @Length(min = 5, max = 10, message = "{module.code.size}")
    private String code;

    @NotBlank(message = "{module.description.blank}")
    private String description;
}
