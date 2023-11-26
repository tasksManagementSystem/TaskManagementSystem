package commands.showCommands;

import commands.BaseCommand;
import core.contracts.TaskManagementRepository;
import models.contracts.Member;
import models.contracts.Team;
import utils.ValidationHelpers;

import java.util.List;

public class ShowTeamMembersCommand extends BaseCommand {

    public static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments.";
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    public static final String THERE_ARE_NO_MEMBERS_IN_S = "There are no members in %s.";

    public ShowTeamMembersCommand(TaskManagementRepository repository) {
        super(repository);

    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS, INVALID_NUMBER_OF_ARGUMENTS);
        String teamName = parameters.get(0);
        return showTeamMembers(teamName);
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }

    private String showTeamMembers(String teamName) {
        Team team = getRepository().findTeamByName(teamName);
        List<Member> memberList = team.getMembers();
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("All members in team %s: ", teamName));
        if (memberList.isEmpty()) {
            sb.append(String.format(THERE_ARE_NO_MEMBERS_IN_S, teamName));
            return sb.toString();
        }
        for (Member member : memberList) {
            sb.append(member.getName()).append(", ");
        }
        sb.deleteCharAt(sb.length() - 2);
        return sb.toString().trim();
    }
}
