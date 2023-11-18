package commands.addCommands;

import commands.BaseCommand;
import core.TaskManagementEngineImpl;
import core.contracts.TaskManagementRepository;
import models.contracts.Member;
import models.contracts.Team;
import utils.ValidationHelpers;

import java.util.List;

public class AddMemberToTeamCommand extends BaseCommand {

    public static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments.";
    public static final String MEMBER_ADDED_SUCCESSFULLY = "Member added successfully.";
    public static final int COUNT = 1;
    public AddMemberToTeamCommand(TaskManagementRepository repository){
        super(repository);
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentCount(parameters, COUNT, INVALID_NUMBER_OF_ARGUMENTS);
        String teamName = parameters.get(0);

        return addMemberToTeam(teamName);

    }


    private String addMemberToTeam(String teamName){
        Member member = getRepository().getLoggedInMember();
        List<Team> teams = getRepository().getTeamsList();
        for (Team team:
             teams) {
            if(team.getName().equals(teamName)){
                team.addMembers(member);
            }

        }

        return MEMBER_ADDED_SUCCESSFULLY;
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }
}
