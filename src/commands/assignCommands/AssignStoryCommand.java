package commands.assignCommands;

import commands.BaseCommand;
import core.contracts.TaskManagementRepository;
import models.contracts.*;
import utils.ValidationHelpers;

import java.util.List;

public class AssignStoryCommand extends BaseCommand {
    public static final int COUNT = 2;
    public static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments.";
    public static final String TASK_REASSIGNED_SUCCESSFULLY = "Assignee of story %s successfully reassigned from %s to %s!";
    public static final String ASSIGNEE_ERR_MESSAGE = "Assignee %s is not part of team %s!";
    public static final String NO_STORY_ERR_MESSAGE = "There is no story %s in team %s!";

    public AssignStoryCommand(TaskManagementRepository repository) {
        super(repository);
    }


    @Override
    protected boolean requiresLogin() {
        return true;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentCount(parameters,COUNT,INVALID_NUMBER_OF_ARGUMENTS );
        String storyToReassign = parameters.get(0);
        String newAssignee = parameters.get(1);
        return reassignStory(storyToReassign, newAssignee);
    }

    private String reassignStory(String storyToReassign, String newAssignee) {
        Member member = getRepository().getLoggedInMember();
        Team memberTeam = getRepository().findTeamByMember(member);
        List<Member> membersInTeam = memberTeam.getMembers();
        List<Board> boardsList = memberTeam.getBoards();

        for (Board board : boardsList) {
            List<Story> stories = board.getStories();
            for (Story story : stories) {
                if (story.getTitle().equals(storyToReassign)) {
                    Member oldAssignee = getRepository().findMemberByUsername(story.getAssignee());
                    throwIfInvalidAssignee(newAssignee, memberTeam, membersInTeam);
                    story.changeAssignee(newAssignee);

                    story.addHistory(String.format("%s changed the assignee of story %s from %s to %s.", member.getName(), story.getTitle(), oldAssignee.getName(), newAssignee));
                    member.addHistory(String.format("%s changed the assignee of story %s from %s to %s.", member.getName(), story.getTitle(), oldAssignee.getName(), newAssignee));
                    return String.format(TASK_REASSIGNED_SUCCESSFULLY, storyToReassign, oldAssignee.getName(), newAssignee);
                }
            }
        }
        throw new IllegalArgumentException(String.format(NO_STORY_ERR_MESSAGE, storyToReassign, memberTeam.getName()));
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
