package bounded_contexts.shared.infrastructure.bus.event;

import bounded_contexts.shared.domain.bus.event.DomainEvent;
import bounded_contexts.shared.domain.bus.event.EventBusPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public final class InMemoryEventBus implements EventBusPort {

    private final DomainEventsSubscribersExecutor domainEventsSubscribersExecutor;

    @Override
    public void publishEvents(List<DomainEvent> events) {
        for (DomainEvent event : events) {
            domainEventsSubscribersExecutor.executeSubscribers(event);
        }
    }
}
