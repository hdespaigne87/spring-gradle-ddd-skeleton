package bounded_contexts.shared.domain.validations;

import bounded_contexts.shared.domain.ValueObject;
import bounded_contexts.shared.domain.exceptions.DomainErrorDetails;
import bounded_contexts.shared.domain.exceptions.DomainException;
import bounded_contexts.shared.domain.validations.fieds.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

public final class ValueObjectValidator extends Validator {

    private ValueObject valueObject;
    private String fieldName;
    private Object value;

    public ValueObjectValidator(ValueObject valueObject) {
        super();
        this.valueObject = valueObject;
        this.fieldName = "value";
        this.value = valueObject.getValue();
    }

    private void validate(Validation validation) {
        Optional<DomainErrorDetails> error = validation.validate();
        if (error.isPresent())
            throw new DomainException(error.get());
    }

    public void ensureNotNull() {
        validate(new FieldNotNull(valueObject, fieldName, value));
    }

    public void ensureNotEmpty() {
        validate(new FieldNotEmpty(valueObject, fieldName, value));
    }

    public void ensureNotBlank() {
        validate(new FieldNotBlank(valueObject, fieldName, value));
    }

    public void ensureIsTrue() {
        validate(new FieldIsTrue(valueObject, fieldName, value));
    }

    public void ensureIsFalse() {
        validate(new FieldIsFalse(valueObject, fieldName, value));
    }

    public void ensureMaxSizeIs(int max) {
        validate(new FieldMaxSizeIs(valueObject, fieldName, value, max));
    }

    public void ensureMinSizeIs(int min) {
        validate(new FieldMinSizeIs(valueObject, fieldName, value, min));
    }

    public void ensureSizeIsBetween(int min, int max) {
        validate(new FieldSizeIsBetween(valueObject, fieldName, value, min, max));
    }

    public void ensureMinValueIs(double min) {
        validate(new FieldMinValueIs(valueObject, fieldName, value, min));
    }

    public void ensureMaxValueIs(double max) {
        validate(new FieldMaxValueIs(valueObject, fieldName, value, max));
    }

    public void ensureIsBetween(double min, double max) {
        validate(new FieldNumericValueIsBetween(valueObject, fieldName, value, min, max));
    }

    public void ensureIsBetween(LocalDateTime min, LocalDateTime max) {
        validate(new FieldDateTimeValueIsBetween(valueObject, fieldName, value, min, max));
    }

    public void ensureIsEmail() {
        validate(new FieldIsEmail(valueObject, fieldName, value));
    }

    public void ensureIsPositive() {
        validate(new FieldIsPositive(valueObject, fieldName, value));
    }

    public void ensureIsPositiveOrZero() {
        validate(new FieldIsPositiveOrZero(valueObject, fieldName, value));
    }

    public void ensureIsNegative() {
        validate(new FieldIsNegative(valueObject, fieldName, value));
    }

    public void ensureIsNegativeOrZero() {
        validate(new FieldIsNegativeOrZero(valueObject, fieldName, value));
    }

    public void ensureIsPast() {
        validate(new FieldIsPast(valueObject, fieldName, value));
    }

    public void ensureIsPastOrPresent() {
        validate(new FieldIsPastOrPresent(valueObject, fieldName, value));
    }

    public void ensureIsFuture() {
        validate(new FieldIsFuture(valueObject, fieldName, value));
    }

    public void ensureIsFutureOrPresent() {
        validate(new FieldIsFutureOrPresent(valueObject, fieldName, value));
    }

    public void ensureIsUrlHttp() {
        validate(new FieldIsUrlHttp(valueObject, fieldName, value));
    }
}
