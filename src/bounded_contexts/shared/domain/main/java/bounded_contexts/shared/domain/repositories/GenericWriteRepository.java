package bounded_contexts.shared.domain.repositories;

import bounded_contexts.shared.domain.ValueObject;

public interface GenericWriteRepository<T, ID extends ValueObject> extends GenericSaveRepository<T>, GenericDeleteRepository<ID> {

}
