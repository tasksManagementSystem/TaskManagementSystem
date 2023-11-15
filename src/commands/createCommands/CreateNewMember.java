package commands.createCommands;

import commands.BaseCommand;
import core.contracts.TaskManagementRepository;
import models.contracts.Member;
import utils.ValidationHelpers;

import java.util.List;

public class CreateNewMember extends BaseCommand {


    public static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments.";
    public static final int COUNT = 1;
    public static final String MEMBER_S_ALREADY_EXIST_CHOOSE_A_DIFFERENT_NAME = "Member %s already exist. Choose a different name.";
    public static final String MEMBER_S_REGISTER_SUCCESSFULLY = "Member %s register successfully.";

    public CreateNewMember(TaskManagementRepository repository) {
        super(repository);
    }
@Override
public String execute (List<String> parameters) {
        ValidationHelpers.validateArgumentCount(parameters, COUNT, INVALID_NUMBER_OF_ARGUMENTS);
        String username = parameters.get(0);
        trowIfMemberExist(username);

        return registerMember(username);

    }

    private String registerMember(String username) {
        Member member = getRepository().createMember(username);
       return String.format(MEMBER_S_REGISTER_SUCCESSFULLY,username);
    }

    private void trowIfMemberExist(String username){
        if(getRepository().memberExist(username)){
            throw new IllegalArgumentException(
                    String.format(MEMBER_S_ALREADY_EXIST_CHOOSE_A_DIFFERENT_NAME, username)
            );
        }

    }

}
