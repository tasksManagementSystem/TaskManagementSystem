package commands.showCommands;

import commands.BaseCommand;
import core.contracts.TaskManagementRepository;
import models.contracts.Board;
import models.contracts.Team;
import utils.ValidationHelpers;

import java.util.List;

public class ShowTeamBoardsCommand extends BaseCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;
    public static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments.";



    public ShowTeamBoardsCommand(TaskManagementRepository repository) {
        super(repository);
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS,INVALID_NUMBER_OF_ARGUMENTS);

        return showTeamBoard();
    }
    private String showTeamBoard() {
        List<Team> teams = getRepository().getTeams();
        StringBuilder sb=new StringBuilder();
        for(Team team:teams){
            sb.append(team.getBoards());
        }
        return sb.toString();


    }
}
