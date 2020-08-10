package bounded_contexts.shared.domain.validations.fieds;

import java.util.Collection;
import java.util.Map;

public class FieldMinSizeIs extends FieldValidation {

    private int min;

    public FieldMinSizeIs(Object rootObject, String fieldName, Object fieldValueToValidate, int min) {
        super(rootObject, fieldName, fieldValueToValidate);
        this.min = min;
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

        return size >= min;
    }

    @Override
    protected String getMessage() {
        return String.format("The min size must be <%s>", min);
    }
}
