package commands.createCommands;

import commands.BaseCommand;
import core.contracts.TaskManagementRepository;
import models.contracts.Board;
import utils.ValidationHelpers;

import java.util.List;

public class CreateNewBoard extends BaseCommand {
    public static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments.";
    public static final int COUNT = 1;
    public static final String BOARD_CREATED_SUCCESSFULLY = "Board with name %s was created in a team.";


    public CreateNewBoard(TaskManagementRepository repository) {
        super(repository);
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentCount(parameters, COUNT, INVALID_NUMBER_OF_ARGUMENTS);
        String name = parameters.get(0);
        return createBoard(name);
    }

    private String createBoard(String name) {
        Board board = getRepository().createBoard(name);
        getRepository().addBoard(board);
        return String.format(BOARD_CREATED_SUCCESSFULLY, name);
    }

}
