package commands.showCommands;

import commands.BaseCommand;
import core.contracts.TaskManagementRepository;
import models.HistoryImpl;
import models.MemberImpl;
import models.contracts.History;
import models.contracts.Member;
import utils.ParsingHelpers;
import utils.ValidationHelpers;
import java.util.List;


public class ShowMemberActivityCommand extends BaseCommand {

    public static final String NO_ACTIVITY_FOR_MEMBER =
            "Currently there is no activity to display for this member.";
    public static final int EXPECT_NUMBER_OF_ARGUMENTS =1;

    public ShowMemberActivityCommand(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }
@Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentCount(parameters,
                EXPECT_NUMBER_OF_ARGUMENTS, NO_ACTIVITY_FOR_MEMBER);

    String memberName = parameters.get(0);
        return showMemberActivity(memberName);
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }


    private String showMemberActivity(String memberName){
        List<Member> memberList = getRepository().getMemberList();
        StringBuilder sb = new StringBuilder();
        for(Member member:memberList){
            if(member.getName().equals(memberName)){
                    List<History> histories=member.getActivityHistory();
                    for(History history:histories){
                        sb.append(history);
                    }
            }
        }

          //  sb.append( member.getActivityHistory());


        return sb.toString();
    }

}
