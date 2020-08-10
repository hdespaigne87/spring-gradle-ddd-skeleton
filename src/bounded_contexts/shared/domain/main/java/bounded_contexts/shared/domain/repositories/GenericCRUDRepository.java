package bounded_contexts.shared.domain.repositories;

import bounded_contexts.shared.domain.AggregateRoot;
import bounded_contexts.shared.domain.ValueObject;

public interface GenericCRUDRepository<T extends AggregateRoot, ID extends ValueObject> extends GenericReadRepository<T, ID>, GenericWriteRepository<T, ID> {

}
