package cybersoft.javabackend.java18.gira.role.service;

import cybersoft.javabackend.java18.gira.common.service.GenericService;
import cybersoft.javabackend.java18.gira.role.dto.UserGroupDTO;
import cybersoft.javabackend.java18.gira.role.model.UserGroup;
import cybersoft.javabackend.java18.gira.role.repository.UserGroupRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface UserGroupService extends GenericService<UserGroup, UserGroupDTO, UUID> {

}

@Service
@Transactional
class UserGroupServiceImpl implements UserGroupService {

    private final UserGroupRepository userGroupRepository;

    private final ModelMapper mapper;

    public UserGroupServiceImpl(UserGroupRepository repository, ModelMapper mapper) {
        this.userGroupRepository = repository;
        this.mapper = mapper;
    }

    @Override
    public JpaRepository<UserGroup, UUID> getRepository() {
        return this.userGroupRepository;
    }

    @Override
    public ModelMapper getMapper() {
        return this.mapper;
    }
}