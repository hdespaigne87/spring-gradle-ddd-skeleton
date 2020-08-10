package bounded_contexts.shared.infrastructure.bus.query;

import bounded_contexts.shared.domain.bus.query.*;
import bounded_contexts.shared.infrastructure.ProjectConfigReader;
import org.reflections.Reflections;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Set;

@Service
public final class QueryHandlersExecutor implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    private static HashMap<Class<? extends Query>, Class<? extends QueryHandler>> information;

    public QueryHandlersExecutor(HashMap<Class<? extends Query>, Class<? extends QueryHandler>> information) {
        QueryHandlersExecutor.information = information;
    }

    public QueryHandlersExecutor() throws NoSuchMethodException {
        this(scanQueryHandlers());
    }

    private static final String QUERY_HANDLER_METHOD_NAME = "handle";
    private static final String BASE_PACKAGE_TO_SCAN = ProjectConfigReader.getBasePackage();

    private static HashMap<Class<? extends Query>, Class<? extends QueryHandler>> scanQueryHandlers() throws NoSuchMethodException {
        if (information != null)
            return information;

        Reflections reflections = new Reflections(BASE_PACKAGE_TO_SCAN);
        Set<Class<? extends QueryHandler>> queryHandlers = reflections.getSubTypesOf(QueryHandler.class);
        HashMap<Class<? extends Query>, Class<? extends QueryHandler>> queryHandlersInformation = new HashMap<>();

        for (Class<? extends QueryHandler> queryHandlerClass : queryHandlers) {
            Method[] methods = queryHandlerClass.getDeclaredMethods();
            boolean methodFound = false;
            for (Method method : methods) {
                if (method.getName().equals(QUERY_HANDLER_METHOD_NAME)) {
                    Class<? extends Query> queryClass = (Class<? extends Query>) method.getParameters()[0].getType();
                    queryHandlersInformation.put(queryClass, queryHandlerClass);
                    methodFound = true;
                    break;
                }
            }
            if (!methodFound)
                throw new NoSuchMethodException(String.format("Method <%s> not found in query handler subclass <%s>",
                        QUERY_HANDLER_METHOD_NAME, queryHandlerClass.getName()));
        }

        return queryHandlersInformation;
    }

    public <R extends Response, T extends Query> R executeQuery(T query) {
        try {
            Class<? extends QueryHandler> queryHandlerClass = information.get(query.getClass());
            if (null == queryHandlerClass) {
                throw new QueryNotRegisteredError(query.getClass());
            }
            QueryHandler handler = context.getBean(queryHandlerClass);
            return (R)handler.handle(query);
        } catch (Throwable error) {
            throw new QueryHandlerExecutionError(error);
        }
    }
}
