package bounded_contexts.shared.domain.exceptions;

import bounded_contexts.shared.domain.ValueObject;

import java.util.UUID;

public final class DomainEntityNotExistsException extends RuntimeException {

    public DomainEntityNotExistsException(Class<?> entityClass, ValueObject<UUID> entityId) {
        super(String.format("The %s <%s> doesn't exist", entityClass.getSimpleName(), entityId.getValue()));
    }
}
