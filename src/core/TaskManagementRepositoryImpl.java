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
import java.util.Objects;



public class TaskManagementRepositoryImpl implements TaskManagementRepository {

    public static final int NAME_MAX_LENGTH = 15;
    public static final int NAME_MAX_LENGTH_BOARD = 10;
    public static final int NAME_MIN_LENGTH = 5;
    public static final String NAME_ALREADY_EXIST_MESSAGE = "This name already exist.";
    public static final String INVALID_NAME_MESSAGE =
            String.format("Name should be between %d and %d symbols.",NAME_MIN_LENGTH,NAME_MAX_LENGTH);
    public static final String THERE_ARE_IS_NO_MEMBER_WITH_NAME = "There are is no member with this name";
    public static final String THERE_IS_NO_TEAM_WITH_NAME_S = "There is no Team with name %s ";
    public static final String NO_LOGGED_IN_MEMBER = "There is no logged in member.";
    public static final String THERE_IS_NO_TEAM_WITH_THIS_MEMBER = "There is no team with this member";
    public final static String MEMBER_LOGGED_OUT = "Member logged out!";
    public static final String ID_DOES_NOT_EXIST = "%s with id %d does not exist.";
    private final List<Member> memberList = new ArrayList<>();
    private final List<Board> boardList = new ArrayList<>();
    private final List<Bug> bugList = new ArrayList<>();
    private final List<Feedback> feedbackList = new ArrayList<>();
    private final List<Story> storyList = new ArrayList<>();
    private final List<Team> teamsList = new ArrayList<>();
    private final List<Comment> comments = new ArrayList<>();
    private Member loggedMember;
    private int nextId;

    public TaskManagementRepositoryImpl() {
        this.loggedMember = null;
         nextId =0;
    }

    public int getNextId() {
        return nextId;
    }

    @Override
    public List<Bug> getBugList() {
        return new ArrayList<>(bugList);
    }
    @Override
    public List<Feedback> getFeedbackList() {
        return new ArrayList<>(feedbackList);
    }
    @Override
    public List<Team> getTeamsList() {
        return new ArrayList<>(teamsList);
    }
    @Override
    public List<Story> getStoryList() {
        return new ArrayList<>(storyList);
    }
    @Override
    public List<Board> getBoardList() {
        return new ArrayList<>(boardList);

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
    public Member getLoggedInMember() {
        if (loggedMember == null) {
            throw new IllegalArgumentException(NO_LOGGED_IN_MEMBER);
        }
        return loggedMember;
    }
    public List<Comment> getComments() {
        return new ArrayList<>(comments);
    }

    @Override
    public <T extends Task> T findElementById(List<T> elements, int id, String elementType){
        for(T element:elements){
            if(element.getId()==id){
                return element;
            }
        }throw new IllegalArgumentException(String.format(ID_DOES_NOT_EXIST,elementType,id));
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
    public Team findTeamByMember(String member) {
        return teamsList.stream()
                .filter(team1 -> team1.getMembers().stream()
                .anyMatch(memberName->memberName.getName().equals(member)))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(THERE_IS_NO_TEAM_WITH_THIS_MEMBER));

//        for (Team team :
//                teamsList) {
//            List<Member> members = team.getMembers();
//            for (Member memberName :
//                    members) {
//                if (memberName.getName().equals(member)) {
//
//                    return team;
                //}


//     throw new IllegalArgumentException(THERE_IS_NO_TEAM_WITH_THIS_MEMBER);
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
    public Board findBoardByName(String name) {
        Board board = boardList.stream()
                .filter(u -> u.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format(THERE_IS_NO_TEAM_WITH_NAME_S, name)));
        return board;
    }
    @Override
    public boolean memberExist(String username) {
        return memberList
                .stream()
                .anyMatch(u -> u.getName().equals(username));
    }
    @Override
    public Member createMember(String username) {
        ValidationHelpers.validateStringLength(username, NAME_MIN_LENGTH, NAME_MAX_LENGTH,String.format(INVALID_NAME_MESSAGE));
        ValidationHelpers.validateMemberName(memberList,username,NAME_ALREADY_EXIST_MESSAGE);

        return new MemberImpl(username);
    }
    @Override
    public Board createBoard(String name) {
        ValidationHelpers.validateStringLength(name, NAME_MIN_LENGTH, NAME_MAX_LENGTH_BOARD,String.format(INVALID_NAME_MESSAGE));
        ValidationHelpers.validateBoardName(boardList,name,NAME_ALREADY_EXIST_MESSAGE);

        return new BoardImpl(name);
    }

    @Override
    public Team createTeam(String name) {
        ValidationHelpers.validateStringLength(name, NAME_MIN_LENGTH, NAME_MAX_LENGTH,String.format(INVALID_NAME_MESSAGE,NAME_MIN_LENGTH,NAME_MAX_LENGTH));
        ValidationHelpers.validateTeamName(teamsList,name,NAME_ALREADY_EXIST_MESSAGE);
        return new TeamImpl(name);
    }
    @Override
    public Bug createBug(String title,String boardToAdd, String description, List<String> stepsToReproduce,
                         Priority priority, Severity severity, String assignee) {
        return new BugImpl(++nextId, title, description, stepsToReproduce, priority, severity, assignee);
    }
    @Override
    public Story createStory(String title, String boardToAdd, String description, Priority priority, Size size, String assignee) {
        return new StoryImpl(++nextId, title, description, priority, size, assignee);
    }
    public Feedback createFeedback(String title, String description, int rating) {
        return new FeedbackImpl(++nextId, title, description, rating);
    }
    public void addMember(Member member) {memberList.add(member);}
    public void addTeam(Team team) {
        teamsList.add(team);
    }

    public void addBoard(Board board) {
        boardList.add(board);
    }
    public void addComments(Comment comment) {
        comments.add(comment);
    }
    @Override
    public void addBug(Bug bug) {bugList.add(bug);}
    @Override
    public void addStory(Story story) {storyList.add(story);}
    @Override
    public void addFeedback(Feedback feedback) {feedbackList.add(feedback);}
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

    public String showAllTeams(){
        StringBuilder allNames = new StringBuilder();
        for (Team name :
                teamsList) {
            allNames.append(name.getName() + ", ");
        }
        return allNames.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskManagementRepositoryImpl that = (TaskManagementRepositoryImpl) o;
        return nextId == that.nextId && Objects.equals(memberList, that.memberList) && Objects.equals(boardList, that.boardList) && Objects.equals(bugList, that.bugList) && Objects.equals(feedbackList, that.feedbackList) && Objects.equals(storyList, that.storyList) && Objects.equals(teamsList, that.teamsList) && Objects.equals(comments, that.comments) && Objects.equals(loggedMember, that.loggedMember);
    }


}
