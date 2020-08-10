package bounded_contexts.shared.domain.validations.fieds;

public class FieldNotNull extends FieldValidation {

    public FieldNotNull(Object rootObject, String fieldName, Object fieldValueToValidate) {
        super(rootObject, fieldName, fieldValueToValidate);
    }

    @Override
    protected boolean isValid(Object fieldValueToValidate) {
        return fieldValueToValidate != null;
    }

    @Override
    protected String getMessage() {
        return "Cannot be null";
    }
}
