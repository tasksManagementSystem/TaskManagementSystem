package commands.showCommands;

import commands.BaseCommand;
import core.contracts.TaskManagementRepository;
import models.contracts.History;
import models.contracts.Member;
import models.contracts.Team;
import utils.ValidationHelpers;

import java.util.List;

public class ShowTeamActivityCommand extends BaseCommand {

    public static final String NO_ACTIVITY_FOR_TEAM =
            "Currently there is no activity to display for this team.";
    public static final int EXPECT_NUMBER_OF_ARGUMENTS = 0;

    public ShowTeamActivityCommand(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentCount(parameters,
                EXPECT_NUMBER_OF_ARGUMENTS, NO_ACTIVITY_FOR_TEAM);
        return showMemberActivity();
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }


    private String showMemberActivity() {
        List<Team> teamList = getRepository().getTeamsList();
        StringBuilder sb = new StringBuilder();
        for (Team team : teamList) {
            sb.append("Team:" + team.getName());
            sb.append(System.lineSeparator());
            List<Member> memberList = team.getMembers();
            for (Member member : memberList) {
                List<History> histories = member.getActivityHistory();
                for (History history : histories) {
                    sb.append(history);
                }

            }
            sb.append(System.lineSeparator());

        }
        return sb.toString();

    }

}
