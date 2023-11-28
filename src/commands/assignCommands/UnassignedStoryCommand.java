package commands.assignCommands;

import commands.BaseCommand;
import core.contracts.TaskManagementRepository;
import models.contracts.Board;
import models.contracts.Member;
import models.contracts.Story;
import utils.ParsingHelpers;
import utils.ValidationHelpers;

import java.util.List;

public class UnassignedStoryCommand extends BaseCommand {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    public static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments.";
    public static final String TASK_UNASSIGNED_SUCCESSFULLY = "The bug with ID %d hae been successfully unassigned from member %s!";

    public static final String NO_BUG_ERR_MESSAGE = "There is no bug with id %d!";
    public static final String UNASSIGNED_BUG = "The bug with ID %d has been unassigned form %s.";

    public static final String ID_WHOLE_NUMBER_ERR_MESSAGE = "ID must be a whole number";
    public static final String NOT_ASSIGN = "Not assign";
    public static final String THE_STORY_HASN_T_BEEN_ASSIGNED = "The story hasn't been assigned";


    public UnassignedStoryCommand(TaskManagementRepository repository) {
        super(repository);
    }


    @Override
    protected boolean requiresLogin() {
        return true;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS, INVALID_NUMBER_OF_ARGUMENTS);
        int idStoryToUnassigned = ParsingHelpers.tryParseInt(parameters.get(0), ID_WHOLE_NUMBER_ERR_MESSAGE);

        return reassignBug(idStoryToUnassigned);
    }

    private String reassignBug(int idStoryToUnassigned) {
        Member member = getRepository().getLoggedInMember();
        Story story = getRepository().findElementById(getRepository().getStoryList(), idStoryToUnassigned, "Story");

        if (story.getAssignee().equals(NOT_ASSIGN)) {
            throw new IllegalArgumentException(THE_STORY_HASN_T_BEEN_ASSIGNED);
        }
        if (story.getId() == idStoryToUnassigned) {

            Member oldAssignee = getRepository().findMemberByUsername(story.getAssignee());
            story.unassigned(story.getAssignee());
            Board board=getRepository().findBoardByName(oldAssignee.getName());


            story.addHistory(String.format(UNASSIGNED_BUG, idStoryToUnassigned, oldAssignee.getName()));
            board.addHistory(String.format(UNASSIGNED_BUG, idStoryToUnassigned, oldAssignee.getName()));
            return String.format(TASK_UNASSIGNED_SUCCESSFULLY, idStoryToUnassigned, oldAssignee.getName());
        }
        throw new IllegalArgumentException(String.format(NO_BUG_ERR_MESSAGE, idStoryToUnassigned));
    }
}
