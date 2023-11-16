package commands.createCommands;

import commands.BaseCommand;
import core.contracts.TaskManagementRepository;
import models.contracts.Member;
import models.enums.Priority;
import models.enums.Severity;
import utils.ParsingHelpers;
import utils.ValidationHelpers;

import java.util.Arrays;
import java.util.List;

public class CreateNewBug extends BaseCommand {

    public static final String INVALID_NUMBERS_OF_ARGUMENTS = "Invalid numbers of arguments.";

    public CreateNewBug(TaskManagementRepository repository) {
        super(repository);
    }


//    public String execute(List<String> parameters) {
//        ValidationHelpers.validateArgumentCount(parameters, 6, INVALID_NUMBERS_OF_ARGUMENTS);
//        String description = parameters.get(0);
//        String title = parameters.get(2);
//        List<String> stepsToReproduce = Arrays.asList(parameters.get(1).split("; "));
//        Priority priority = ParsingHelpers.tryParseEnum(parameters.get(3), Priority.class);
//        Severity severity = ParsingHelpers.tryParseEnum(parameters.get(4), Severity.class);
//        String assignee = parameters.get(5);
//
//        return createBug(title, description, stepsToReproduce, priority, severity, assignee);
//    }
//
//        private String createBug( String title, String description, List<String> stepToReproduce, Priority priority, Severity severity, String assignee){
//           // Member member = getRepository().getMemberList().;
//        }

}