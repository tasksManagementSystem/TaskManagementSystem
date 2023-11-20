//package commands.addCommands;
//
//import commands.BaseCommand;
//import core.contracts.TaskManagementRepository;
//import utils.ValidationHelpers;
//
//import java.util.List;
//
//public class AddCommentToTaskCommand extends BaseCommand {
//    public static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments.";
//    public static final String MEMBER_ADDED_SUCCESSFULLY = "Member added successfully to Team: %s.";
//    public static final int COUNT = 2;
//    public AddCommentToTaskCommand(TaskManagementRepository repository) {
//        super(repository);
//    }
//
//    @Override
//    protected boolean requiresLogin() {
//        return false;
//    }
//    @Override
//    public String execute(List<String> parameters) {
//        ValidationHelpers.validateArgumentCount(parameters, COUNT, INVALID_NUMBER_OF_ARGUMENTS);
//        String comment = parameters.get(0);
//        String author = parameters.get(1);
//
//       // return addMemberToTeam(teamName);
//
//    }
//
//}
