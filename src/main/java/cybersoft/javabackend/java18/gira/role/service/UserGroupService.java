package cybersoft.javabackend.java18.gira.role.service;

import cybersoft.javabackend.java18.gira.common.service.GenericService;
import cybersoft.javabackend.java18.gira.common.util.GiraMapper;
import cybersoft.javabackend.java18.gira.role.dto.UserGroupDTO;
import cybersoft.javabackend.java18.gira.role.dto.UserGroupWithUsersDTO;
import cybersoft.javabackend.java18.gira.role.model.UserGroup;
import cybersoft.javabackend.java18.gira.role.repository.UserGroupRepository;
import cybersoft.javabackend.java18.gira.user.model.User;
import cybersoft.javabackend.java18.gira.user.service.UserService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;
import java.util.List;
import java.util.UUID;

public interface UserGroupService extends GenericService<UserGroup, UserGroupDTO, UUID> {

    UserGroupWithUsersDTO addUsers(UUID userGroupId, List<UUID> ids);

    UserGroupWithUsersDTO removeUsers(UUID userGroupId, List<UUID> uuids);

    List<UserGroupWithUsersDTO> findAllDtoIncludeUsers();
}

@Service
@Transactional
class UserGroupServiceImpl implements UserGroupService {

    private final UserGroupRepository userGroupRepository;

    private final GiraMapper giraMapper;

    private final UserService userService;

    public UserGroupServiceImpl(UserGroupRepository repository, GiraMapper mapper, UserService userService) {
        this.userGroupRepository = repository;
        this.giraMapper = mapper;
        this.userService = userService;
    }

    @Override
    public JpaRepository<UserGroup, UUID> getRoleRepository() {
        return this.userGroupRepository;
    }

    @Override
    public GiraMapper getGiraMapper() {
        return this.giraMapper;
    }

    @Override
    public UserGroupWithUsersDTO addUsers(UUID userGroupId, List<UUID> ids) {
        UserGroup userGroup = userGroupRepository.findById(userGroupId)
                .orElseThrow(() -> new ValidationException("UserGroup is not existed"));
        List<User> users = userService.findByIds(ids);
        users.forEach(userGroup::addUser);
        return giraMapper.map(userGroup, UserGroupWithUsersDTO.class);
    }

    @Override
    public UserGroupWithUsersDTO removeUsers(UUID userGroupId, List<UUID> ids) {
        UserGroup userGroup = userGroupRepository.findById(userGroupId)
                .orElseThrow(() -> new ValidationException("UserGroup is not existed"));
        List<User> users = userService.findByIds(ids);
        users.forEach(userGroup::removeUser);
        return giraMapper.map(userGroup, UserGroupWithUsersDTO.class);
    }

    @Override
    public List<UserGroupWithUsersDTO> findAllDtoIncludeUsers() {
        return userGroupRepository.findAllWithUsers()
                .stream()
                .map(model -> giraMapper.map(model, UserGroupWithUsersDTO.class))
                .toList();
    }
}