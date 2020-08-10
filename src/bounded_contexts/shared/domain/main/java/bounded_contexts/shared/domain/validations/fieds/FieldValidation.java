package bounded_contexts.shared.domain.validations.fieds;

import bounded_contexts.shared.domain.exceptions.DomainErrorDetails;
import bounded_contexts.shared.domain.validations.Validation;

import java.util.Optional;

public abstract class FieldValidation implements Validation {

    protected String rootObject;
    protected String fieldName;
    protected Object fieldValueToValidate;

    public FieldValidation(Object rootObject, String fieldName, Object fieldValueToValidate) {
        if (rootObject != null)
            this.rootObject = rootObject.getClass().getSimpleName();
        this.fieldName = fieldName;
        this.fieldValueToValidate = fieldValueToValidate;
    }

    protected abstract boolean isValid(Object fieldValueToValidate);

    protected abstract String getMessage();

    @Override
    public Optional<DomainErrorDetails> validate() {
        if (isValid(fieldValueToValidate))
            return Optional.empty();
        DomainErrorDetails error = new DomainErrorDetails(rootObject, fieldName, fieldValueToValidate, getMessage());
        return Optional.of(error);
    }
}
