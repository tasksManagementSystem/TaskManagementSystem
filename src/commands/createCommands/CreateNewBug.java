package commands.createCommands;

import commands.BaseCommand;
import core.contracts.TaskManagementRepository;
import models.contracts.*;
import models.enums.Priority;
import models.enums.Severity;
import utils.ParsingHelpers;
import utils.ValidationHelpers;
import java.util.Arrays;
import java.util.List;

public class CreateNewBug extends BaseCommand {


    public static final String WRONG_NUMBERS_OF_ARGUMENTS = "Wrong numbers of arguments.";
    public static final String BUG_CREATED = "Bug %s created successfully in board %s!";

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 7;
    public static final String ASSIGNEE_ERR_MESSAGE = "Assignee %s is not part of team %s!";


    public CreateNewBug(TaskManagementRepository repository) {
        super(repository);
    }


    @Override
    protected boolean requiresLogin() {
        return true;
    }

    @Override
    public String execute(List<String> parameters) {

        ValidationHelpers.validateArgumentCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS, WRONG_NUMBERS_OF_ARGUMENTS);
        String boardToAdd = parameters.get(0);
        String title = parameters.get(1);
        String description = parameters.get(2);
        List<String> stepsToReproduce = Arrays.asList(parameters.get(3).split(";"));
        Priority priority = ParsingHelpers.tryParseEnum(parameters.get(4), Priority.class);
        Severity severity = ParsingHelpers.tryParseEnum(parameters.get(5), Severity.class);
        String assignee = parameters.get(6);

        return createBug(boardToAdd, title, description, stepsToReproduce, priority, severity, assignee);
    }

    public String createBug(String boardToAdd, String title, String description, List<String> stepsToReproduce, Priority priority, Severity severity, String assignee) {
        Member member = getRepository().getLoggedInMember();
        Team teamOfLoggedInMember = getRepository().findTeamByMember(member.getName());
        List<Member> membersInTeam = teamOfLoggedInMember.getMembers();
        throwIfInvalidAssignee(assignee, teamOfLoggedInMember, membersInTeam);
        Board board = findBoardInTeam(teamOfLoggedInMember, boardToAdd);
        Bug bugToAdd = getRepository().createBug(title, boardToAdd, description, stepsToReproduce, priority, severity, assignee);

        getRepository().addBug(bugToAdd);
        board.addBug(bugToAdd);

        member.addHistory(String.format(BUG_CREATED, title, boardToAdd));
        board.addHistory(String.format(BUG_CREATED, title, boardToAdd));
        return String.format(BUG_CREATED, title, boardToAdd);
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

    private Board findBoardInTeam(Team teamOfLoggedInMember, String board) {
        List<Board> boards = teamOfLoggedInMember.getBoards();
        for (Board board1 : boards) {
            if (board1.getName().equals(board)) {
                return board1;
            }
        }
        throw new IllegalArgumentException("Board does not exist in this team");
    }

}