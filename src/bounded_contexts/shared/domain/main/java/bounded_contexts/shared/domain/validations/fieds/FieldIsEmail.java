package bounded_contexts.shared.domain.validations.fieds;

public class FieldIsEmail extends FieldMatchWithRegExp {

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public FieldIsEmail(Object rootObject, String fieldName, Object fieldValueToValidate) {
        super(rootObject, fieldName, fieldValueToValidate);
    }

    @Override
    protected String getRegExpPattern() {
        return EMAIL_PATTERN;
    }

    @Override
    protected String getMessage() {
        return "Must be an email";
    }
}
