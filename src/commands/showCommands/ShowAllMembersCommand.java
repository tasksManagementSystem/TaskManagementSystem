package commands.showCommands;

import commands.BaseCommand;
import core.contracts.TaskManagementRepository;
import models.contracts.Member;
import models.contracts.Team;
import utils.ValidationHelpers;

import java.util.List;

public class ShowAllMembersCommand extends BaseCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;
    public static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments.";
    public static final String THERE_ARE_NO_MEMBERS_IN_S = "There are no members in %s.";


    public ShowAllMembersCommand(TaskManagementRepository repository) {
        super(repository);
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS, INVALID_NUMBER_OF_ARGUMENTS);

        return showAllMembers();
    }


    private String showAllMembers() {
        List<Team> teams = getRepository().getTeamsList();
        StringBuilder sb = new StringBuilder();

        for (Team team : teams) {
            List<Member> memberList = team.getMembers();
            if (memberList.isEmpty()) {
                sb.append(String.format(THERE_ARE_NO_MEMBERS_IN_S,team.getName()));
                return sb.toString();
            }
            for (Member member : memberList) {
                sb.append(member.getName()).append(", ");
            }

        }sb.deleteCharAt(sb.length() - 2);
        return sb.toString().trim();
    }
}
