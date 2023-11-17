package commands.changeCommands;

import commands.BaseCommand;
import core.contracts.TaskManagementRepository;

import java.util.List;

public class ChangeFeedbackRatingCommand extends BaseCommand {

    public ChangeFeedbackRatingCommand(TaskManagementRepository repository) {
        super(repository);
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }


}
