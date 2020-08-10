package bounded_contexts.shared.domain.bus.event;

import lombok.AccessLevel;
import lombok.Getter;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
public abstract class DomainEvent implements Serializable {

    private UUID eventId = UUID.randomUUID();
    private UUID aggregateId;
    private LocalDateTime eventOcurredOn = LocalDateTime.now();
    private Map<String, Object> eventBody;
    @Getter(AccessLevel.NONE)
    private boolean bodyLoaded;

    public abstract String getEventName();

    public DomainEvent(UUID aggregateId) {
        this.aggregateId = aggregateId;
    }

    public Map<String, Object> getEventBody() {
        loadBodyFields();
        return eventBody;
    }

    private void loadBodyFields() {
        if (bodyLoaded)
            return;
        eventBody = new HashMap<>();
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                boolean accessible = field.trySetAccessible();
                field.setAccessible(true);
                eventBody.put(field.getName(), field.get(this));
                field.setAccessible(accessible);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        bodyLoaded = true;
    }
}
