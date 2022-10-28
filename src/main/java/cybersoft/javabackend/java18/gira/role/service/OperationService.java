package cybersoft.javabackend.java18.gira.role.service;

import cybersoft.javabackend.java18.gira.common.service.GenericService;
import cybersoft.javabackend.java18.gira.role.dto.OperationDTO;
import cybersoft.javabackend.java18.gira.role.model.Operation;
import cybersoft.javabackend.java18.gira.role.repository.OperationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface OperationService extends GenericService<Operation, OperationDTO, UUID> {

    List<Operation> findAll(List<UUID> operationIds);
}

@org.springframework.stereotype.Service
@Transactional
class OperationServiceImpl implements OperationService {

    private final OperationRepository operationRepository;

    private final ModelMapper mapper;

    public OperationServiceImpl(OperationRepository operationRepository, ModelMapper modelMapper) {
        this.operationRepository = operationRepository;
        this.mapper = modelMapper;
    }

    @Override
    public JpaRepository<Operation, UUID> getRepository() {
        return this.operationRepository;
    }

    @Override
    public ModelMapper getMapper() {
        return this.mapper;
    }

    @Override
    public List<Operation> findAll(List<UUID> operationIds) {
        return operationRepository.findAllById(operationIds);
    }
}