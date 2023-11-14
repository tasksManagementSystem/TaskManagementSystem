package core;

import commands.contracts.Command;
import core.contracts.CommandFactory;
import core.contracts.TaskManagementRepository;

public class CommandFactoryImpl implements CommandFactory {
    @Override
    public Command createCommandFromCommandName(String commandTypeAsString, TaskManagementRepository taskManagementRepository) {
        return null;
    }
}
