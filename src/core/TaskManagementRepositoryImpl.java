package core;

import core.contracts.TaskManagementRepository;
import models.BoardImpl;
import models.MemberImpl;
import models.TeamImpl;
import models.contracts.Board;
import models.contracts.Member;
import models.contracts.Team;
import models.enums.Severity;
import utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public class TaskManagementRepositoryImpl implements TaskManagementRepository {


    public static final String THERE_ARE_IS_NO_MEMBER_WITH_NAME = "There are is no member with this name";
    public static final String BOARD_NAME_ALREADY_EXIST_MESSAGE = "This board name already exist.";
    public static final String THERE_IS_NO_TEAM_WITH_NAME_S = "There is no Team with name %s ";

    List<Member> memberList;
    List<Board> boardList;
    List<Team> teamsList;


    public TaskManagementRepositoryImpl (){}


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

    @Override
    public Team createTeam(String name) {
        return new TeamImpl(name);
    }

    public void addBoard(Board board){
        ValidationHelpers.validateBoardName(boardList,board.getName(),BOARD_NAME_ALREADY_EXIST_MESSAGE);
        boardList.add(board);

    }

    @Override
    public List<Board> getBoards() {
        return new ArrayList<>(boardList);
    }

    @Override
    public List<Team> getTeamsList() {
        return new ArrayList<>(teamsList);
    }

    @Override
    public Severity getSeverity() {
        return null;
    }

    @Override
    public List<Member> getMemberList() {
        return new ArrayList<>(memberList);
    }

    @Override
    public Team findTeamByName(String name){
        Team team = teamsList.stream()
                .filter(u -> u.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() ->new IllegalArgumentException(String.format(THERE_IS_NO_TEAM_WITH_NAME_S,name)));
                return team;
    }
}
