package bounded_contexts.shared.domain;

import bounded_contexts.shared.domain.validations.ValueObjectValidator;

import java.util.Objects;

public abstract class ValueObject<T> {

    protected T value;
    protected ValueObjectValidator validator;

    public ValueObject(T value) {
        this.value = value;
        this.validator = new ValueObjectValidator(this);
    }

    public final T getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValueObject<?> that = (ValueObject<?>) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        if (value == null)
            return null;
        return value.toString();
    }
}
