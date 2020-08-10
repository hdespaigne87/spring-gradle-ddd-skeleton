package bounded_contexts.shared.infrastructure.bus.command;

import bounded_contexts.shared.domain.bus.command.Command;
import bounded_contexts.shared.domain.bus.command.CommandBusPort;
import bounded_contexts.shared.domain.bus.command.CommandHandlerExecutionError;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public final class InMemoryCommandBus implements CommandBusPort {

    private final CommandHandlersExecutor commandHandlersExecutor;

    @Override
    public void dispatch(Command command) throws CommandHandlerExecutionError {
        commandHandlersExecutor.executeCommand(command);
    }
}
