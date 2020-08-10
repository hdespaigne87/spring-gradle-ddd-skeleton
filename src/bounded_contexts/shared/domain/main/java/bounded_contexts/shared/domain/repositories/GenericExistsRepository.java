package bounded_contexts.shared.domain.repositories;

import bounded_contexts.shared.domain.ValueObject;

public interface GenericExistsRepository<ID extends ValueObject> {

    boolean existsById(ID id);
}
