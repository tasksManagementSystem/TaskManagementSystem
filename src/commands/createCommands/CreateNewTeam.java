package commands.createCommands;

import commands.BaseCommand;
import core.contracts.TaskManagementRepository;
import models.contracts.Board;
import models.contracts.Team;
import utils.ValidationHelpers;

import java.util.List;

public class CreateNewTeam extends BaseCommand {
    public static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments.";
    public static final int COUNT = 1;
    public static final String TEAM_CREATED_SUCCESSFULLY = "Team with name %s was created.";


    public CreateNewTeam(TaskManagementRepository repository) {
        super(repository);
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentCount(parameters, COUNT, INVALID_NUMBER_OF_ARGUMENTS);
        String name = parameters.get(0);
        return createTeam(name);

    }


    private String createTeam(String name) {
        Team team = getRepository().createTeam(name);
        return String.format(TEAM_CREATED_SUCCESSFULLY, name);
    }
}
