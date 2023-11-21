package commands.changeCommands;

import commands.BaseCommand;
import core.contracts.TaskManagementRepository;
import models.contracts.Board;
import models.contracts.Bug;
import models.contracts.Member;
import models.contracts.Team;
import models.enums.Priority;
import models.enums.StatusBug;
import utils.ParsingHelpers;
import utils.ValidationHelpers;

import java.util.List;

public class ChangeBugPriorityCommand extends BaseCommand {

     public static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments.";
    public static final int COUNT = 2;
    public static final String NO_BUG_WITH_TITLE_S = "No bug with title %s.";
    public static final String CHANGED_THE_PRIORITY_OF_BUG_FROM_S_TO_S = "%s changed the priority of bug from %s to %s.";
    public static final String PRIORITY_OF_BUG_HAS_BEEN_CHANGED_SUCCESSFULLY_FROM_S_TO_S = "Priority of bug has been changed successfully from %s to %s!";

    public ChangeBugPriorityCommand(TaskManagementRepository repository) {
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
        String priority = parameters.get(1);
        return changeBugPriority(title, priority);
    }

    private String changeBugPriority(String title, String priority) {
        Member member = getRepository().getLoggedInMember();
        Team memberTeam = getRepository().findTeamByMember(member);

        List<Board> boardsList = memberTeam.getBoards();

        for (Board board : boardsList) {
            List<Bug> bugs = board.getBugs();

            for (Bug bug : bugs) {
                if (bug.getTitle().equals(title)) {
                   Priority oldPriority = bug.getPriority();
                    bug.changePriority(ParsingHelpers.tryParseEnum(priority, Priority.class));


                    bug.addHistory(String.format(CHANGED_THE_PRIORITY_OF_BUG_FROM_S_TO_S, member.getName(), oldPriority, bug.getPriority()));
                    member.addHistory(String.format(CHANGED_THE_PRIORITY_OF_BUG_FROM_S_TO_S, member.getName(), oldPriority, bug.getPriority()));

                    return String.format(PRIORITY_OF_BUG_HAS_BEEN_CHANGED_SUCCESSFULLY_FROM_S_TO_S, oldPriority, bug.getPriority());

                }

            }
        }
        throw new IllegalArgumentException(String.format(NO_BUG_WITH_TITLE_S, title));
    }
}