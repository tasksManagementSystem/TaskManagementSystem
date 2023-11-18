package commands.createCommands;

import commands.BaseCommand;
import core.TaskManagementRepositoryImpl;
import core.contracts.TaskManagementRepository;
import models.contracts.Board;
import models.contracts.Team;
import utils.ValidationHelpers;

import java.util.List;

public class CreateNewBoard extends BaseCommand {
    public static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments.";
    public static final int COUNT = 2;
    public static final String BOARD_CREATED_SUCCESSFULLY = "Board with name %s was created in a team.";


    public CreateNewBoard(TaskManagementRepository repository) {
        super(repository);
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentCount(parameters, COUNT, INVALID_NUMBER_OF_ARGUMENTS);
        String boardName = parameters.get(0);
        String teamName = parameters.get(1);
        return createBoard(boardName,teamName);
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }


    private String createBoard(String boardName, String teamName) {
        Board board = getRepository().createBoard(boardName);
        Team team = getRepository().findTeamByName(teamName);
        team.addBoard(board);
        TaskManagementRepositoryImpl.boardList.add(board);

        return String.format(BOARD_CREATED_SUCCESSFULLY, boardName);
    }

}
