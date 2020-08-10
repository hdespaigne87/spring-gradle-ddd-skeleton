package bounded_contexts.shared.domain.validations.fieds;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class FieldIsPastOrPresent extends FieldValidation {

    public FieldIsPastOrPresent(Object rootObject, String fieldName, Object fieldValueToValidate) {
        super(rootObject, fieldName, fieldValueToValidate);
    }

    @Override
    protected boolean isValid(Object fieldValueToValidate) {
        LocalDateTime now = LocalDateTime.now();
        if (fieldValueToValidate instanceof LocalDateTime) {
            LocalDateTime value = ((LocalDateTime) fieldValueToValidate);
            return value.isBefore(now) || value.isEqual(now);
        }
        if (fieldValueToValidate instanceof LocalDate) {
            LocalDate value = ((LocalDate) fieldValueToValidate);
            return value.isBefore(now.toLocalDate()) || value.isEqual(now.toLocalDate());
        }
        if (fieldValueToValidate instanceof LocalTime) {
            LocalTime value = ((LocalTime) fieldValueToValidate);
            return value.isBefore(now.toLocalTime()) || value.equals(now.toLocalTime());
        }
        return true;
    }

    @Override
    protected String getMessage() {
        return String.format("Must be in the past or present");
    }
}
