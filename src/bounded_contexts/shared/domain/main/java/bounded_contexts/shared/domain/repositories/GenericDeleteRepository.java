package bounded_contexts.shared.domain.repositories;

import bounded_contexts.shared.domain.AggregateRoot;

public interface GenericDeleteRepository<T extends AggregateRoot> {

    void delete(T aggregateRoot);
}
