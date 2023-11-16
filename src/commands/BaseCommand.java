package commands;

import commands.contracts.Command;
import core.contracts.TaskManagementRepository;

import java.util.List;

public abstract class BaseCommand implements Command {
    private final TaskManagementRepository repository;

    public BaseCommand(TaskManagementRepository repository) {
        this.repository = repository;
    }


    @Override
    public String execute(List<String> parameters) {
        return execute(parameters);
    }

    public TaskManagementRepository getRepository() {
        return repository;
    }



}