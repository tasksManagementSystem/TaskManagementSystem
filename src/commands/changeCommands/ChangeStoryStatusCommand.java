package commands.changeCommands;

import commands.BaseCommand;
import core.contracts.TaskManagementRepository;
import models.contracts.*;
import models.enums.StatusStory;
import utils.ParsingHelpers;
import utils.ValidationHelpers;

import java.util.List;

public class ChangeStoryStatusCommand extends BaseCommand {
    public static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments.";
    public static final int COUNT = 2;
    public static final String NO_STORY_WITH_TITLE_S = "No story with title %s.";
    public static final String CHANGED_THE_STATUS_OF_STORY_FROM_S_TO_S = "%s changed the status of story from %s to %s.";
    public static final String STATUS_OF_STORY_HAS_BEEN_CHANGED_SUCCESSFULLY_FROM_S_TO_S = "Status of story has been changed successfully from %s to %s!";

    public ChangeStoryStatusCommand(TaskManagementRepository repository) {
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
        String status = parameters.get(1);
        return changeStoryStatus(title, status);
    }

    private String changeStoryStatus(String title, String status) {
        Member member = getRepository().getLoggedInMember();
        Team memberTeam = getRepository().findTeamByMember(member);

        List<Board> boardsList = memberTeam.getBoards();

        for (Board board : boardsList) {
            List<Story> stories = board.getStories();

            for (Story story : stories) {
                if (story.getTitle().equals(title)) {
                    StatusStory oldStatus = story.getStatusStory();
                    story.changeStatus(ParsingHelpers.tryParseEnum(status, StatusStory.class));


                    story.addHistory(String.format(CHANGED_THE_STATUS_OF_STORY_FROM_S_TO_S, member.getName(), oldStatus, story.getStatusStory()));
                    member.addHistory(String.format(CHANGED_THE_STATUS_OF_STORY_FROM_S_TO_S, member.getName(), oldStatus, story.getStatusStory()));

                    return String.format(STATUS_OF_STORY_HAS_BEEN_CHANGED_SUCCESSFULLY_FROM_S_TO_S, oldStatus, story.getStatusStory());

                }

            }
        }
        throw new IllegalArgumentException(String.format(NO_STORY_WITH_TITLE_S, title));
    }
}
