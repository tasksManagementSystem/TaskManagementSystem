package commands.addCommands;

import commands.BaseCommand;
import core.contracts.TaskManagementRepository;
import models.CommentImpl;
import models.contracts.*;
import utils.ParsingHelpers;
import utils.ValidationHelpers;

import java.util.List;

public class AddCommentToTaskCommand extends BaseCommand {
    public static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments.";
    public static final String COMMENT_ADDED_SUCCESSFULLY = "Comment added successfully to task %s by %s.";

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 5;

    public static final String ID_WHOLE_NUMBER_ERR_MESSAGE = "ID must be a whole number";

    public AddCommentToTaskCommand(TaskManagementRepository repository) {
        super(repository);
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS, INVALID_NUMBER_OF_ARGUMENTS);
        String comment = parameters.get(0);
        String author = parameters.get(1);
        String task = parameters.get(2);
        int id = ParsingHelpers.tryParseInt(parameters.get(3), ID_WHOLE_NUMBER_ERR_MESSAGE);
        String boardName = parameters.get(4);
        return createComment(comment, author, task, id, boardName);
    }

    private String createComment(String comment, String author, String task, int id, String boardName) {
        Board board = getRepository().findBoardByName(boardName);
        Comment newComment = new CommentImpl(comment, author);
        if (task.equals("Bug")) {
            Bug bug = getRepository().findElementById(getRepository().getBugList(), id, "Bug");
            bug.addComment(newComment);
        } else if (task.equals("Story")) {
            Story story = getRepository().findElementById(getRepository().getStoryList(), id, "Story");
            story.addComment(newComment);
        } else if (task.equals("Feedback")) {
            Feedback feedback = getRepository().findElementById(getRepository().getFeedbackList(), id, "Feedback");
            feedback.addComment(newComment);
        }

        getRepository().addComments(newComment);
        board.addHistory(String.format(COMMENT_ADDED_SUCCESSFULLY, task, author));
        return String.format(COMMENT_ADDED_SUCCESSFULLY, task, author);
    }
}
