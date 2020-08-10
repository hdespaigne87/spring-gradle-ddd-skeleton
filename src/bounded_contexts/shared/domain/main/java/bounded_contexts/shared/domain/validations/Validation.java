package bounded_contexts.shared.domain.validations;

import bounded_contexts.shared.domain.exceptions.DomainErrorDetails;

import java.util.Optional;

public interface Validation {

    Optional<DomainErrorDetails> validate();
}
