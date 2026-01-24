package cybersoft.javabackend.java18.gira.common.service;

import cybersoft.javabackend.java18.gira.common.model.BaseEntity;
import cybersoft.javabackend.java18.gira.common.util.GiraMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GenericService<T extends BaseEntity, D, I> {
    /**
     * Factory method to get the repository for this service
     * @return JpaRepository instance
     */
    JpaRepository<T, I> getRepository();

    /**
     * Factory method to get the mapper for this service
     * @return GiraMapper instance
     */
    GiraMapper getGiraMapper();

    default List<T> findAll() {
        return getRepository().findAll();
    }

    default List<T> findAllWithPageable(Pageable pageable) {
        return getRepository().findAll(pageable).stream().toList();
    }

    default List<D> findAllDto(Class<D> clazz) {
        return getRepository()
                .findAll()
                .stream()
                .map(model -> getGiraMapper().map(model, clazz))
                .toList();
    }

    default List<D> findAllDtoWithPageable(Pageable pageable, Class<D> clazz) {
        return getRepository()
                .findAll(pageable)
                .stream()
                .map(model -> getGiraMapper().map(model, clazz))
                .toList();
    }

    default List<T> findByIds(List<I> ids) {
        return getRepository().findAllById(ids);
    }

    default Optional<T> findById(I id) {
        return getRepository().findById(id);
    }

    default T save(T entity) {
        return getRepository().save(entity);
    }

    default D save(D dto, Class<T> modelClass, Class<D> dtoClass) {
        T model = getGiraMapper().map(dto, modelClass);
        T savedModel = getRepository().save(model);
        return getGiraMapper().map(savedModel, dtoClass);
    }

    default D update(D dto, I id, Class<T> modelClass, Class<D> dtoClass) {
        T existingEntity = getRepository().findById(id)
                .orElseThrow(() -> new cybersoft.javabackend.java18.gira.common.exception.EntityNotFoundException(
                        modelClass.getSimpleName(), id));
        getGiraMapper().map(dto, existingEntity);
        T updatedEntity = getRepository().save(existingEntity);
        return getGiraMapper().map(updatedEntity, dtoClass);
    }

    default void deleteById(I id) {
        getRepository().deleteById(id);
    }
}
