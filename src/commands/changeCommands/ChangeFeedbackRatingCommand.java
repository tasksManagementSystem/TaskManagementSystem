package commands.changeCommands;

import commands.BaseCommand;
import core.contracts.TaskManagementRepository;
import models.contracts.Board;
import models.contracts.Feedback;
import models.contracts.Task;
import utils.ParsingHelpers;
import utils.ValidationHelpers;

import java.util.List;

public class ChangeFeedbackRatingCommand extends BaseCommand {

    public static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments.";
    public static final String INVALID_INPUT_MESSAGE = "Invalid input. Expected a number.";
    public static final int COUNT = 2;


    public ChangeFeedbackRatingCommand(TaskManagementRepository repository) {
        super(repository);
    }
    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentCount(parameters, COUNT, INVALID_NUMBER_OF_ARGUMENTS);
        String title = parameters.get(0);
        String description = parameters.get(1);
        int rating = ParsingHelpers.tryParseInt(parameters.get(2),INVALID_INPUT_MESSAGE)
                ;
        return changeFeedback(title,description,rating);
    }
    private String changeFeedback(String title, String description,int rating) {
        return null;

    }
}
