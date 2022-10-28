package cybersoft.javabackend.java18.gira.role.boundary;

import cybersoft.javabackend.java18.gira.common.util.ResponseUtils;
import cybersoft.javabackend.java18.gira.role.dto.UserGroupDTO;
import cybersoft.javabackend.java18.gira.role.model.UserGroup;
import cybersoft.javabackend.java18.gira.role.service.UserGroupService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user-group")
public class UserGroupResource {

    private final UserGroupService userGroupService;

    public UserGroupResource(UserGroupService service) {
        this.userGroupService = service;
    }

    @GetMapping
    public Object findAll() {
        return ResponseUtils.get(userGroupService.findAllDto(UserGroupDTO.class), HttpStatus.OK);
    }

    @PostMapping
    public Object save(@RequestBody @Valid UserGroupDTO dto) {
        return ResponseUtils.get(userGroupService.save(dto, UserGroup.class, UserGroupDTO.class), HttpStatus.CREATED);
    }
}
