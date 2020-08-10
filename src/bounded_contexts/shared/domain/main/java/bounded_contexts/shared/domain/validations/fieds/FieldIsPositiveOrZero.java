package bounded_contexts.shared.domain.validations.fieds;

public class FieldIsPositiveOrZero extends FieldValidation {

    public FieldIsPositiveOrZero(Object rootObject, String fieldName, Object fieldValueToValidate) {
        super(rootObject, fieldName, fieldValueToValidate);
    }

    @Override
    protected boolean isValid(Object fieldValueToValidate) {
        if (fieldValueToValidate instanceof Number)
            return ((Number) fieldValueToValidate).doubleValue() >= 0;
        return true;
    }

    @Override
    protected String getMessage() {
        return "Must be positive (greater than or equal to zero)";
    }
}
