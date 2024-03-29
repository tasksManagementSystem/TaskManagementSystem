package commands.createCommands;

import commands.BaseCommand;
import core.contracts.TaskManagementRepository;
import models.contracts.*;
import models.enums.Priority;
import models.enums.Size;
import utils.ParsingHelpers;
import utils.ValidationHelpers;

import java.util.List;

public class CreateNewStory extends BaseCommand {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 6;
    public static final String STORY_CREATED = "Story %s created successfully in board %s!";
    public static final String ASSIGNEE_ERR_MESSAGE = "Assignee %s is not part of team %s!";
    public static final String WRONG_NUMBER_OF_ARGUMENTS = "Wrong number of arguments.";

    public CreateNewStory(TaskManagementRepository repository) {
        super(repository);
    }


    @Override
    protected boolean requiresLogin() {
        return true;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS, WRONG_NUMBER_OF_ARGUMENTS);
        String boardToAdd = parameters.get(0);
        String title = parameters.get(1);
        String description = parameters.get(2);
        Priority priority = ParsingHelpers.tryParseEnum(parameters.get(3), Priority.class);
        Size size = ParsingHelpers.tryParseEnum(parameters.get(4), Size.class);
        String assignee = parameters.get(5);
        return createStory( boardToAdd,title,description, priority, size, assignee);
    }

    private String createStory(String boardToAdd,String title,String description, Priority priority, Size size, String assignee) {
        Member member = getRepository().getLoggedInMember();
        Team teamOfLoggedInMember = getRepository().findTeamByMember(member.getName());
        List<Member> membersInTeam = teamOfLoggedInMember.getMembers();
        throwIfInvalidAssignee(assignee, teamOfLoggedInMember, membersInTeam);
        List<Board> boards = teamOfLoggedInMember.getBoards();
        Board board = findBoardInTeam(boards, boardToAdd);
        Story storyToAdd = getRepository().createStory(title,boardToAdd, description,priority, size, assignee);

        getRepository().addStory(storyToAdd);
        getRepository().addTask(storyToAdd);
        board.addStory(storyToAdd);

        member.addHistory(String.format(STORY_CREATED, title, boardToAdd));
        board.addHistory(String.format(STORY_CREATED, title, boardToAdd));
        return String.format(STORY_CREATED, title, boardToAdd);
    }

    private static void throwIfInvalidAssignee(String assignee, Team teamOfLoggedInMember, List<Member> membersInTeam) {
        boolean isMember = membersInTeam.stream().anyMatch(member -> member.getName().equals(assignee));
        if (!isMember) {
            throw new IllegalArgumentException(String.format(ASSIGNEE_ERR_MESSAGE, assignee, teamOfLoggedInMember.getName()));
        }
    }

    private Board findBoardInTeam(List<Board> boardList, String board) {
        return boardList.stream().filter(board1 -> board1.getName().equals(board)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Board does not exist in this team"));
    }

}
