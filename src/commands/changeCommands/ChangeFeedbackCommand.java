package commands.changeCommands;

import commands.BaseCommand;
import core.contracts.TaskManagementRepository;
import models.contracts.Feedback;
import models.enums.StatusFeedback;
import utils.ParsingHelpers;
import utils.ValidationHelpers;

import java.util.List;

public class ChangeFeedbackCommand extends BaseCommand {
    public static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments.";
    public static final int COUNT = 3;
    public static final String FEEDBACK_HAS_BEEN_CHANGED_SUCCESSFULLY_FROM_S_TO_S = "%s of feedback has been changed successfully from %s to %s!";
    public static final String RATING_WHOLE_NUMBER_ERR_MESSAGE = "Rating must be a whole number";
    public static final String TYPE_OF_FIELD_DOES_NOT_EXIST = "Type of field does not exist.";

    public ChangeFeedbackCommand(TaskManagementRepository repository) {
        super(repository);
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentCount(parameters, COUNT, INVALID_NUMBER_OF_ARGUMENTS);
        int id = ParsingHelpers.tryParseInt(parameters.get(0), RATING_WHOLE_NUMBER_ERR_MESSAGE);
        String typeOfField = parameters.get(1).toUpperCase();
        String newValue = parameters.get(2);
        return changeValueFields(id, typeOfField, newValue);
    }

    private String changeValueFields(int id, String typeOfField, String newValue) {
        Feedback feedback = getRepository().findElementById(getRepository().getFeedbackList(), id, "Feedback");
        String oldValue = "";
        switch (typeOfField) {

            case "STATUS":
                oldValue = feedback.getStatusFeedback().toString();
                feedback.changeStatusFeedback(ParsingHelpers.tryParseEnum(newValue, StatusFeedback.class));
                break;
            case "RATING":
                oldValue = String.valueOf(feedback.getRating());
                feedback.changeRating(ParsingHelpers.tryParseInt(newValue, RATING_WHOLE_NUMBER_ERR_MESSAGE));
                break;
            default:
                throw new IllegalArgumentException(TYPE_OF_FIELD_DOES_NOT_EXIST);
        }

        feedback.addHistory(String.format(FEEDBACK_HAS_BEEN_CHANGED_SUCCESSFULLY_FROM_S_TO_S, typeOfField, oldValue, newValue));

        return String.format(FEEDBACK_HAS_BEEN_CHANGED_SUCCESSFULLY_FROM_S_TO_S, typeOfField, oldValue, newValue);
    }
}
