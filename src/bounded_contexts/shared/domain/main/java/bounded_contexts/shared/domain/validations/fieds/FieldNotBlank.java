package bounded_contexts.shared.domain.validations.fieds;

public class FieldNotBlank extends FieldValidation {

    public FieldNotBlank(Object rootObject, String fieldName, Object fieldValueToValidate) {
        super(rootObject, fieldName, fieldValueToValidate);
    }

    @Override
    protected boolean isValid(Object fieldValueToValidate) {
        if (fieldValueToValidate == null)
            return false;
        if (!(fieldValueToValidate instanceof String))
            return true;
        return !((String)fieldValueToValidate).trim().isEmpty();
    }

    @Override
    protected String getMessage() {
        return "Cannot be blank";
    }
}
