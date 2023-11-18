package core;

import core.contracts.TaskManagementRepository;
import models.*;
import models.contracts.*;
import models.enums.Priority;
import models.enums.Severity;
import models.enums.Size;
import utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;



public class TaskManagementRepositoryImpl implements TaskManagementRepository {


    public static final String THERE_ARE_IS_NO_MEMBER_WITH_NAME = "There are is no member with this name";
    public static final String BOARD_NAME_ALREADY_EXIST_MESSAGE = "This board name already exist.";
    public static final String THERE_IS_NO_TEAM_WITH_NAME_S = "There is no Team with name %s ";
    public static final String NO_LOGGED_IN_MEMBER = "There is no logged in member.";

    public static List<Member> memberList = new ArrayList<>();
     public static List<Board> boardList = new ArrayList<>();
    public static List<Team> teamsList = new ArrayList<>();
    private Member loggedMember;

    private int id;


    public TaskManagementRepositoryImpl() {
        this.loggedMember = null;
        this.id = 1;
    }


    @Override
    public Member findMemberByUsername(String username) {
        return memberList
                .stream()
                .filter(u -> u.getName().equals(username))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(THERE_ARE_IS_NO_MEMBER_WITH_NAME));
    }

    @Override
    public boolean memberExist(String username) {
        return memberList
                .stream()
                .anyMatch(u -> u.getName().equals(username));
    }

    @Override
    public Member createMember(String username) {
        return new MemberImpl(username);
    }

    @Override
    public Board createBoard(String name) {
        return new BoardImpl(name);
    }

    @Override
    public Team createTeam(String name) {
        return new TeamImpl(name);
    }

    public void addBoard(Board board) {
        ValidationHelpers.validateBoardName(boardList, board.getName(), BOARD_NAME_ALREADY_EXIST_MESSAGE);
        boardList.add(board);

    }

    @Override
    public Bug createBug(String title, String boardToAdd, String description, List<String> stepsToReproduce,
                         Priority priority, Severity severity, String assignee) {
        return new BugImpl(++id, title, description, stepsToReproduce, priority, severity, assignee);
    }

    @Override
    public Story createStory(String title, String description, Priority priority, Size size, String assignee) {
        return null;
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
    public Team findTeamByName(String name) {
        Team team = teamsList.stream()
                .filter(u -> u.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format(THERE_IS_NO_TEAM_WITH_NAME_S, name)));
        return team;
    }

    @Override
    public Member getLoggedInMember() {
        if (loggedMember == null) {
            throw new IllegalArgumentException(NO_LOGGED_IN_MEMBER);
        }
        return loggedMember;
    }

    @Override
    public boolean hasLoggedInMember() {
        return loggedMember != null;
    }

    @Override
    public void login(Member member) {
        loggedMember = member;
    }

    @Override
    public void logout() {
        loggedMember = null;
    }

    public Feedback createFeedback(String title, String description, int rating) {
        return new FeedbackImpl(title, description, rating);
    }

    @Override
    public Board findBoardByName(String name) {
        Board board = boardList.stream()
                .filter(u -> u.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format(THERE_IS_NO_TEAM_WITH_NAME_S, name)));
        return board;
    }

}
