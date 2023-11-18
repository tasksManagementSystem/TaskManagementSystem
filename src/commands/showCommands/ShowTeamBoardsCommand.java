package commands.showCommands;

import commands.BaseCommand;
import core.contracts.TaskManagementRepository;
import models.contracts.Board;
import models.contracts.Team;
import utils.ValidationHelpers;

import java.util.List;

public class ShowTeamBoardsCommand extends BaseCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;
    public static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments.";
    public static final String THERE_ARE_NO_BOARDS = "There are no boards in %s.";


    public ShowTeamBoardsCommand(TaskManagementRepository repository) {
        super(repository);
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS, INVALID_NUMBER_OF_ARGUMENTS);
        String teamName = parameters.get(0);
        return showTeamBoard(teamName);
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }

    private String showTeamBoard(String teamName) {
        Team team = getRepository().findTeamByName(teamName);
        List<Board> boardList = team.getBoards();
        StringBuilder sb = new StringBuilder();
        if (boardList.isEmpty()) {
            sb.append(String.format(THERE_ARE_NO_BOARDS, teamName));
            return sb.toString();
        }
        for (Board board : boardList) {
            sb.append(board.getName()).append(", ");
        }
        sb.deleteCharAt(sb.length() - 2);
        return sb.toString().trim();
    }


}

