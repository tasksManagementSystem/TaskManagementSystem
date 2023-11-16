package commands.showCommands;

import commands.BaseCommand;
import core.contracts.TaskManagementRepository;
import models.contracts.Member;
import utils.ValidationHelpers;
import java.util.List;


public class ShowMemberActivityCommand extends BaseCommand {

    public static final String NO_ACTIVITY_FOR_MEMBER =
            "Currently there is no activity to display for this member.";
    public static final int EXPECT_NUMBER_OF_ARGUMENTS =0;

    public ShowMemberActivityCommand(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }
@Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentCount(parameters,
                EXPECT_NUMBER_OF_ARGUMENTS, NO_ACTIVITY_FOR_MEMBER);
        return showMemberActivity();
    }


    private String showMemberActivity(){
        List<Member> memberList = getRepository().getMemberList();
        StringBuilder sb = new StringBuilder();
        for (Member member: memberList) {
            sb.append( member.getActivityHistory());

        }
        return sb.toString();
    }

}
