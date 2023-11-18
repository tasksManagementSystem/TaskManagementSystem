package commands.changeCommands;

import commands.BaseCommand;
import core.contracts.TaskManagementRepository;

public class ChangeFeedbackStatus extends BaseCommand {

    public ChangeFeedbackStatus(TaskManagementRepository repository) {
        super(repository);
    }

    @Override
    protected boolean requiresLogin() {return true;
    }
}
