package cybersoft.javabackend.java18.gira.role.boundary;

import cybersoft.javabackend.java18.gira.common.util.ResponseUtils;
import cybersoft.javabackend.java18.gira.role.dto.UserGroupDTO;
import cybersoft.javabackend.java18.gira.role.model.UserGroup;
import cybersoft.javabackend.java18.gira.role.service.UserGroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user-group")
public class UserGroupRestResource {

    private final UserGroupService userGroupService;

    public UserGroupRestResource(UserGroupService service) {
        this.userGroupService = service;
    }

    @GetMapping
    public Object findAll() {
        return ResponseUtils.get(
                userGroupService.findAllDto(UserGroupDTO.class),
                HttpStatus.OK
        );
    }

    @GetMapping("/include-users")
    public ResponseEntity<?> findAllUserGroupIncludedUsers() {
        return ResponseUtils.get(userGroupService.findAllDtoIncludeUsers(), HttpStatus.OK);
    }

    @PostMapping
    public Object save(@RequestBody @Valid UserGroupDTO dto) {
        return ResponseUtils.get(
                userGroupService.save(dto, UserGroup.class, UserGroupDTO.class),
                HttpStatus.CREATED
        );
    }

    @PostMapping("{user-group-id}/add-user")
    public ResponseEntity<?> addUsers(@RequestBody List<UUID> ids, @PathVariable("user-group-id") UUID userGroupId) {
        return ResponseUtils.get(userGroupService.addUsers(userGroupId, ids), HttpStatus.OK);
    }
}
