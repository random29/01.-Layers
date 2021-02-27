package tech.itpark.repository;

import java.util.List;
import java.util.Optional;

//  Crud
public interface CrudRepository<T, ID> {
    List<T> findAll();
    Optional<T> findById(ID id); // Optional
    // в старых всгда будет UserEntity = null.
    T save(T entity);
    boolean removeById(ID id);
}
