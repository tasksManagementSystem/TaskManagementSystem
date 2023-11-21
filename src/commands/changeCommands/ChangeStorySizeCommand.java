package commands.changeCommands;

import commands.BaseCommand;
import core.contracts.TaskManagementRepository;
import models.contracts.Board;
import models.contracts.Member;
import models.contracts.Story;
import models.contracts.Team;
import models.enums.Size;
import models.enums.StatusStory;
import utils.ParsingHelpers;
import utils.ValidationHelpers;

import java.util.List;

public class ChangeStorySizeCommand extends BaseCommand {
    public static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments.";
    public static final int COUNT = 2;
    public static final String NO_STORY_WITH_TITLE_S = "No story with title %s.";
    public static final String CHANGED_THE_SIZE_OF_STORY_FROM_S_TO_S = "%s changed the size of story from %s to %s.";
    public static final String SIZE_OF_STORY_HAS_BEEN_CHANGED_SUCCESSFULLY_FROM_S_TO_S = "Size of story has been changed successfully from %s to %s!";

    public ChangeStorySizeCommand(TaskManagementRepository repository) {
        super(repository);
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentCount(parameters, COUNT, INVALID_NUMBER_OF_ARGUMENTS);
        String title = parameters.get(0);
        String size = parameters.get(1);
        return changeStoryStatus(title, size);
    }

    private String changeStoryStatus(String title, String size) {
        Member member = getRepository().getLoggedInMember();
        Team memberTeam = getRepository().findTeamByMember(member);

        List<Board> boardsList = memberTeam.getBoards();

        for (Board board : boardsList) {
            List<Story> stories = board.getStories();

            for (Story story : stories) {
                if (story.getTitle().equals(title)) {
                    Size oldSize = story.getSize();
                    story.changeSize(ParsingHelpers.tryParseEnum(size, Size.class));


                    story.addHistory(String.format(CHANGED_THE_SIZE_OF_STORY_FROM_S_TO_S, member.getName(), oldSize, story.getSize()));
                    member.addHistory(String.format(CHANGED_THE_SIZE_OF_STORY_FROM_S_TO_S, member.getName(), oldSize, story.getSize()));

                    return String.format(SIZE_OF_STORY_HAS_BEEN_CHANGED_SUCCESSFULLY_FROM_S_TO_S, oldSize, story.getSize());

                }

            }
        }
        throw new IllegalArgumentException(String.format(NO_STORY_WITH_TITLE_S, title));
    }
}