package cybersoft.javabackend.java18.gira.common.service;

import cybersoft.javabackend.java18.gira.common.model.BaseEntity;
import cybersoft.javabackend.java18.gira.common.util.GiraMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GenericService<T extends BaseEntity, D, I> {
    // T: entity
    // D: DTO
    // I: Id (kieu cua khoa chinh)
    JpaRepository<T, I> getRoleRepository(); // Factory Method
    GiraMapper getGiraMapper();

    default List<T> findAll() {
        return getRoleRepository().findAll();
    }

    default List<T> findAllWithPageable(Pageable pageable) {
        return getRoleRepository().findAll(pageable).stream().toList();
    }

    // ham findAll tra ra DTO
    default List<D> findAllDto(Class<D> clazz) {
        return getRoleRepository()
                .findAll()
                .stream()
                .map(model -> getGiraMapper().map(model, clazz))
                .toList();
    }

    default List<D> findAllDtoWithPageable(Pageable pageable, Class<D> clazz) {
        return getRoleRepository()
                .findAll(pageable)
                .stream()
                .map(model -> getGiraMapper().map(model, clazz))
                .toList();
    }

    default List<T> findByIds(List<I> ids) {
        return getRoleRepository().findAllById(ids);
    }

    default Optional<T> findById(I id) {
        return getRoleRepository().findById(id);
    }

    default T save(T entity) {
        return getRoleRepository().save(entity);
    }

    default D save(D dto, Class<T> modelClass, Class<D> dtoClass) {
        T model = getGiraMapper().map(dto, modelClass); // map DTO to entity
        T saveModel = getRoleRepository().save(model); // save entity
        return getGiraMapper().map(saveModel, dtoClass); // map top DTO and return
    }

    default D update(D dto, I id, Class<T> modelClass, Class<D> dtoClass) {
        getRoleRepository().deleteById(id);
        T entity = getGiraMapper().map(dto, modelClass);
        entity.setId((UUID) id);
        getRoleRepository().saveAndFlush(entity);
        return getGiraMapper().map(entity, dtoClass);
    }

    default D deleteById(I id) {
        getRoleRepository().deleteById(id);
        return null;
    }
}
