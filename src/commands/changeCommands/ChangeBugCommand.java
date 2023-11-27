package commands.changeCommands;

import commands.BaseCommand;
import core.contracts.TaskManagementRepository;
import models.contracts.Bug;
import models.enums.Priority;
import models.enums.Severity;
import models.enums.StatusBug;
import utils.ParsingHelpers;
import utils.ValidationHelpers;

import java.util.List;

public class ChangeBugCommand extends BaseCommand {
    public static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments.";
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;
    public static final String STATUS_OF_BUG_HAS_BEEN_CHANGED_SUCCESSFULLY_FROM_S_TO_S = "Status of bug has been changed successfully from %s to %s!";
    public static final String ID_WHOLE_NUMBER_ERR_MESSAGE = "ID must be a whole number";
    public static final String TYPE_OF_FIELD_DOES_NOT_EXIST = "Type of field does not exist.";

    public ChangeBugCommand(TaskManagementRepository repository) {
        super(repository);
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS, INVALID_NUMBER_OF_ARGUMENTS);
        int id = ParsingHelpers.tryParseInt(parameters.get(0), ID_WHOLE_NUMBER_ERR_MESSAGE);
        String typeOfField = parameters.get(1).toUpperCase();
        String newValue = parameters.get(2);
        return changeValueFields(id, typeOfField, newValue);
    }

    private String changeValueFields(int id, String typeOfField, String newValue) {
        Bug bug = getRepository().findElementById(getRepository().getBugList(), id, "Bug");
        String oldValue = "";
        switch (typeOfField) {
            case "PRIORITY":
                oldValue = bug.getPriority().toString();
                bug.changePriority(ParsingHelpers.tryParseEnum(newValue, Priority.class));
                break;

            case "STATUS":
                oldValue = bug.getStatusBug().toString();
                bug.changeStatusBug(ParsingHelpers.tryParseEnum(newValue, StatusBug.class));
                break;
            case "SEVERITY":
                oldValue = bug.getSeverity().toString();
                bug.changeSeverity(ParsingHelpers.tryParseEnum(newValue, Severity.class));
                break;
            default:
                throw new IllegalArgumentException(TYPE_OF_FIELD_DOES_NOT_EXIST);
        }
        bug.addHistory(String.format(STATUS_OF_BUG_HAS_BEEN_CHANGED_SUCCESSFULLY_FROM_S_TO_S, oldValue, newValue));

        return String.format(STATUS_OF_BUG_HAS_BEEN_CHANGED_SUCCESSFULLY_FROM_S_TO_S, oldValue, newValue);
    }
}
