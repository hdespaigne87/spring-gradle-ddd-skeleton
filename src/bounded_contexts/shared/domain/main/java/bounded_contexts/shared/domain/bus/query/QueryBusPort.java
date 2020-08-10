package bounded_contexts.shared.domain.bus.query;

public interface QueryBusPort {
    <R extends Response> R ask(Query query) throws QueryHandlerExecutionError;
}
