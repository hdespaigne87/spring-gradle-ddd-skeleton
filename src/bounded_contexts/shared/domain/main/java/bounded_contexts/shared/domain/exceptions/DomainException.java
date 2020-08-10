package bounded_contexts.shared.domain.exceptions;

import lombok.Getter;

@SuppressWarnings({"serial"})

@Getter
public class DomainException extends RuntimeException {

    private Enum<?> errorCode;
    private Object[] messageParams;
    private DomainErrorDetails errorDetails;

    protected DomainException(String message) {
        super(message);
    }

    public DomainException(Enum<?> errorCode, Object... messageParams) {
        super("");
        this.errorCode = errorCode;
        this.messageParams = messageParams;
    }

    public DomainException(DomainErrorDetails errorDetails) {
        super(errorDetails.getMessage());
        this.errorDetails = errorDetails;
    }
}