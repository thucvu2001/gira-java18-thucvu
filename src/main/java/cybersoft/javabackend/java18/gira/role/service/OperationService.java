package cybersoft.javabackend.java18.gira.role.service;

import cybersoft.javabackend.java18.gira.common.service.GenericService;
import cybersoft.javabackend.java18.gira.common.util.GiraMapper;
import cybersoft.javabackend.java18.gira.role.dto.OperationDTO;
import cybersoft.javabackend.java18.gira.role.model.Operation;
import cybersoft.javabackend.java18.gira.role.repository.OperationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface OperationService extends GenericService<Operation, OperationDTO, UUID> {

    List<Operation> findAll(List<UUID> operationIds);
}

@Service
@Transactional
@RequiredArgsConstructor
class OperationServiceImpl implements OperationService {
    private final OperationRepository operationRepository;
    private final GiraMapper mapper;

    @Override
    public JpaRepository<Operation, UUID> getRepository() {
        return this.operationRepository;
    }

    @Override
    public GiraMapper getGiraMapper() {
        return this.mapper;
    }

    @Override
    public List<Operation> findAll(List<UUID> operationIds) {
        return operationRepository.findAllById(operationIds);
    }
}