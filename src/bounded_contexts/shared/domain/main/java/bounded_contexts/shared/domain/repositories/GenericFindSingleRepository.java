package bounded_contexts.shared.domain.repositories;

import bounded_contexts.shared.domain.ValueObject;

import java.util.Optional;

public interface GenericFindSingleRepository<T, ID extends ValueObject> {

    Optional<T> findById(ID id);
}
