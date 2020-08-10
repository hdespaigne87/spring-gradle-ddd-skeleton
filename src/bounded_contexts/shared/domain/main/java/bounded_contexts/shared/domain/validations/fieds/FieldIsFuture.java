package bounded_contexts.shared.domain.validations.fieds;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class FieldIsFuture extends FieldValidation {

    public FieldIsFuture(Object rootObject, String fieldName, Object fieldValueToValidate) {
        super(rootObject, fieldName, fieldValueToValidate);
    }

    @Override
    protected boolean isValid(Object fieldValueToValidate) {
        LocalDateTime now = LocalDateTime.now();
        if (fieldValueToValidate instanceof LocalDateTime) {
            LocalDateTime value = ((LocalDateTime) fieldValueToValidate);
            return value.isAfter(now);
        }
        if (fieldValueToValidate instanceof LocalDate) {
            LocalDate value = ((LocalDate) fieldValueToValidate);
            return value.isAfter(now.toLocalDate());
        }
        if (fieldValueToValidate instanceof LocalTime) {
            LocalTime value = ((LocalTime) fieldValueToValidate);
            return value.isAfter(now.toLocalTime());
        }
        return true;
    }

    @Override
    protected String getMessage() {
        return String.format("Must be in the future");
    }
}
