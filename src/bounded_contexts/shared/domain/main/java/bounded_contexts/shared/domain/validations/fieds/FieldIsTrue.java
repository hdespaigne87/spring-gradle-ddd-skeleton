package bounded_contexts.shared.domain.validations.fieds;

public class FieldIsTrue extends FieldValidation {

    public FieldIsTrue(Object rootObject, String fieldName, Object fieldValueToValidate) {
        super(rootObject, fieldName, fieldValueToValidate);
    }

    @Override
    protected boolean isValid(Object fieldValueToValidate) {
        if (!(fieldValueToValidate instanceof Boolean))
            return true;
        return fieldValueToValidate == null ? false : (Boolean) fieldValueToValidate;
    }

    @Override
    protected String getMessage() {
        return "Must be true";
    }
}
