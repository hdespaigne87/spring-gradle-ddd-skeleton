package bounded_contexts.shared.infrastructure.bus.query;

import bounded_contexts.shared.domain.bus.query.Query;
import bounded_contexts.shared.domain.bus.query.QueryBusPort;
import bounded_contexts.shared.domain.bus.query.QueryHandlerExecutionError;
import bounded_contexts.shared.domain.bus.query.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public final class InMemoryQueryBus implements QueryBusPort {

    private final QueryHandlersExecutor queryHandlersExecutor;

    @Override
    public <R extends Response> R ask(Query query) throws QueryHandlerExecutionError {
        return queryHandlersExecutor.executeQuery(query);
    }
}
