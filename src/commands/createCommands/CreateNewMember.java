package commands.createCommands;

import commands.BaseCommand;
import core.contracts.TaskManagementRepository;
import models.contracts.Member;
import utils.ValidationHelpers;

import java.util.List;

public class CreateNewMember extends BaseCommand {


    public static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments.";
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    public static final String MEMBER_S_REGISTER_SUCCESSFULLY = "Member %s register successfully.";

    public CreateNewMember(TaskManagementRepository repository) {
        super(repository);
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS, INVALID_NUMBER_OF_ARGUMENTS);
        String username = parameters.get(0);

        return registerMember(username);

    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }

    private String registerMember(String username) {
        Member member = getRepository().createMember(username);
        getRepository().addMember(member);

        member.addHistory(String.format(MEMBER_S_REGISTER_SUCCESSFULLY, username));
        return String.format(MEMBER_S_REGISTER_SUCCESSFULLY, username);
    }

}
