package bounded_contexts.shared.domain.validations.fieds;

import java.util.Collection;
import java.util.Map;

public class FieldMaxSizeIs extends FieldValidation {

    private int max;

    public FieldMaxSizeIs(Object rootObject, String fieldName, Object fieldValueToValidate, int max) {
        super(rootObject, fieldName, fieldValueToValidate);
        this.max = max;
    }

    @Override
    protected boolean isValid(Object fieldValueToValidate) {
        if (fieldValueToValidate == null)
            return true;
        int size = 0;

        if (fieldValueToValidate instanceof String)
            size = ((String) fieldValueToValidate).length();
        if (fieldValueToValidate instanceof Collection)
            size = ((Collection) fieldValueToValidate).size();
        if (fieldValueToValidate instanceof Map)
            size = ((Map) fieldValueToValidate).size();
        if (fieldValueToValidate.getClass().isArray())
            size = ((Object[]) fieldValueToValidate).length;

        return size <= max;
    }

    @Override
    protected String getMessage() {
        return String.format("The max size must be <%s>", max);
    }
}
