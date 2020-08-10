package bounded_contexts.shared.domain.validations.fieds;

public class FieldNumericValueIsBetween extends FieldValidation {

    private double min;
    private double max;

    public FieldNumericValueIsBetween(Object rootObject, String fieldName, Object fieldValueToValidate, double min, double max) {
        super(rootObject, fieldName, fieldValueToValidate);
        this.min = min;
        this.max = max;
    }

    @Override
    protected boolean isValid(Object fieldValueToValidate) {
        if (fieldValueToValidate instanceof Number) {
            double value = ((Number) fieldValueToValidate).doubleValue();
            return value >= min && value <= max;
        }
        return true;
    }

    @Override
    protected String getMessage() {
        return String.format("Must be between <%s> and <%s>", min, max);
    }
}
