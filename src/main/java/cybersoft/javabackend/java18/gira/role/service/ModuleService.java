package cybersoft.javabackend.java18.gira.role.service;


import cybersoft.javabackend.java18.gira.common.service.GenericService;
import cybersoft.javabackend.java18.gira.common.util.GiraMapper;
import cybersoft.javabackend.java18.gira.role.dto.ModuleDTO;
import cybersoft.javabackend.java18.gira.role.model.Module;
import cybersoft.javabackend.java18.gira.role.repository.ModuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface ModuleService extends GenericService<Module, ModuleDTO, UUID> {

}

@Service
@Transactional
@RequiredArgsConstructor
class ModuleServiceImpl implements ModuleService {
    private final ModuleRepository moduleRepository;
    private final GiraMapper mapper;

    @Override
    public JpaRepository<Module, UUID> getRepository() {
        return this.moduleRepository;
    }

    @Override
    public GiraMapper getGiraMapper() {
        return this.mapper;
    }
}
