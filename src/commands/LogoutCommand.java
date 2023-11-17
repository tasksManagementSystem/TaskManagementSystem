package commands;

import core.TaskManagementRepositoryImpl;

import java.util.List;

public class LogoutCommand extends BaseCommand {

    public final static String MEMBER_LOGGED_OUT = "Member logged out!";

    public LogoutCommand(TaskManagementRepositoryImpl taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }

    @Override
    public String execute(List<String> parameters) {
        getRepository().logout();
        return MEMBER_LOGGED_OUT;
    }

}