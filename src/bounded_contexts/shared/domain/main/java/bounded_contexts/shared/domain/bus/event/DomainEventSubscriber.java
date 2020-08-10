package bounded_contexts.shared.domain.bus.event;

public interface DomainEventSubscriber<EventType extends DomainEvent> {

    void onDomainEventReceived(EventType event);
}
