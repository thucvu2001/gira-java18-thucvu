package cybersoft.javabackend.java18.gira.role.service;

import cybersoft.javabackend.java18.gira.common.model.RoleWithOperationsDTO;
import cybersoft.javabackend.java18.gira.common.service.GenericService;
import cybersoft.javabackend.java18.gira.common.util.GiraMapper;
import cybersoft.javabackend.java18.gira.role.dto.RoleDTO;
import cybersoft.javabackend.java18.gira.role.model.Operation;
import cybersoft.javabackend.java18.gira.role.model.Role;
import cybersoft.javabackend.java18.gira.role.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;
import java.util.List;
import java.util.UUID;

public interface RoleService extends GenericService<Role, RoleDTO, UUID> {
    Role update(Role role, String code);

    void deleteByCode(String code);

    RoleDTO save(RoleDTO dto);

    RoleWithOperationsDTO addOperation(UUID roleId, List<UUID> operationIds);
}

@Service
@Transactional
class RoleServiceImpl implements RoleService {
    private final RoleRepository repository;
    private final GiraMapper mapper;
    private final OperationService operationService; // phai goi service thay vi repository

    public RoleServiceImpl(RoleRepository repository, GiraMapper mapper, OperationService operationService) {
        this.repository = repository;
        this.mapper = mapper;
        this.operationService = operationService;
    }

    @Override
    public Role update(Role role, String code) {
        Role curRole = repository.findByCode(code);
        curRole.setName(role.getName());
        curRole.setDescription(role.getDescription());
        // return curRole;
        return repository.save(curRole);
    }

    @Override
    public void deleteByCode(String code) {
        repository.deleteByCode(code);
    }

    @Override
    public RoleDTO save(RoleDTO dto) {
        Role model = mapper.map(dto, Role.class); // chuyen DTO thanh entity
        Role saveModel = repository.save(model); // luu entity vao db
        return mapper.map(saveModel, RoleDTO.class); // chuyen lai thanh DTO va tra ra
    }

    @Override
    public RoleWithOperationsDTO addOperation(UUID roleId, List<UUID> operationIds) {
        Role curRole = repository.findById(roleId).orElseThrow(() -> new ValidationException("Role is not existed"));

        List<Operation> operations = operationService.findByIds(operationIds);
        operations.forEach(curRole::addOperation); // duyet list va add tat ca vao Role
        return mapper.map(curRole, RoleWithOperationsDTO.class);
    }

    @Override
    public JpaRepository<Role, UUID> getRepository() {
        return this.repository;
    }

    @Override
    public ModelMapper getMapper() {
        return this.mapper;
    }
}
