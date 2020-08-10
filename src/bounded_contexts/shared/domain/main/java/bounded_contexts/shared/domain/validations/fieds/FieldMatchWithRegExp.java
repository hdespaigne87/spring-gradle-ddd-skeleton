package bounded_contexts.shared.domain.validations.fieds;

import java.util.regex.Pattern;

public abstract class FieldMatchWithRegExp extends FieldValidation {

    public FieldMatchWithRegExp(Object rootObject, String fieldName, Object fieldValueToValidate) {
        super(rootObject, fieldName, fieldValueToValidate);
    }

    protected abstract String getRegExpPattern();

    @Override
    protected boolean isValid(Object fieldValueToValidate) {
        if (!(fieldValueToValidate instanceof String))
            return true;
        Pattern pattern = Pattern.compile(getRegExpPattern());
        return pattern.matcher(fieldValueToValidate.toString()).matches();
    }
}
