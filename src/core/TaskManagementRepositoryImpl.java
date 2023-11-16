package core;

import core.contracts.TaskManagementRepository;
import models.MemberImpl;
import models.contracts.Member;

import java.util.List;

public class TaskManagementRepositoryImpl implements TaskManagementRepository {


    public static final String THERE_ARE_IS_NO_MEMBER_WITH_NAME = "There are is no member with this name";
    List<Member> memberList;


    public TaskManagementRepositoryImpl (){}

    public List<Member> getMemberList() {
        return memberList;
    }
    @Override
    public Member findMemberByUsername(String username){
        return memberList
                .stream()
                .filter(u->u.getName().equals(username))
                .findFirst()
                .orElseThrow(()->new IllegalArgumentException(THERE_ARE_IS_NO_MEMBER_WITH_NAME));
    }
    @Override
    public boolean memberExist(String username){
        return memberList
                .stream()
                .anyMatch(u-> u.getName().equals(username));
    }
    @Override
    public Member creatMember (String username){
        return new MemberImpl(username);
    }

}
