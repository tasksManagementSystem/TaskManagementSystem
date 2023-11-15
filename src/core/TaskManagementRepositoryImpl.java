package core;

import core.contracts.TaskManagementRepository;
import models.BoardImpl;
import models.MemberImpl;
import models.contracts.Board;
import models.contracts.Member;
import utils.ValidationHelpers;

import java.util.List;

public class TaskManagementRepositoryImpl implements TaskManagementRepository {


    public static final String THERE_ARE_IS_NO_MEMBER_WITH_NAME = "There are is no member with this name";
    public static final String BOARD_NAME_ALREADY_EXIST_MESSAGE = "This board name already exist.";

    List<Member> memberList;
    List<Board> boardList;


    public TaskManagementRepositoryImpl (){


    }

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
    public Member createMember (String username){
        return new MemberImpl(username);
    }
    @Override
    public Board createBoard (String name){
        return new BoardImpl(name);
    }
    public void addBoard(Board board){
        ValidationHelpers.validateBoardName(boardList,board.getName(),BOARD_NAME_ALREADY_EXIST_MESSAGE);
        boardList.add(board);

    }

}
