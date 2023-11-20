package core;

import core.contracts.TaskManagementRepository;
import models.*;
import models.contracts.*;
import models.enums.Priority;
import models.enums.Severity;
import models.enums.Size;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class TaskManagementRepositoryImpl implements TaskManagementRepository {


    public static final String THERE_ARE_IS_NO_MEMBER_WITH_NAME = "There are is no member with this name";
    public static final String THERE_IS_NO_TEAM_WITH_NAME_S = "There is no Team with name %s ";
    public static final String NO_LOGGED_IN_MEMBER = "There is no logged in member.";
    public static final String THERE_IS_NO_TEAM_WITH_THIS_MEMBER = "There is no team with this member";
    public final static String MEMBER_LOGGED_OUT = "Member logged out!";
    private final List<Member> memberList = new ArrayList<>();
    private final List<Board> boardList = new ArrayList<>();
    private final List<Team> teamsList = new ArrayList<>();

    private final List<Comment> comments = new ArrayList<>();
    private Member loggedMember;

    private int id;


    public TaskManagementRepositoryImpl() {
        this.loggedMember = null;
        this.id = 0;
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


    @Override
    public Bug createBug(String title, String boardToAdd, String description, List<String> stepsToReproduce,
                         Priority priority, Severity severity, String assignee) {
        return new BugImpl(++id, title, description, stepsToReproduce, priority, severity, assignee);
    }

    @Override
    public Story createStory(String title, String boardToAdd, String description, Priority priority, Size size, String assignee) {
        return new StoryImpl(++id, title, description, priority, size, assignee);
    }

    @Override
    public Team findTeamByMember(Member member) {
        for (Team team :
                teamsList) {
            List<Member> members = team.getMembers();
            for (Member memberName :
                    members) {
                if (memberName.equals(member)) {

                    return team;
                }
            }
        }
        throw new IllegalArgumentException(THERE_IS_NO_TEAM_WITH_THIS_MEMBER);
    }


    @Override
    public int hashCode() {
        return Objects.hash(loggedMember, id);
    }


    @Override
    public List<Team> getTeamsList() {
        return new ArrayList<>(teamsList);
    }

    public void addTeam(Team team) {
        teamsList.add(team);

    }

    @Override
    public List<Board> getBoardList() {
        return new ArrayList<>(boardList);

    }

    public void addBoard(Board board) {
        boardList.add(board);

    }

    @Override
    public Severity getSeverity() {
        return null;
    }

    @Override
    public List<Member> getMemberList() {
        return new ArrayList<>(memberList);
    }

    public void addMember(Member member) {
        memberList.add(member);

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
        loggedMember.addHistory((MEMBER_LOGGED_OUT));
        loggedMember = null;

    }

    public Feedback createFeedback(String title, String description, int rating) {
        return new FeedbackImpl(++id, title, description, rating);
    }

    @Override
    public Board findBoardByName(String name) {
        Board board = boardList.stream()
                .filter(u -> u.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format(THERE_IS_NO_TEAM_WITH_NAME_S, name)));
        return board;
    }

    public List<Comment> getComments() {
        return new ArrayList<>(comments);
    }

    public void addComments(Comment comment) {
        comments.add(comment);
    }

}
