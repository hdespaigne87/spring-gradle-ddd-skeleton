package bounded_contexts.shared.domain.bus.event;

import java.util.List;

public interface EventBusPort {

    void publishEvents(List<DomainEvent> events);
}
