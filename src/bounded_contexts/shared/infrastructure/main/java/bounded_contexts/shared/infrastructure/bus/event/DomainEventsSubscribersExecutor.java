package bounded_contexts.shared.infrastructure.bus.event;

import bounded_contexts.shared.domain.bus.event.DomainEvent;
import bounded_contexts.shared.domain.bus.event.DomainEventSubscriber;
import bounded_contexts.shared.infrastructure.ProjectConfigReader;
import org.reflections.Reflections;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Service
public final class DomainEventsSubscribersExecutor implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    private static HashMap<Class<? extends DomainEvent>, List<Class<? extends DomainEventSubscriber>>> information;

    public DomainEventsSubscribersExecutor(HashMap<Class<? extends DomainEvent>, List<Class<? extends DomainEventSubscriber>>> information) {
        DomainEventsSubscribersExecutor.information = information;
    }

    public DomainEventsSubscribersExecutor() throws NoSuchMethodException {
        this(scanDomainEventSubscribers());
    }

    private static final String SUBSCRIBER_METHOD_NAME = "onDomainEventReceived";
    private static final String BASE_PACKAGE_TO_SCAN = ProjectConfigReader.getBasePackage();

    private static HashMap<Class<? extends DomainEvent>, List<Class<? extends DomainEventSubscriber>>> scanDomainEventSubscribers() throws NoSuchMethodException {
        if (information != null)
            return information;

        Reflections reflections = new Reflections(BASE_PACKAGE_TO_SCAN);
        Set<Class<? extends DomainEventSubscriber>> subscribers = reflections.getSubTypesOf(DomainEventSubscriber.class);
        HashMap<Class<? extends DomainEvent>, List<Class<? extends DomainEventSubscriber>>> subscribersInformation = new HashMap<>();

        for (Class<? extends DomainEventSubscriber> subscriberClass : subscribers) {
            Method[] methods = subscriberClass.getDeclaredMethods();
            boolean methodFound = false;
            for (Method method : methods) {
                if (method.getName().equals(SUBSCRIBER_METHOD_NAME)) {
                    Class<? extends DomainEvent> eventClass = (Class<? extends DomainEvent>) method.getParameters()[0].getType();

                    if (!subscribersInformation.containsKey(eventClass))
                        subscribersInformation.put(eventClass, new ArrayList<>());

                    subscribersInformation.get(eventClass).add(subscriberClass);
                    methodFound = true;
                    break;
                }
            }
            if (!methodFound)
                throw new NoSuchMethodException(String.format("Method <%s> not found in subscriber event subclass <%s>",
                        SUBSCRIBER_METHOD_NAME, subscriberClass.getName()));
        }

        return subscribersInformation;
    }

    public <T extends DomainEvent> void executeSubscribers(T domainEvent) {
        List<Class<? extends DomainEventSubscriber>> subscribers = information.get(domainEvent.getClass());
        if (subscribers != null) {
            for (Class<? extends DomainEventSubscriber> subscriberClass : subscribers) {
                context.getBean(subscriberClass).onDomainEventReceived(domainEvent);
            }
        }
    }
}
