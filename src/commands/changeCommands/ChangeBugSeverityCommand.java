package commands.changeCommands;

import commands.BaseCommand;
import core.contracts.TaskManagementRepository;
import models.contracts.Board;
import models.contracts.Bug;
import models.contracts.Member;
import models.contracts.Team;
import models.enums.Priority;
import models.enums.Severity;
import utils.ParsingHelpers;
import utils.ValidationHelpers;

import java.util.List;

public class ChangeBugSeverityCommand extends BaseCommand {
    public static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments.";
    public static final int COUNT = 2;
    public static final String NO_BUG_WITH_TITLE_S = "No bug with title %s.";
    public static final String CHANGED_THE_SEVERITY_OF_BUG_FROM_S_TO_S = "%s changed the severity of bug from %s to %s.";
    public static final String SEVERITY_OF_BUG_HAS_BEEN_CHANGED_SUCCESSFULLY_FROM_S_TO_S = "Severity of bug has been changed successfully from %s to %s!";

    public ChangeBugSeverityCommand(TaskManagementRepository repository) {
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
        String severity = parameters.get(1);
        return changeBugPriority(title, severity);
    }

    private String changeBugPriority(String title, String severity) {
        Member member = getRepository().getLoggedInMember();
        Team memberTeam = getRepository().findTeamByMember(member);

        List<Board> boardsList = memberTeam.getBoards();

        for (Board board : boardsList) {
            List<Bug> bugs = board.getBugs();

            for (Bug bug : bugs) {
                if (bug.getTitle().equals(title)) {
                    Severity oldSeverity = bug.getSeverity();
                    bug.changeSeverity(ParsingHelpers.tryParseEnum(severity, Severity.class));


                    bug.addHistory(String.format(CHANGED_THE_SEVERITY_OF_BUG_FROM_S_TO_S, member.getName(), oldSeverity, bug.getSeverity()));
                    member.addHistory(String.format(CHANGED_THE_SEVERITY_OF_BUG_FROM_S_TO_S, member.getName(), oldSeverity, bug.getSeverity()));

                    return String.format(SEVERITY_OF_BUG_HAS_BEEN_CHANGED_SUCCESSFULLY_FROM_S_TO_S, oldSeverity, bug.getSeverity());

                }

            }
        }
        throw new IllegalArgumentException(String.format(NO_BUG_WITH_TITLE_S, title));
    }
}