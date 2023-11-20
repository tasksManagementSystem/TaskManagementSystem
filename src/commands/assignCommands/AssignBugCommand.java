package commands.assignCommands;

import commands.BaseCommand;
import core.contracts.TaskManagementRepository;
import models.contracts.Board;
import models.contracts.Bug;
import models.contracts.Member;
import models.contracts.Team;
import utils.ValidationHelpers;

import java.util.List;

public class AssignBugCommand extends BaseCommand {
    public static final int COUNT = 2;
    public static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments.";
    public static final String TASK_REASSIGNED_SUCCESSFULLY = "Assignee of bug %s successfully reassigned from %s to %s!";
    public static final String ASSIGNEE_ERR_MESSAGE = "Assignee %s is not part of team %s!";
    public static final String NO_BUG_ERR_MESSAGE = "There is no bug %s in team %s!";

    public AssignBugCommand(TaskManagementRepository repository) {
        super(repository);
    }


    @Override
    protected boolean requiresLogin() {
        return true;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentCount(parameters,COUNT,INVALID_NUMBER_OF_ARGUMENTS );
        String bugToReassign = parameters.get(0);
        String newAssignee = parameters.get(1);
        return reassignBug(bugToReassign, newAssignee);
    }

    private String reassignBug(String bugToReassign, String newAssignee) {
        Member member = getRepository().getLoggedInMember();
        Team memberTeam = getRepository().findTeamByMember(member);
        List<Member> membersInTeam = memberTeam.getMembers();
        List<Board> boardsList = memberTeam.getBoards();

        for (Board board : boardsList) {
            List<Bug> bugs = board.getBugs();
            for (Bug bug : bugs) {
                if (bug.getTitle().equals(bugToReassign)) {
                    Member oldAssignee = getRepository().findMemberByUsername(bug.getAssignee());
                    throwIfInvalidAssignee(newAssignee, memberTeam, membersInTeam);
                    bug.changeAssignee(newAssignee);

                    bug.addHistory(String.format("%s changed the assignee of bug %s from %s to %s.", member.getName(), bug.getTitle(), oldAssignee.getName(), newAssignee));
                    member.addHistory(String.format("%s changed the assignee of bug %s from %s to %s.", member.getName(), bug.getTitle(), oldAssignee.getName(), newAssignee));
                    return String.format(TASK_REASSIGNED_SUCCESSFULLY, bugToReassign, oldAssignee.getName(), newAssignee);
                }
            }
        }
        throw new IllegalArgumentException(String.format(NO_BUG_ERR_MESSAGE, bugToReassign, memberTeam.getName()));
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

