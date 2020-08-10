package bounded_contexts.shared.domain.validations.fieds;

public class FieldIsNegativeOrZero extends FieldValidation {

    public FieldIsNegativeOrZero(Object rootObject, String fieldName, Object fieldValueToValidate) {
        super(rootObject, fieldName, fieldValueToValidate);
    }

    @Override
    protected boolean isValid(Object fieldValueToValidate) {
        if (fieldValueToValidate instanceof Number)
            return ((Number) fieldValueToValidate).doubleValue() <= 0;
        return true;
    }

    @Override
    protected String getMessage() {
        return "Must be negative (lower than or equal to zero)";
    }
}
