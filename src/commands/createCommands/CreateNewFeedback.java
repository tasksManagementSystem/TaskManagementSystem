package commands.createCommands;

import commands.BaseCommand;
import core.contracts.TaskManagementRepository;
import models.contracts.*;
import utils.ParsingHelpers;
import utils.ValidationHelpers;

import java.util.List;

public class CreateNewFeedback extends BaseCommand {
    public static final String FEEDBACK_CREATED = "Feedback %s created successfully in board %s!";

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 4;
    public static final String RATING_WHOLE_NUMBER_ERR_MESSAGE = "Rating must be a whole number1";
    public static final String TITLE_EXIST_ERR_MESSAGE = "Task with such a title already exists!";
    public static final String BOARD_NOT_EXIST_ERR_MESSAGE = "Board %s does not exist in this team!";
    public CreateNewFeedback(TaskManagementRepository repository) {
        super(repository);
    }

    @Override
    protected boolean requiresLogin() {
        return false;
    }
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS, "Wrong number of arguments.");
        String description = parameters.get(0);
        String title = parameters.get(1);
        String boardToAdd = parameters.get(2);
        int rating = ParsingHelpers.tryParseInt(parameters.get(3), RATING_WHOLE_NUMBER_ERR_MESSAGE);

        return createFeedback(title, boardToAdd, description, rating);
    }

    private String createFeedback(String title, String boardToAdd, String description, int rating) {
        Member member = getRepository().getLoggedInMember();
        Team teamOfLoggedInMember = getRepository().findTeamOfMember(member.getName());
        List<Board> boards = teamOfLoggedInMember.getBoards();
        Board board = findBoardInTeam(boards, boardToAdd);
        List<Task> taskList = board.getTasks();
        throwIfTaskExist(title, taskList);
        Feedback feedbackToAdd = getRepository().createFeedback(title, description, rating);
        board.addFeedback(feedbackToAdd);
        member.logEvent(String.format("Feedback %s created by member %s", title, member.getName()));
        feedbackToAdd.logEvent(String.format("Feedback %s created by member %s", title, member.getName()));

        return String.format(FEEDBACK_CREATED, title, boardToAdd);
    }

    private static void throwIfTaskExist(String nameOfTask, List<Task> taskList) {
        for (Task task : taskList) {
            if (task.getTitle().equals(nameOfTask)) {
                throw new IllegalArgumentException(TITLE_EXIST_ERR_MESSAGE);
            }
        }
    }

    private Board findBoardInTeam(List<Board> boardList, String board) {
        for (Board board1 : boardList) {
            if (board1.getName().equals(board)) {
                return board1;
            }
        }
        throw new IllegalArgumentException(String.format(BOARD_NOT_EXIST_ERR_MESSAGE, board));
    }


}