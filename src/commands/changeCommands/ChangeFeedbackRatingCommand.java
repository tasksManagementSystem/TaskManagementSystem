package commands.changeCommands;

import commands.BaseCommand;
import core.contracts.TaskManagementRepository;
import models.contracts.*;
import utils.ParsingHelpers;
import utils.ValidationHelpers;

import java.util.List;

public class ChangeFeedbackRatingCommand extends BaseCommand {

    public static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments.";
    public static final String INVALID_INPUT_MESSAGE = "Rating must be a whole number";
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    public static final String THERE_IS_NO_TASK_WITH_TITLE_S = "There is no Task with title %s and description %s ";
    public static final String BOARD_NOT_EXIST_ERR_MESSAGE = "Board %s does not exist in this team!";
    public static final String FEEDBACK_RATING_CHANGED = "The rating of item with ID %d switched from %d to %d.";


    public ChangeFeedbackRatingCommand(TaskManagementRepository repository) {
        super(repository);
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS, INVALID_NUMBER_OF_ARGUMENTS);
        String title = parameters.get(0);
        String description = parameters.get(1);
        String boardToChange = parameters.get(2);
        int rating = ParsingHelpers.tryParseInt(parameters.get(3), INVALID_INPUT_MESSAGE);

        return changeFeedback(title, description, boardToChange, rating);
    }


    private String changeFeedback(String title, String description, String boardToChange, int rating) {

        Member member = getRepository().getLoggedInMember();
        Team teamOfLoggedInMember = getRepository().findTeamByMember(member);
        List<Board> boards = teamOfLoggedInMember.getBoards();
        Board board = findBoardInTeam(boards, boardToChange);
        List<Task> taskList = board.getTasks();
        Task task = findTaskInBoard(taskList, title, description);
        Feedback feedbackToChange = getRepository().createFeedback(title, description, rating);
        task.addHistory(String.format(String.format(FEEDBACK_RATING_CHANGED, feedbackToChange.getId(), board.changeFeedback(feedbackToChange), rating)));

        return String.valueOf(board.getActivityHistory().add(String.format(String.format(FEEDBACK_RATING_CHANGED, feedbackToChange.getId(), board.changeFeedback(feedbackToChange), rating))));

    }

    private Board findBoardInTeam(List<Board> boardList, String board) {
        for (Board board1 : boardList) {
            if (board1.getName().equals(board)) {
                return board1;
            }
        }
        throw new IllegalArgumentException(String.format(BOARD_NOT_EXIST_ERR_MESSAGE, board));
    }

    private Task findTaskInBoard(List<Task> taskList, String title, String description) {
        for (Task task : taskList) {
            if (task.getTitle().equals(title) && task.getDescription().equals(description)) {
                return task;
            }
        }
        throw new IllegalArgumentException(String.format(THERE_IS_NO_TASK_WITH_TITLE_S, title, description));
    }

    @Override
    protected boolean requiresLogin() {return true;
    }
}
