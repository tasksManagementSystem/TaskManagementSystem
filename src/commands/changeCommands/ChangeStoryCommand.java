package commands.changeCommands;

import commands.BaseCommand;
import core.contracts.TaskManagementRepository;
import models.contracts.Story;
import models.enums.Priority;
import models.enums.Size;
import models.enums.StatusStory;
import utils.ParsingHelpers;
import utils.ValidationHelpers;

import java.util.List;

public class ChangeStoryCommand extends BaseCommand {
    public static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments.";
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;
    public static final String STATUS_OF_STORY_HAS_BEEN_CHANGED_SUCCESSFULLY_FROM_S_TO_S = "Status of story has been changed successfully from %s to %s!";
    public static final String RATING_WHOLE_NUMBER_ERR_MESSAGE = "Rating must be a whole number";
    public static final String TYPE_OF_FIELD_DOES_NOT_EXIST = "Type of field does not exist.";

    public ChangeStoryCommand(TaskManagementRepository repository) {
        super(repository);
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS, INVALID_NUMBER_OF_ARGUMENTS);
        int id = ParsingHelpers.tryParseInt(parameters.get(0), RATING_WHOLE_NUMBER_ERR_MESSAGE);
        String typeOfField = parameters.get(1).toUpperCase();
        String newValue = parameters.get(2);
        return changeValueFields(id, typeOfField, newValue);
    }

    private String changeValueFields(int id, String typeOfField, String newValue) {
        Story story = getRepository().findElementById(getRepository().getStoryList(), id, "Story");
        String oldValue = "";
        switch (typeOfField) {
            case "PRIORITY":
                oldValue = story.getPriority().toString();
                story.changePriority(ParsingHelpers.tryParseEnum(newValue, Priority.class));
                break;

            case "STATUS":
                oldValue = story.getStatusStory().toString();
                story.changeStatusStory(ParsingHelpers.tryParseEnum(newValue, StatusStory.class));
                break;
            case "SIZE":
                oldValue = story.getSize().toString();
                story.changeSize(ParsingHelpers.tryParseEnum(newValue, Size.class));
                break;
            default:
                throw new IllegalArgumentException(TYPE_OF_FIELD_DOES_NOT_EXIST);
        }
        story.addHistory(String.format(STATUS_OF_STORY_HAS_BEEN_CHANGED_SUCCESSFULLY_FROM_S_TO_S, oldValue, newValue));

        return String.format(STATUS_OF_STORY_HAS_BEEN_CHANGED_SUCCESSFULLY_FROM_S_TO_S, oldValue, newValue);
    }
}
