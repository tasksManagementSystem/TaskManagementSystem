package core.contracts;

import models.contracts.*;
import models.enums.Priority;
import models.enums.Severity;
import models.enums.Size;

import java.util.ArrayList;
import java.util.List;

public interface TaskManagementRepository {

    List<Member> getMemberList();

    Member findMemberByUsername(String username);

    Board findBoardByName(String name);

    boolean memberExist(String username);

    Member createMember(String username);

    Board createBoard(String name);

    Team createTeam(String name);

    void addBoard(Board board);

    void addMember(Member member);

    void addTeam(Team team);

    List<Team> getTeamsList();

    List<Board> getBoardList();


    Severity getSeverity();

    Team findTeamByName(String name);

    Team findTeamByMember(String member);

    Member getLoggedInMember();

    boolean hasLoggedInMember();

    void login(Member member);

    void logout();

    Bug createBug(String title, String boardToAdd, String description, List<String> stepsToReproduce,
                  Priority priority, Severity severity, String assignee);

    Story createStory(String title, String boardToAdd, String description, Priority priority, Size size, String assignee);

//    Team findTeamByMember(String member);

    Feedback createFeedback(String title, String description, int rating);

    List<Comment> getComments();

    void addComments(Comment comment);

    <T extends Task> T findElementById(List<T> elements, int id, String elementType);

    List<Bug> getBugList();

    List<Feedback> getFeedbackList();

    public List<Story> getStoryList();

    int getNextId();

    void addBug(Bug bug);

    void addStory(Story story);

    void addFeedback(Feedback feedback);

    String showAllTeams();
}
