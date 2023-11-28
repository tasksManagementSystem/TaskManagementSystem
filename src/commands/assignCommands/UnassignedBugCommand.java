package commands.assignCommands;

import commands.BaseCommand;
import core.contracts.TaskManagementRepository;
import models.contracts.Board;
import models.contracts.Bug;
import models.contracts.Member;
import utils.ParsingHelpers;
import utils.ValidationHelpers;

import java.util.List;

public class UnassignedBugCommand extends BaseCommand {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    public static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments.";
    public static final String TASK_UNASSIGNED_SUCCESSFULLY = "The bug with ID %d hae been successfully unassigned from member %s!";

    public static final String NO_BUG_ERR_MESSAGE = "There is no bug with id %d!";
    public static final String UNASSIGNED_BUG = "The bug with ID %d has been unassigned form %s.";

    public static final String ID_WHOLE_NUMBER_ERR_MESSAGE = "ID must be a whole number";
    public static final String NOT_ASSIGN = "Not assign";


    public UnassignedBugCommand(TaskManagementRepository repository) {
        super(repository);
    }


    @Override
    protected boolean requiresLogin() {
        return true;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS, INVALID_NUMBER_OF_ARGUMENTS);
        int idBugToUnassigned = ParsingHelpers.tryParseInt(parameters.get(0), ID_WHOLE_NUMBER_ERR_MESSAGE);

        return reassignBug(idBugToUnassigned);
    }

    private String reassignBug(int idBugToUnassigned) {
        Member member = getRepository().getLoggedInMember();
        Bug bug = getRepository().findElementById(getRepository().getBugList(), idBugToUnassigned, "Bug");

        if (bug.getAssignee().equals(NOT_ASSIGN)) {
            throw new IllegalArgumentException("The bug hasn't been assigned");
        }
        if (bug.getId() == idBugToUnassigned) {
            Member oldAssignee = getRepository().findMemberByUsername(bug.getAssignee());
            Board board=getRepository().findBoardByName(oldAssignee.getName());
            bug.unassigned(bug.getAssignee());

            bug.addHistory(String.format(UNASSIGNED_BUG, idBugToUnassigned, oldAssignee.getName()));
            board
                    .addHistory(String.format(UNASSIGNED_BUG, idBugToUnassigned, oldAssignee.getName()));
            return String.format(TASK_UNASSIGNED_SUCCESSFULLY, idBugToUnassigned, oldAssignee.getName());
        }
        throw new IllegalArgumentException(String.format(NO_BUG_ERR_MESSAGE, idBugToUnassigned));
    }

}


