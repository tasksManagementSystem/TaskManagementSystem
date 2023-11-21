package commands.addCommands;

import commands.BaseCommand;
import core.contracts.TaskManagementRepository;
import models.CommentImpl;
import models.contracts.Board;
import models.contracts.Comment;
import utils.ValidationHelpers;

import java.util.List;
import java.util.Scanner;

public class AddCommentToTaskCommand extends BaseCommand {
    public static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments.";
    public static final String COMMENT_ADDED_SUCCESSFULLY = "Comment added successfully to task %s by %s.";

    public static final int COUNT = 3;

    public AddCommentToTaskCommand(TaskManagementRepository repository) {
        super(repository);
    }

    @Override
    protected boolean requiresLogin() {
        return false;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentCount(parameters, COUNT, INVALID_NUMBER_OF_ARGUMENTS);
        String author = parameters.get(0);
        String task = parameters.get(1);
        String boardName = parameters.get(2);
        return createComment(author, task, boardName);
    }

    private String createComment(String author, String task, String boardName) {
        Board board = getRepository().findBoardByName(boardName);
        Scanner sc = new Scanner(System.in);
        String writeComment = sc.nextLine();
        Comment comment = new CommentImpl(writeComment, author);
        getRepository().addComments(comment);
        board.addHistory(String.format(COMMENT_ADDED_SUCCESSFULLY, task, author));
        return String.format(COMMENT_ADDED_SUCCESSFULLY, task, author);
    }
}
