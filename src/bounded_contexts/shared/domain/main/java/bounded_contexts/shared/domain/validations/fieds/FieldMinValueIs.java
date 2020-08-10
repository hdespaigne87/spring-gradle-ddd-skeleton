package bounded_contexts.shared.domain.validations.fieds;

public class FieldMinValueIs extends FieldValidation {

    private double min;

    public FieldMinValueIs(Object rootObject, String fieldName, Object fieldValueToValidate, double min) {
        super(rootObject, fieldName, fieldValueToValidate);
        this.min = min;
    }

    @Override
    protected boolean isValid(Object fieldValueToValidate) {
        if (fieldValueToValidate instanceof Number)
            return ((Number) fieldValueToValidate).doubleValue() >= min;
        return true;
    }

    @Override
    protected String getMessage() {
        return String.format("The min value must be <%s>", min);
    }
}
