package bounded_contexts.shared.domain.validations.fieds;

import java.net.MalformedURLException;
import java.net.URL;

public class FieldIsUrlHttp extends FieldValidation {

    public FieldIsUrlHttp(Object rootObject, String fieldName, Object fieldValueToValidate) {
        super(rootObject, fieldName, fieldValueToValidate);
    }

    @Override
    protected String getMessage() {
        return "Must be an url http";
    }

    @Override
    protected boolean isValid(Object fieldValueToValidate) {
        if (fieldValueToValidate == null)
            return true;
        if (!fieldValueToValidate.toString().startsWith("http://") && !fieldValueToValidate.toString().startsWith("https://"))
            return false;
        try {
            new URL(fieldValueToValidate.toString());
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }
}
