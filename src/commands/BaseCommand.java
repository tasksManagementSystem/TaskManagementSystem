package commands;

import commands.contracts.Command;
import core.contracts.TaskManagementRepository;

import java.util.List;

public abstract class BaseCommand implements Command {

    public final static String MEMBER_NOT_LOGGED = "You are not logged in! Please login first!";
    private final TaskManagementRepository repository;

    public BaseCommand(TaskManagementRepository repository) {
        this.repository = repository;
    }

    public TaskManagementRepository getRepository() {
        return repository;
    }
    @Override
    public String execute(List<String> parameters) {
        if (requiresLogin() && !repository.hasLoggedInMember()) {
            throw new IllegalArgumentException(MEMBER_NOT_LOGGED);
        }
        return execute(parameters);
    }



    protected abstract boolean requiresLogin();

}