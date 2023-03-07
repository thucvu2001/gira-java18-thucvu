package cybersoft.javabackend.java18.gira.role.controller;

import cybersoft.javabackend.java18.gira.common.model.ResponseDTO;
import cybersoft.javabackend.java18.gira.common.util.ResponseUtils;
import cybersoft.javabackend.java18.gira.role.dto.OperationDTO;
import cybersoft.javabackend.java18.gira.role.model.Operation;
import cybersoft.javabackend.java18.gira.role.service.OperationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/operations")
public class OperationRestResource {
    private final OperationService operationService;

    public OperationRestResource(OperationService service) {
        this.operationService = service;
    }

    @GetMapping("/find-all")
    public ResponseEntity<ResponseDTO> findAll() {
        return ResponseUtils.get(operationService.findAllDto(OperationDTO.class), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseDTO> save(@RequestBody @Valid OperationDTO dto) {
        return ResponseUtils.get(operationService.save(dto, Operation.class, OperationDTO.class), HttpStatus.CREATED);
    }

    @PutMapping("/update/{operation-id}")
    public ResponseEntity<ResponseDTO> update(@RequestBody @Valid OperationDTO dto, @PathVariable("operation-id") UUID operationId) {
        return ResponseUtils.get(operationService.update(dto, operationId, Operation.class, OperationDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{operation-id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable("operation-id") UUID operationId) {
        return ResponseUtils.get(operationService.deleteById(operationId), HttpStatus.OK);
    }
}
