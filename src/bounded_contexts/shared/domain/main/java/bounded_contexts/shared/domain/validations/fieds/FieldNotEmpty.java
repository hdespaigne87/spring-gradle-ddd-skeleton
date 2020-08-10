package bounded_contexts.shared.domain.validations.fieds;

import java.util.Collection;
import java.util.Map;

public class FieldNotEmpty extends FieldValidation {

    public FieldNotEmpty(Object rootObject, String fieldName, Object fieldValueToValidate) {
        super(rootObject, fieldName, fieldValueToValidate);
    }

    @Override
    protected boolean isValid(Object fieldValueToValidate) {
        if (fieldValueToValidate == null)
            return false;
        if (fieldValueToValidate instanceof String)
            return !((String) fieldValueToValidate).isEmpty();
        if (fieldValueToValidate instanceof Map)
            return !((Map) fieldValueToValidate).isEmpty();
        if (fieldValueToValidate instanceof Collection)
            return !((Collection) fieldValueToValidate).isEmpty();
        if (fieldValueToValidate.getClass().isArray())
            return ((Object[]) fieldValueToValidate).length > 0;
        return true;
    }

    @Override
    protected String getMessage() {
        return "Cannot be empty";
    }
}
