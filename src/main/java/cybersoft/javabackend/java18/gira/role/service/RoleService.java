package cybersoft.javabackend.java18.gira.role.service;

import cybersoft.javabackend.java18.gira.common.service.GenericService;
import cybersoft.javabackend.java18.gira.common.util.GiraMapper;
import cybersoft.javabackend.java18.gira.role.dto.RoleDTO;
import cybersoft.javabackend.java18.gira.role.dto.RoleWithOperationsDTO;
import cybersoft.javabackend.java18.gira.role.dto.RoleWithUserGroupDTO;
import cybersoft.javabackend.java18.gira.role.model.Operation;
import cybersoft.javabackend.java18.gira.role.model.Role;
import cybersoft.javabackend.java18.gira.role.model.UserGroup;
import cybersoft.javabackend.java18.gira.role.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cybersoft.javabackend.java18.gira.common.exception.EntityNotFoundException;
import javax.validation.ValidationException;
import java.util.List;
import java.util.UUID;

public interface RoleService extends GenericService<Role, RoleDTO, UUID> {
    Role update(Role role, String code);

    void deleteByCode(String code);

    RoleDTO save(RoleDTO dto);

    RoleWithOperationsDTO addOperation(UUID roleId, List<UUID> operationIds);

    RoleWithOperationsDTO removeOperation(UUID roleId, List<UUID> operationIds);

    RoleWithUserGroupDTO addUserGroup(UUID roleId, List<UUID> userGroupIds);

    RoleWithUserGroupDTO removeUserGroup(UUID roleId, List<UUID> userGroupIds);
}

@Service
@Transactional
@RequiredArgsConstructor
class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final GiraMapper mapper;
    private final OperationService operationService;
    private final UserGroupService userGroupService;

    @Override
    public Role update(Role role, String code) {
        Role curRole = roleRepository.findByCode(code);
        curRole.setName(role.getName());
        curRole.setDescription(role.getDescription());
        return roleRepository.save(curRole); // return curRole;
    }

    @Override
    public void deleteByCode(String code) {
        roleRepository.deleteByCode(code);
    }

    @Override
    public RoleDTO save(RoleDTO dto) {
        Role model = mapper.map(dto, Role.class); // chuyen DTO thanh entity
        Role saveModel = roleRepository.save(model); // luu entity vao db
        return mapper.map(saveModel, RoleDTO.class); // chuyen lai thanh DTO va tra ra
    }

    @Override
    public RoleWithOperationsDTO addOperation(UUID roleId, List<UUID> operationIds) {
        Role curRole = roleRepository.findById(roleId)
                .orElseThrow(() -> new EntityNotFoundException("Role", roleId));
        List<Operation> operations = operationService.findByIds(operationIds);
        operations.forEach(curRole::addOperation);
        return mapper.map(curRole, RoleWithOperationsDTO.class);
    }

    @Override
    public RoleWithOperationsDTO removeOperation(UUID roleId, List<UUID> operationIds) {
        Role curRole = roleRepository.findById(roleId)
                .orElseThrow(() -> new EntityNotFoundException("Role", roleId));
        List<Operation> operations = operationService.findByIds(operationIds);
        operations.forEach(curRole::removeOperation);
        return mapper.map(curRole, RoleWithOperationsDTO.class);
    }

    @Override
    public RoleWithUserGroupDTO addUserGroup(UUID roleId, List<UUID> userGroupIds) {
        Role curRole = roleRepository.findById(roleId)
                .orElseThrow(() -> new EntityNotFoundException("Role", roleId));
        List<UserGroup> userGroups = userGroupService.findByIds(userGroupIds);
        userGroups.forEach(curRole::addUserGroup);
        return mapper.map(curRole, RoleWithUserGroupDTO.class);
    }

    @Override
    public RoleWithUserGroupDTO removeUserGroup(UUID roleId, List<UUID> userGroupIds) {
        Role curRole = roleRepository.findById(roleId)
                .orElseThrow(() -> new EntityNotFoundException("Role", roleId));
        List<UserGroup> userGroups = userGroupService.findByIds(userGroupIds);
        userGroups.forEach(curRole::removeUserGroup);
        return mapper.map(curRole, RoleWithUserGroupDTO.class);
    }

    @Override
    public JpaRepository<Role, UUID> getRepository() {
        return this.roleRepository;
    }

    @Override
    public GiraMapper getGiraMapper() {
        return this.mapper;
    }
}
