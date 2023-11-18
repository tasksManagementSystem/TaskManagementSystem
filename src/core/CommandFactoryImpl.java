package core;

import commands.LoginCommand;
import commands.LogoutCommand;
import commands.addCommands.AddMemberToTeamCommand;
import commands.changeCommands.ChangeFeedbackRatingCommand;
import commands.contracts.Command;
import commands.createCommands.*;
import commands.enums.CommandType;
import commands.showCommands.*;
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
            case CREATE_NEW_MEMBER:
                return new CreateNewMember(taskManagementRepository);
            case SHOW_TEAM_BOARD:
               return new ShowTeamBoardsCommand(taskManagementRepository);
            case CREATE_NEW_BOARD:
                return new CreateNewBoard(taskManagementRepository);
            case CHANGE_FEEDBACK_RATING:
                return new ChangeFeedbackRatingCommand(taskManagementRepository);
            case SHOW_BOARD_ACTIVITY:
                return new ShowBoardActivityCommand(taskManagementRepository);
            case CREATE_NEW_BUG:
                return new CreateNewBug(taskManagementRepository);
            case CREATE_NEW_FEEDBACK:
                return new CreateNewFeedback(taskManagementRepository);
            case CREATE_NEW_STORY:
                return new CreateNewStory(taskManagementRepository);
            case CREATE_NEW_TEAM:
                return new CreateNewTeam(taskManagementRepository);
            case SHOW_ALL_TEAMS:
                return new ShowAllTeamsCommand(taskManagementRepository);
            case SHOW_MEMBER_ACTIVITY:
                return new ShowMemberActivityCommand(taskManagementRepository);
            case SHOW_TEAM_MEMBERS:
                return new ShowTeamMembersCommand(taskManagementRepository);
            case ADD_MEMBER_TO_TEAM:
                return new AddMemberToTeamCommand(taskManagementRepository);

            default:
                throw new IllegalArgumentException();
        }

    }
}
