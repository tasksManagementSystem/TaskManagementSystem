package core.contracts;

import commands.contracts.Command;

public interface CommandFactory {
    Command createCommandFromCommandName(String commandTypeAsString, TaskManagementRepository taskManagementRepository);


}
