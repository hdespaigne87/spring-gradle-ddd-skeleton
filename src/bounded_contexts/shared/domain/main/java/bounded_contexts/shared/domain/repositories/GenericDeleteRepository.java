package bounded_contexts.shared.domain.repositories;

import bounded_contexts.shared.domain.ValueObject;

public interface GenericDeleteRepository<ID extends ValueObject> {

    void deleteById(ID id);
}
