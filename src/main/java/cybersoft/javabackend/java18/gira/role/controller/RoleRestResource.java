package cybersoft.javabackend.java18.gira.role.controller;

import cybersoft.javabackend.java18.gira.common.model.ResponseDTO;
import cybersoft.javabackend.java18.gira.common.util.ResponseUtils;
import cybersoft.javabackend.java18.gira.role.dto.RoleDTO;
import cybersoft.javabackend.java18.gira.role.model.Role;
import cybersoft.javabackend.java18.gira.role.service.RoleService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController // se tra ve json
@RequestMapping("api/v1/roles")
public class RoleRestResource {
    private final RoleService roleService;

    public RoleRestResource(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/find-all")
    public ResponseEntity<ResponseDTO> findAll() {
        return ResponseUtils.get(roleService.findAllDto(RoleDTO.class), HttpStatus.OK);
    }

    @GetMapping("/find-all-with-paging")
    public ResponseEntity<ResponseDTO> findAllDtoPaging(@RequestParam("size") int size, @RequestParam("index") int index) {
        return ResponseUtils.get(
                roleService.findAllDtoWithPageable(Pageable.ofSize(size).withPage(index), RoleDTO.class)
                , HttpStatus.OK
        );
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseDTO> save(@RequestBody @Valid RoleDTO roleDTO) {
        return ResponseUtils.get(roleService.save(roleDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update/{role-id}")
    public ResponseEntity<ResponseDTO> delete(@RequestBody @Valid RoleDTO roleDTO, @PathVariable("role-id") UUID roleId) {
        return ResponseUtils.get(roleService.update(roleDTO, roleId, Role.class, RoleDTO.class), HttpStatus.OK);
    }

    @PostMapping("/add-operations/{role-id}")
    public ResponseEntity<ResponseDTO> addOperation(@RequestBody List<UUID> ids, @PathVariable("role-id") UUID roleId) {
        return ResponseUtils.get(roleService.addOperation(roleId, ids), HttpStatus.OK);
    }

    @DeleteMapping("/remove-operations/{role-id}")
    public ResponseEntity<ResponseDTO> removeOperation(@RequestBody List<UUID> ids, @PathVariable("role-id") UUID roleId) {
        return ResponseUtils.get(roleService.removeOperation(roleId, ids), HttpStatus.OK);
    }
}
