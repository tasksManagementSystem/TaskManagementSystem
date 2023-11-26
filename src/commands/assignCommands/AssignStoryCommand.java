package commands.assignCommands;

import commands.BaseCommand;
import core.contracts.TaskManagementRepository;
import models.contracts.Board;
import models.contracts.Member;
import models.contracts.Story;
import models.contracts.Team;
import utils.ParsingHelpers;
import utils.ValidationHelpers;

import java.util.List;

public class AssignStoryCommand extends BaseCommand {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    public static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments.";
    public static final String TASK_REASSIGNED_SUCCESSFULLY = "Assignee of story with ID %d successfully reassigned from %s to %s!";
    public static final String ASSIGNEE_ERR_MESSAGE = "Assignee %s is not part of team %s!";
    public static final String NO_STORY_ERR_MESSAGE = "There is no story with id %d in team %s!";
    public static final String CHANGED_THE_ASSIGNEE_OF_STORY_S_FROM_S_TO_S = "%s changed the assignee of story %s from %s to %s.";
    public static final String ID_WHOLE_NUMBER_ERR_MESSAGE = "ID must be a whole number";

    public AssignStoryCommand(TaskManagementRepository repository) {
        super(repository);
    }


    @Override
    protected boolean requiresLogin() {
        return true;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS, INVALID_NUMBER_OF_ARGUMENTS);
        int idStoryToReassign = ParsingHelpers.tryParseInt(parameters.get(0), ID_WHOLE_NUMBER_ERR_MESSAGE);
        String newAssignee = parameters.get(1);
        return reassignStory(idStoryToReassign, newAssignee);
    }

    private String reassignStory(int idStoryToReassign, String newAssignee) {
        Member member = getRepository().getLoggedInMember();
        Team memberTeam = getRepository().findTeamByMember(member.getName());
        List<Member> membersInTeam = memberTeam.getMembers();
        List<Board> boardsList = memberTeam.getBoards();

        for (Board board : boardsList) {
            List<Story> stories = board.getStories();
            for (Story story : stories) {
                if (story.getId() == idStoryToReassign) {
                    Member oldAssignee = getRepository().findMemberByUsername(story.getAssignee());
                    throwIfInvalidAssignee(newAssignee, memberTeam, membersInTeam);
                    story.changeAssignee(newAssignee);
                    story.addHistory(String.format(CHANGED_THE_ASSIGNEE_OF_STORY_S_FROM_S_TO_S, member.getName(), story.getTitle(), oldAssignee.getName(), newAssignee));
                    member.addHistory(String.format(CHANGED_THE_ASSIGNEE_OF_STORY_S_FROM_S_TO_S, member.getName(), story.getTitle(), oldAssignee.getName(), newAssignee));
                    return String.format(TASK_REASSIGNED_SUCCESSFULLY, idStoryToReassign, oldAssignee.getName(), newAssignee);
                }
            }
        }
        throw new IllegalArgumentException(String.format(NO_STORY_ERR_MESSAGE, idStoryToReassign, memberTeam.getName()));
    }

    private static void throwIfInvalidAssignee(String assignee, Team teamOfLoggedInMember, List<Member> membersInTeam) {
        boolean isMember = false;
        for (Member member1 : membersInTeam) {
            if (member1.getName().equals(assignee)) {
                isMember = true;
            }
        }
        if (!isMember) {
            throw new IllegalArgumentException(String.format(ASSIGNEE_ERR_MESSAGE, assignee, teamOfLoggedInMember.getName()));
        }
    }

}
