package commands;


import core.contracts.TaskManagementRepository;
import models.contracts.Member;
import utils.ValidationHelpers;

import java.util.List;

public class LoginCommand extends BaseCommand {
    public final static String MEMBER_LOGGED_IN = "Member %s successfully logged in!";
    public final static String MEMBER_LOGGED_IN_ALREADY = "Member %s is logged in! Please log out first!";

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    public static final String WRONG_NUMBERS_OF_PARAMETERS = "Wrong numbers of parameters.";

    public LoginCommand(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected boolean requiresLogin() {
        return false;
    }

    @Override
    public String execute(List<String> parameters) {
        throwIfMemberLoggedIn();
        ValidationHelpers.validateArgumentCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS, WRONG_NUMBERS_OF_PARAMETERS);
        String username = parameters.get(0);

        return login(username);
    }

    private String login(String username) {
        Member memberFound = getRepository().findMemberByUsername(username);
        getRepository().login(memberFound);
        memberFound.addHistory(String.format(MEMBER_LOGGED_IN,username));
        return String.format(MEMBER_LOGGED_IN, username);
    }

    private void throwIfMemberLoggedIn() {
        if (getRepository().hasLoggedInMember()) {
            throw new IllegalArgumentException(
                    String.format(MEMBER_LOGGED_IN_ALREADY, getRepository().getLoggedInMember().getName())
            );
        }
    }

}
