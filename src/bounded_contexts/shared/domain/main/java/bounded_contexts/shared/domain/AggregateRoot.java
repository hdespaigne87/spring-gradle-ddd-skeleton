package bounded_contexts.shared.domain;

import bounded_contexts.shared.domain.bus.event.DomainEvent;
import bounded_contexts.shared.domain.validations.AggregateRootValidator;

import java.util.*;

public abstract class AggregateRoot {

    public abstract ValueObject<UUID> getId();

    protected AggregateRootValidator validator = new AggregateRootValidator(this);

    private List<DomainEvent> recordedDomainEvents = new LinkedList<>();

    final public List<DomainEvent> pullDomainEvents() {
        final List<DomainEvent> recordedDomainEvents = new LinkedList<>(this.recordedDomainEvents);
        this.recordedDomainEvents.clear();
        return Collections.unmodifiableList(recordedDomainEvents);
    }

    final protected void record(DomainEvent event) {
        recordedDomainEvents.add(event);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AggregateRoot)) return false;
        AggregateRoot that = (AggregateRoot) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
