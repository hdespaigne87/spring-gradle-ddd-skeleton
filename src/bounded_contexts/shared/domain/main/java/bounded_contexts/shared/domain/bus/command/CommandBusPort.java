package bounded_contexts.shared.domain.bus.command;

public interface CommandBusPort {
    void dispatch(Command command) throws CommandHandlerExecutionError;
}
