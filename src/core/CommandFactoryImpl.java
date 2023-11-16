package core;

import commands.changeCommands.ChangeFeedbackRatingCommand;
import commands.contracts.Command;
import commands.createCommands.CreateNewBoard;
import commands.enums.CommandType;
import commands.showCommands.ShowBoardActivityCommand;
import commands.showCommands.ShowTeamBoardsCommand;
import core.contracts.CommandFactory;
import core.contracts.TaskManagementRepository;
import utils.ParsingHelpers;

public class CommandFactoryImpl implements CommandFactory {
    @Override
    public Command createCommandFromCommandName(String commandTypeAsString, TaskManagementRepository taskManagementRepository) {
        CommandType commandType = ParsingHelpers.tryParseEnum(commandTypeAsString, CommandType.class);
        switch (commandType) {
            case SHOW_BOARD_ACTIVITY:
                return new ShowBoardActivityCommand(taskManagementRepository);
            case SHOW_TEAM_BOARD_ACTIVITY:
               return new ShowTeamBoardsCommand(taskManagementRepository);
            case CREATE_NEW_BOARD:
                return new CreateNewBoard(taskManagementRepository);
            case CHANGE_FEEDBACK_RATING:
                return new ChangeFeedbackRatingCommand(taskManagementRepository);
            default:
                throw new IllegalArgumentException();
        }

    }
}
