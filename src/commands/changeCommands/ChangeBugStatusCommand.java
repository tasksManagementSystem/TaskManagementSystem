package commands.changeCommands;

import commands.BaseCommand;
import core.contracts.TaskManagementRepository;
import models.contracts.Board;
import models.contracts.Bug;
import models.contracts.Member;
import models.contracts.Team;
import models.enums.StatusBug;
import utils.ParsingHelpers;
import utils.ValidationHelpers;

import java.util.List;

public class ChangeBugStatusCommand extends BaseCommand {
    public static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments.";
    public static final int COUNT = 2;
    public static final String NO_BUG_WITH_TITLE_S = "No bug with title %s.";
    public static final String CHANGED_THE_STATUS_OF_BUG_FROM_S_TO_S = "%s changed the status of bug from %s to %s.";
    public static final String STATUS_OF_BUG_HAS_BEEN_CHANGED_SUCCESSFULLY_FROM_S_TO_S = "Status of bug has been changed successfully from %s to %s!";

    public ChangeBugStatusCommand(TaskManagementRepository repository) {
        super(repository);
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentCount(parameters, COUNT, INVALID_NUMBER_OF_ARGUMENTS);
        String title = parameters.get(0);
        String status = parameters.get(1);
        return changeBugStatus(title, status);
    }

    private String changeBugStatus(String title, String status) {
        Member member = getRepository().getLoggedInMember();
        Team memberTeam = getRepository().findTeamByMember(member);

        List<Board> boardsList = memberTeam.getBoards();

        for (Board board : boardsList) {
            List<Bug> bugs = board.getBugs();

            for (Bug bug : bugs) {
                if (bug.getTitle().equals(title)) {
                    StatusBug oldStatus = bug.getStatusBug();
                    bug.changeStatus(ParsingHelpers.tryParseEnum(status, StatusBug.class));


                    bug.addHistory(String.format(CHANGED_THE_STATUS_OF_BUG_FROM_S_TO_S, member.getName(), oldStatus, bug.getStatusBug()));
                    member.addHistory(String.format(CHANGED_THE_STATUS_OF_BUG_FROM_S_TO_S, member.getName(), oldStatus, bug.getStatusBug()));

                    return String.format(STATUS_OF_BUG_HAS_BEEN_CHANGED_SUCCESSFULLY_FROM_S_TO_S, oldStatus, bug.getStatusBug());

                }

            }
        }
        throw new IllegalArgumentException(String.format(NO_BUG_WITH_TITLE_S, title));
    }
}
