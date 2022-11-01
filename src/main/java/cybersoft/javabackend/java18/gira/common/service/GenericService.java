package cybersoft.javabackend.java18.gira.common.service;

import cybersoft.javabackend.java18.gira.common.model.BaseEntity;
import cybersoft.javabackend.java18.gira.common.util.GiraMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GenericService<T extends BaseEntity, D, I> {
    // T: entity
    // D: DTO
    // I: Id (kieu cua khoa chinh)
    JpaRepository<T, I> getRepository(); // Factory Method

    GiraMapper getGiraMapper();

    default List<T> findAll() {
        return getRepository().findAll();
    }

    default List<T> findAll(Pageable pageable) {
        return getRepository().findAll(pageable).stream().toList();
    }

    // ham findAll tra ra DTO
    default List<D> findAllDto(Class<D> clazz) {
        return getRepository()
                .findAll()
                .stream()
                .map(model -> getGiraMapper().map(model, clazz))
                .toList();
    }

    default List<D> findAllDto(Pageable pageable, Class<D> clazz) {
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
        T saveModel = getRepository().save(model);
        return getGiraMapper().map(saveModel, dtoClass);
    }

    default void deleteById(I id) {
        getRepository().deleteById(id);
    }

    default T update(T entity) {
        return getRepository().save(entity);
    }
}
