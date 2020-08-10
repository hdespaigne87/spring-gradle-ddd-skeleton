package bounded_contexts.shared.domain.validations.fieds;

public class FieldMaxValueIs extends FieldValidation {

    private double max;

    public FieldMaxValueIs(Object rootObject, String fieldName, Object fieldValueToValidate, double max) {
        super(rootObject, fieldName, fieldValueToValidate);
        this.max = max;
    }

    @Override
    protected boolean isValid(Object fieldValueToValidate) {
        if (fieldValueToValidate instanceof Number)
            return ((Number) fieldValueToValidate).doubleValue() <= max;
        return true;
    }

    @Override
    protected String getMessage() {
        return String.format("The max value must be <%s>", max);
    }
}
