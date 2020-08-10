package bounded_contexts.shared.domain;

import java.util.Objects;
import java.util.UUID;

public abstract class DomainEntity {

    public abstract ValueObject<UUID> getId();

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
