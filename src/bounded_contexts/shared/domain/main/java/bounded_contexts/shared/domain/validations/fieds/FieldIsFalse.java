package bounded_contexts.shared.domain.validations.fieds;

public class FieldIsFalse extends FieldValidation {

    public FieldIsFalse(Object rootObject, String fieldName, Object fieldValueToValidate) {
        super(rootObject, fieldName, fieldValueToValidate);
    }

    @Override
    protected boolean isValid(Object fieldValueToValidate) {
        if (!(fieldValueToValidate instanceof Boolean))
            return true;
        return fieldValueToValidate != null && !(Boolean) fieldValueToValidate;
    }

    @Override
    protected String getMessage() {
        return "Must be false";
    }
}
