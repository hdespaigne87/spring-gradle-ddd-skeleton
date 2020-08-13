package bounded_contexts.shared.domain.repositories;

import bounded_contexts.shared.domain.AggregateRoot;
import bounded_contexts.shared.domain.ValueObject;

public interface GenericWriteRepository<T extends AggregateRoot, ID extends ValueObject> extends GenericSaveRepository<T>, GenericDeleteRepository<T> {

}
