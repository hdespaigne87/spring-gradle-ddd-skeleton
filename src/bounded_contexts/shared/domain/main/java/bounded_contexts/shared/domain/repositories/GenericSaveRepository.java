package bounded_contexts.shared.domain.repositories;

public interface GenericSaveRepository<T> {

    void save(T entity);
}
