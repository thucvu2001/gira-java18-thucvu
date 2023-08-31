package cybersoft.javabackend.java18.gira.role.controller;

import cybersoft.javabackend.java18.gira.common.model.ResponseDTO;
import cybersoft.javabackend.java18.gira.common.util.ResponseUtils;
import cybersoft.javabackend.java18.gira.role.dto.ModuleDTO;
import cybersoft.javabackend.java18.gira.role.model.Module;
import cybersoft.javabackend.java18.gira.role.service.ModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/module")
@RequiredArgsConstructor
public class ModuleRestResource {

    private final ModuleService moduleService;

    @GetMapping("/find-all")
    public ResponseEntity<ResponseDTO> findAllModule() {
        return ResponseUtils.get(moduleService.findAllDto(ModuleDTO.class), HttpStatus.OK);
    }

    @PostMapping("/save-module")
    public ResponseEntity<ResponseDTO> saveModule(@RequestBody @Valid ModuleDTO dto) {
        return ResponseUtils.get(moduleService.save(dto, Module.class, ModuleDTO.class), HttpStatus.OK);
    }

    @PutMapping("/update/{module-id}")
    public ResponseEntity<ResponseDTO> updateModule(@RequestBody @Valid ModuleDTO moduleDTO, @PathVariable("module-id") UUID moduleId) {
        return ResponseUtils.get(moduleService.update(moduleDTO, moduleId, Module.class, ModuleDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{module-id}")
    public ResponseEntity<ResponseDTO> deleteModule(@PathVariable("module-id") UUID moduleId) {
        return ResponseUtils.get(moduleService.deleteById(moduleId), HttpStatus.OK);
    }
}
