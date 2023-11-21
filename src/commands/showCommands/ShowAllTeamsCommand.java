package commands.showCommands;

import commands.BaseCommand;
import core.contracts.TaskManagementRepository;
import models.contracts.Team;
import utils.ValidationHelpers;

import java.util.List;

public class ShowAllTeamsCommand extends BaseCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;
    public static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments.";


    public ShowAllTeamsCommand(TaskManagementRepository repository) {
        super(repository);
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS, INVALID_NUMBER_OF_ARGUMENTS);

        return showAllTeams();
    }

    @Override
    protected boolean requiresLogin() {
        return false;
    }

    private String showAllTeams() {
        List<Team> teams = getRepository().getTeamsList();
        StringBuilder allTeamsNames = new StringBuilder();
        allTeamsNames.append("All teams: ");
        allTeamsNames.append(getRepository().showAllTeams());
        allTeamsNames.deleteCharAt(allTeamsNames.length() - 2);

        return allTeamsNames.toString().trim();

    }

}
