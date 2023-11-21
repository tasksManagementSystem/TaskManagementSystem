package commands.changeCommands;

import commands.BaseCommand;
import core.contracts.TaskManagementRepository;
import models.contracts.Board;
import models.contracts.Member;
import models.contracts.Story;
import models.contracts.Team;
import models.enums.Priority;
import models.enums.StatusStory;
import utils.ParsingHelpers;
import utils.ValidationHelpers;

import java.util.List;

public class ChangeStoryPriorityCommand extends BaseCommand {
    public static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments.";
    public static final int COUNT = 2;
    public static final String NO_STORY_WITH_TITLE_S = "No story with title %s.";
    public static final String CHANGED_THE_PRIORITY_OF_STORY_FROM_S_TO_S = "%s changed the priority of story from %s to %s.";
    public static final String PRIORITY_OF_STORY_HAS_BEEN_CHANGED_SUCCESSFULLY_FROM_S_TO_S = "Priority of story has been changed successfully from %s to %s!";

    public ChangeStoryPriorityCommand(TaskManagementRepository repository) {
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
        String priority = parameters.get(1);
        return changeStoryStatus(title, priority);
    }

    private String changeStoryStatus(String title, String priority) {
        Member member = getRepository().getLoggedInMember();
        Team memberTeam = getRepository().findTeamByMember(member);

        List<Board> boardsList = memberTeam.getBoards();

        for (Board board : boardsList) {
            List<Story> stories = board.getStories();

            for (Story story : stories) {
                if (story.getTitle().equals(title)) {
                    Priority oldPriority = story.getPriority();
                    story.changePriority(ParsingHelpers.tryParseEnum(priority, Priority.class));


                    story.addHistory(String.format(CHANGED_THE_PRIORITY_OF_STORY_FROM_S_TO_S, member.getName(), oldPriority, story.getPriority()));
                    member.addHistory(String.format(CHANGED_THE_PRIORITY_OF_STORY_FROM_S_TO_S, member.getName(), oldPriority, story.getPriority()));

                    return String.format(PRIORITY_OF_STORY_HAS_BEEN_CHANGED_SUCCESSFULLY_FROM_S_TO_S, oldPriority, story.getPriority());

                }

            }
        }
        throw new IllegalArgumentException(String.format(NO_STORY_WITH_TITLE_S, title));
    }
}