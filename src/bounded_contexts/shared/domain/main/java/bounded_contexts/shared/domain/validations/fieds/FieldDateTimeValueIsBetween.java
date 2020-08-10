package bounded_contexts.shared.domain.validations.fieds;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class FieldDateTimeValueIsBetween extends FieldValidation {

    private LocalDateTime min;
    private LocalDateTime max;
    private String minStr;
    private String maxStr;
    private static final String dateFormat = "dd/MM/yyyy";
    private static final String dateTimeFormat = "dd/MM/yyyy HH:mm:ss";
    private static final String timeFormat = "HH:mm:ss";

    public FieldDateTimeValueIsBetween(Object rootObject, String fieldName, Object fieldValueToValidate, LocalDateTime min, LocalDateTime max) {
        super(rootObject, fieldName, fieldValueToValidate);
        this.min = min;
        this.max = max;
    }

    @Override
    protected boolean isValid(Object fieldValueToValidate) {
        if (fieldValueToValidate instanceof LocalDateTime) {
            LocalDateTime value = ((LocalDateTime) fieldValueToValidate);
            minStr = min.format(DateTimeFormatter.ofPattern(dateTimeFormat));
            maxStr = max.format(DateTimeFormatter.ofPattern(dateTimeFormat));
            return value.isAfter(min) && value.isBefore(max);
        }
        if (fieldValueToValidate instanceof LocalDate) {
            LocalDate value = ((LocalDate) fieldValueToValidate);
            minStr = min.toLocalDate().format(DateTimeFormatter.ofPattern(dateFormat));
            maxStr = max.toLocalDate().format(DateTimeFormatter.ofPattern(dateFormat));
            return value.isAfter(min.toLocalDate()) && value.isBefore(max.toLocalDate());
        }
        if (fieldValueToValidate instanceof LocalTime) {
            LocalTime value = ((LocalTime) fieldValueToValidate);
            minStr = min.toLocalTime().format(DateTimeFormatter.ofPattern(timeFormat));
            maxStr = max.toLocalTime().format(DateTimeFormatter.ofPattern(timeFormat));
            return value.isAfter(min.toLocalTime()) && value.isBefore(max.toLocalTime());
        }
        return true;
    }

    @Override
    protected String getMessage() {
        return String.format("Must be between <%s> and <%s>", minStr, maxStr);
    }
}
