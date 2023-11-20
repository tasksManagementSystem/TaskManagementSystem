package core.contracts;

import models.contracts.*;
import models.enums.Priority;
import models.enums.Severity;
import models.enums.Size;

import java.util.List;

public interface TaskManagementRepository {

    List<Member> getMemberList();

    Member findMemberByUsername (String username);
    Board findBoardByName (String name);

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

    Member getLoggedInMember();

    boolean hasLoggedInMember();

    void login(Member member);

    void logout();

    Bug createBug(String title, String boardToAdd, String description, List<String> stepsToReproduce,
                  Priority priority, Severity severity, String assignee);

    Story createStory(String title,String boardToAdd, String description, Priority priority, Size size, String assignee);

    Team findTeamByMember(Member member);

    Feedback createFeedback(String title, String description, int rating);

    List<Comment> getComments();

    void addComments(Comment comment);

}
