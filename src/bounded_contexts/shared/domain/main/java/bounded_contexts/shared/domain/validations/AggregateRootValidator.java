package bounded_contexts.shared.domain.validations;

import bounded_contexts.shared.domain.AggregateRoot;
import bounded_contexts.shared.domain.DomainEntity;
import bounded_contexts.shared.domain.exceptions.DomainErrorDetails;
import bounded_contexts.shared.domain.exceptions.DomainException;
import bounded_contexts.shared.domain.validations.fieds.FieldNotEmpty;
import bounded_contexts.shared.domain.validations.fieds.FieldNotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

public final class AggregateRootValidator extends Validator {

    private AggregateRoot aggregateRoot;

    public AggregateRootValidator(AggregateRoot aggregateRoot) {
        super();
        this.aggregateRoot = aggregateRoot;
    }

    private void validate(Validation validation) {
        Optional<DomainErrorDetails> error = validation.validate();
        if (error.isPresent())
            throw new DomainException(error.get());
    }

    public void ensureNotNull(DomainEntity domainEntity, String fieldName) {
        validate(new FieldNotNull(aggregateRoot, fieldName, domainEntity));
    }

    public void ensureNotEmpty(Collection collection, String fieldName) {
        validate(new FieldNotEmpty(aggregateRoot, fieldName, collection));
    }
}
