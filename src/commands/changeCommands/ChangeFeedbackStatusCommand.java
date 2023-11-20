package commands.changeCommands;

import commands.BaseCommand;
import core.contracts.TaskManagementRepository;
import models.contracts.Board;
import models.contracts.Feedback;
import models.contracts.Member;
import models.contracts.Team;
import models.enums.StatusFeedback;
import utils.ParsingHelpers;
import utils.ValidationHelpers;

import java.util.List;

public class ChangeFeedbackStatusCommand extends BaseCommand {
    public static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments.";
    public static final int COUNT = 2;
    public static final String NO_FEEDBACK_WITH_TITLE_S = "No feedback with title %s.";
    public static final String CHANGED_THE_STATUS_OF_FEEDBACK_FROM_S_TO_S = "%s changed the status of feedback from %s to %s.";
    public static final String STATUS_OF_FEEDBACK_HAS_BEEN_CHANGED_SUCCESSFULLY_FROM_S_TO_S = "Status of feedback has been changed successfully from %s to %s!";

    public ChangeFeedbackStatusCommand(TaskManagementRepository repository) {
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
        return changeFeedbackStatus(title, status);
    }

    private String changeFeedbackStatus(String title, String status) {
        Member member = getRepository().getLoggedInMember();
        Team memberTeam = getRepository().findTeamByMember(member);

        List<Board> boardsList = memberTeam.getBoards();

        for (Board board : boardsList) {
            List<Feedback> feedbacks = board.getFeedbacks();

            for (Feedback feedback : feedbacks) {
                if (feedback.getTitle().equals(title)) {
                    StatusFeedback oldStatus = feedback.getStatusFeedback();
                    feedback.changeStatus(ParsingHelpers.tryParseEnum(status, StatusFeedback.class));


                    feedback.addHistory(String.format(CHANGED_THE_STATUS_OF_FEEDBACK_FROM_S_TO_S, member.getName(), oldStatus, feedback.getStatusFeedback()));
                    member.addHistory(String.format(CHANGED_THE_STATUS_OF_FEEDBACK_FROM_S_TO_S, member.getName(), oldStatus, feedback.getStatusFeedback()));

                    return String.format(STATUS_OF_FEEDBACK_HAS_BEEN_CHANGED_SUCCESSFULLY_FROM_S_TO_S, oldStatus, feedback.getStatusFeedback());

                }

            }
        }
        throw new IllegalArgumentException(String.format(NO_FEEDBACK_WITH_TITLE_S, title));
    }
}