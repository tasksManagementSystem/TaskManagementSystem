package commands.showCommands;

import commands.BaseCommand;
import core.contracts.TaskManagementRepository;
import models.contracts.Board;
import utils.ValidationHelpers;

import java.util.List;

public class ShowBoardActivityCommand extends BaseCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;
    public static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments.";

    public ShowBoardActivityCommand(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS, INVALID_NUMBER_OF_ARGUMENTS);
        return showBoardActivity();

    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }

    private String showBoardActivity() {
        List<Board> boards = getRepository().getBoards();
        StringBuilder sb = new StringBuilder();
        for (Board board1 : boards) {
            sb.append(board1.getActivityHistory());
        }
        return sb.toString();


    }
}