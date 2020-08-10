package bounded_contexts.shared.domain.validations.fieds;

public class FieldIsPositive extends FieldValidation {

    public FieldIsPositive(Object rootObject, String fieldName, Object fieldValueToValidate) {
        super(rootObject, fieldName, fieldValueToValidate);
    }

    @Override
    protected boolean isValid(Object fieldValueToValidate) {
        if (fieldValueToValidate instanceof Number)
            return ((Number) fieldValueToValidate).doubleValue() > 0;
        return true;
    }

    @Override
    protected String getMessage() {
        return "Must be positive (greater than zero)";
    }
}
