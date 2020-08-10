package bounded_contexts.shared.domain.validations;

import bounded_contexts.shared.domain.exceptions.DomainErrorDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class Validator {

    private List<Validation> validators;

    public Validator() {
        this.validators = new ArrayList<>();
    }

    public List<DomainErrorDetails> validate() {
        List<DomainErrorDetails> errors = new ArrayList<>();
        for (Validation validation : validators) {
            Optional<DomainErrorDetails> error = validation.validate();
            if (error.isPresent())
                errors.add(error.get());
        }
        validators.clear();
        return errors;
    }

    public Validator ensureCustomValidation(Validation validation) {
        if (validators == null)
            validators = new ArrayList<>();
        validators.add(validation);
        return this;
    }
}
