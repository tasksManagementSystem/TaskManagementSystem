package commands.assignCommands;

import commands.BaseCommand;
import core.contracts.TaskManagementRepository;
import models.contracts.Board;
import models.contracts.Bug;
import models.contracts.Member;
import models.contracts.Team;
import utils.ParsingHelpers;
import utils.ValidationHelpers;

import java.util.List;

public class AssignBugCommand extends BaseCommand {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    public static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments.";
    public static final String TASK_REASSIGNED_SUCCESSFULLY = "Assignee of bug with ID %d successfully reassigned from %s to %s!";
    public static final String ASSIGNEE_ERR_MESSAGE = "Assignee %s is not part of team %s!";
    public static final String NO_BUG_ERR_MESSAGE = "There is no bug with id %d in team %s!";
    public static final String CHANGED_THE_ASSIGNEE_OF_BUG_S_FROM_S_TO_S = "%s changed the assignee of bug %s from %s to %s.";

    public static final String ID_WHOLE_NUMBER_ERR_MESSAGE = "ID must be a whole number";

    public AssignBugCommand(TaskManagementRepository repository) {
        super(repository);
    }


    @Override
    protected boolean requiresLogin() {
        return true;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS, INVALID_NUMBER_OF_ARGUMENTS);
        int idBugToReassign = ParsingHelpers.tryParseInt(parameters.get(0), ID_WHOLE_NUMBER_ERR_MESSAGE);
        String newAssignee = parameters.get(1);
        return reassignBug(idBugToReassign, newAssignee);
    }

    private String reassignBug(int idBugToReassign, String newAssignee) {
        Member member = getRepository().getLoggedInMember();
        Team memberTeam = getRepository().findTeamByMember(member.getName());
        List<Member> membersInTeam = memberTeam.getMembers();
        List<Board> boardsList = memberTeam.getBoards();

        for (Board board : boardsList) {
            List<Bug> bugs = board.getBugs();
            for (Bug bug : bugs) {
                if (bug.getId() == idBugToReassign) {
                    Member oldAssignee = getRepository().findMemberByUsername(bug.getAssignee());
                    throwIfInvalidAssignee(newAssignee, memberTeam, membersInTeam);
                    bug.changeAssignee(newAssignee);
                    bug.addHistory(String.format(CHANGED_THE_ASSIGNEE_OF_BUG_S_FROM_S_TO_S, member.getName(), bug.getTitle(), oldAssignee.getName(), newAssignee));
                    member.addHistory(String.format(CHANGED_THE_ASSIGNEE_OF_BUG_S_FROM_S_TO_S, member.getName(), bug.getTitle(), oldAssignee.getName(), newAssignee));
                    return String.format(TASK_REASSIGNED_SUCCESSFULLY, idBugToReassign, oldAssignee.getName(), newAssignee);
                }
            }
        }
        throw new IllegalArgumentException(String.format(NO_BUG_ERR_MESSAGE, idBugToReassign, memberTeam.getName()));
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

