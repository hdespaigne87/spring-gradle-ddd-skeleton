package bounded_contexts.shared.domain.repositories;

import bounded_contexts.shared.domain.AggregateRoot;

public interface GenericSaveRepository<T extends AggregateRoot> {

    void save(T aggregateRoot);
}
