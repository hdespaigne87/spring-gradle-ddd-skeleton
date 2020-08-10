package bounded_contexts.shared.infrastructure.bus.command;

import bounded_contexts.shared.domain.bus.command.Command;
import bounded_contexts.shared.domain.bus.command.CommandHandler;
import bounded_contexts.shared.domain.bus.command.CommandHandlerExecutionError;
import bounded_contexts.shared.domain.bus.command.CommandNotRegisteredError;
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
public final class CommandHandlersExecutor implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    private static HashMap<Class<? extends Command>, Class<? extends CommandHandler>> information;

    public CommandHandlersExecutor(HashMap<Class<? extends Command>, Class<? extends CommandHandler>> information) {
        CommandHandlersExecutor.information = information;
    }

    public CommandHandlersExecutor() throws NoSuchMethodException {
        this(scanCommandHandlers());
    }

    private static final String COMMAND_HANDLER_METHOD_NAME = "handle";
    private static final String BASE_PACKAGE_TO_SCAN = ProjectConfigReader.getBasePackage();

    private static HashMap<Class<? extends Command>, Class<? extends CommandHandler>> scanCommandHandlers() throws NoSuchMethodException {
        if (information != null)
            return information;

        Reflections reflections = new Reflections(BASE_PACKAGE_TO_SCAN);
        Set<Class<? extends CommandHandler>> commandHandlers = reflections.getSubTypesOf(CommandHandler.class);
        HashMap<Class<? extends Command>, Class<? extends CommandHandler>> commandHandlersInformation = new HashMap<>();

        for (Class<? extends CommandHandler> commandHandlerClass : commandHandlers) {
            Method[] methods = commandHandlerClass.getDeclaredMethods();
            boolean methodFound = false;
            for (Method method : methods) {
                if (method.getName().equals(COMMAND_HANDLER_METHOD_NAME)) {
                    Class<? extends Command> commandClass = (Class<? extends Command>) method.getParameters()[0].getType();
                    commandHandlersInformation.put(commandClass, commandHandlerClass);
                    methodFound = true;
                    break;
                }
            }
            if (!methodFound)
                throw new NoSuchMethodException(String.format("Method <%s> not found in command handler subclass <%s>",
                        COMMAND_HANDLER_METHOD_NAME, commandHandlerClass.getName()));
        }

        return commandHandlersInformation;
    }

    public <T extends Command> void executeCommand(T command) {
        try {
            Class<? extends CommandHandler> commandHandlerClass = information.get(command.getClass());
            if (null == commandHandlerClass) {
                throw new CommandNotRegisteredError(command.getClass());
            }
            CommandHandler handler = context.getBean(commandHandlerClass);
            handler.handle(command);
        } catch (Throwable error) {
            throw new CommandHandlerExecutionError(error);
        }
    }
}
