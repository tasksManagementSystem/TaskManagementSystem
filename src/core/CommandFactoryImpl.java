package core;

import commands.contracts.Command;
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
//            case SHOWUSERS:
//                return new ShowUsersCommand(vehicleDealershipRepository);
//            case ADDCOMMENT:
//                return new AddCommentCommand(vehicleDealershipRepository);
//            case ADDVEHICLE:
//                return new AddVehicleCommand(vehicleDealershipRepository);
//            case REGISTERUSER:
//                return new RegisterUserCommand(vehicleDealershipRepository);
//            case SHOWVEHICLES:
//                return new ShowVehiclesCommand(vehicleDealershipRepository);
//            case REMOVECOMMENT:
//                return new RemoveCommentCommand(vehicleDealershipRepository);
//            case REMOVEVEHICLE:
//                return new RemoveVehicleCommand(vehicleDealershipRepository);
            default:
                throw new IllegalArgumentException();
        }

    }
}
