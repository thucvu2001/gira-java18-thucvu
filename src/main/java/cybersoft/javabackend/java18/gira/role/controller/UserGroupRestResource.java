package cybersoft.javabackend.java18.gira.role.controller;

import cybersoft.javabackend.java18.gira.common.model.ResponseDTO;
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
@RequestMapping("/api/v1/user-group")
public class UserGroupRestResource {
    private final UserGroupService userGroupService;

    public UserGroupRestResource(UserGroupService service) {
        this.userGroupService = service;
    }

    @GetMapping("/find-all-user-group")
    public ResponseEntity<ResponseDTO> findAllUsers() {
        return ResponseUtils.get(userGroupService.findAllDto(UserGroupDTO.class), HttpStatus.OK);
    }

    @GetMapping("/find-all-user-group-include-users")
    public ResponseEntity<ResponseDTO> findAllUserGroupIncludedUsers() {
        return ResponseUtils.get(userGroupService.findAllDtoIncludeUsers(), HttpStatus.OK);
    }

    @PostMapping("/save-user-group")
    public ResponseEntity<ResponseDTO> saveUser(@RequestBody @Valid UserGroupDTO userGroupDTO) {
        return ResponseUtils.get(userGroupService.save(userGroupDTO, UserGroup.class, UserGroupDTO.class), HttpStatus.CREATED);
    }

    @PostMapping("/add-users/{user-group-id}")
    public ResponseEntity<ResponseDTO> addUsers(@RequestBody List<UUID> ids, @PathVariable("user-group-id") UUID userGroupId) {
        return ResponseUtils.get(userGroupService.addUsers(userGroupId, ids), HttpStatus.OK);
    }

    @DeleteMapping("/remove-users/{user-group-id}")
    public ResponseEntity<ResponseDTO> removeUsers(@RequestBody List<UUID> ids, @PathVariable("user-group-id") UUID userGroupId) {
        return ResponseUtils.get(userGroupService.removeUsers(userGroupId, ids), HttpStatus.OK);
    }
}
