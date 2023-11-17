package core;

import commands.LoginCommand;
import commands.LogoutCommand;
import commands.changeCommands.ChangeFeedbackRatingCommand;
import commands.contracts.Command;
import commands.createCommands.CreateNewMember;
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
            case LOGIN:
                return new LoginCommand(taskManagementRepository);
            case LOGOUT:
                return new LogoutCommand((TaskManagementRepositoryImpl) taskManagementRepository);
            case CREATEMEMBER:
                return new CreateNewMember(taskManagementRepository);
            case SHOW_TEAM_BOARD_ACTIVITY:
               return new ShowTeamBoardsCommand(taskManagementRepository);
            case CREATE_NEW_BOARD:
                return new CreateNewBoard(taskManagementRepository);
            case CHANGE_FEEDBACK_RATING:
                return new ChangeFeedbackRatingCommand(taskManagementRepository);
                return new ShowTeamBoardsCommand(taskManagementRepository);
            case SHOW_BOARD_ACTIVITY:
                return new ShowBoardActivityCommand(taskManagementRepository);

            default:
                throw new IllegalArgumentException();
        }

    }
}
